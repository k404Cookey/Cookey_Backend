package com.k404.Cookey.uploader.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class FireBaseService {    

//	@Value("${firebase.bucket-name}")
//	private String firebaseBucket;
	
//	public String uploadFile(MultipartFile file) {
//		try {
//            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//            BlobId blobId = BlobId.of(firebaseBucket, fileName);
//            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//            byte[] fileBytes = file.getBytes();
//            storage.create(blobInfo, fileBytes);
//            return fileName;
//		} catch(IOException e) {
//			throw new RuntimeException("Failed to upload file", e);
//		}
//	}
//	public String uploadFiles(MultipartFile file, String nameFile) throws IOException {
//		Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
//		InputStream content =new ByteArrayInputStream(file.getBytes());
//		Blob blob = bucket.create(nameFile.toString(), content, file.getContentType());
//		return blob.getMediaLink();
//	}
	private String uploadToFireBase(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("k404-cookey.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/serviceAccountKey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format("https://firebasestorage.googleapis.com/v0/b/k404-cookey.appspot.com/o/%s?alt=media", URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }
    
    private void removeNewFile(File targetFile) {
        
        targetFile.delete();
   }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    
    public String upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name. 

            File uploadFile = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = this.uploadToFireBase(uploadFile, fileName);                                   // to get uploaded file link
            uploadFile.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return TEMP_URL;                    // Your customized response
        } catch (Exception e) {
            e.printStackTrace();            
        } return null;

    }
        
	// s3서버 예제
//	    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
//	        File uploadFile = convert(multipartFile)
//	                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
//	
//	        return uploadToFireBase(uploadFile, dirName);
//	    }
//	
//	    private String uploadToFireBase(File uploadFile, String dirName) {
//	        String fileName = dirName + "/" + uploadFile.getName();
//	        s3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
//	
//	        //로컬 파일 삭제
//	        removeNewFile(uploadFile);
//	
//	        return s3Client.getUrl(bucket, fileName).toString();
//	    }
//	
//	    //Todo optional 공부 ㅠ
//	    private Optional<File> convert(MultipartFile file) throws IOException {
//	        File convertFile = new File(file.getOriginalFilename());
//	        if(convertFile.createNewFile()) {
//	            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
//	                fos.write(file.getBytes());
//	            }
//	            return Optional.of(convertFile);
//	        }
//	
//	        return Optional.empty();
//	    }
//	
//	    private void removeNewFile(File targetFile) {
//	        targetFile.delete();
//	    }	
}
