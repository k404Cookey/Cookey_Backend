package com.k404.Cookey.uploader.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FireBaseService {
	
	public String upload(MultipartFile multipartFile)  {
		
		try {
		
		String fileName = multipartFile.getOriginalFilename();                        // to get original file name
        fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name. 
		
		File uploadFile = this.convertToFile(multipartFile, fileName);
		String TEMP_URL = this.uploadToFireBase(uploadFile, fileName);   
		uploadFile.delete(); 
		
		return TEMP_URL;                     // Your customized response
		
		} catch (Exception e) {
			
        e.printStackTrace();
        
      
    }		return null;

}
	
	private String uploadToFireBase(File uploadFile, String fileName) throws Exception {
		
		 BlobId blobId = BlobId.of("k404-cookey-99e43.appspot.com", fileName);
	     BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
	     Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/serviceAccountKey.json"));
	     Storage storage =  StorageOptions.newBuilder().setCredentials(credentials).build().getService();
	     storage.create(blobInfo, Files.readAllBytes(uploadFile.toPath()));
	     return String.format("https://firebasestorage.googleapis.com/v0/b/k404-cookey-99e43.appspot.com/o/%s?alt=media", URLEncoder.encode(fileName, StandardCharsets.UTF_8));		
	}
	
	private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
		File tempFile = new File(fileName);
	        
		try (FileOutputStream fos = new FileOutputStream(tempFile)) {
	            fos.write(multipartFile.getBytes());
	            fos.close();
	    }
			return tempFile;
	    }
	
//	private Optional<File> convert(MultipartFile file) throws IOException {
//		
//	      File convertFile = new File(file.getOriginalFilename());
//	      
//	      if(convertFile.createNewFile()) {
//	          try (FileOutputStream fos = new FileOutputStream(convertFile)) {
//	              fos.write(file.getBytes());
//	          }
//	          return Optional.of(convertFile);
//	      }
//	
//	      return Optional.empty();
//	  }
	
//	private void removeNewFile(File targetFile) {
//		
//	      targetFile.delete();
//	 }
	
	private String getExtension(String fileName) {
		
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
