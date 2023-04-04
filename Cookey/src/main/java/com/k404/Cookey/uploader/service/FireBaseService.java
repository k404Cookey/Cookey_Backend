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
		
		String fileName = multipartFile.getOriginalFilename();                        
		// 원본파일 이름 가져오기
        fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  
        // 파일 이름에 UUID 값을 추가하여 고유한 이름으로 파일을 저장
		
		File uploadFile = this.convertToFile(multipartFile, fileName);		
		// MultipartFile 객체를 File 객체로 변환 -> 변환된 파일을 Firebase Storage에 업로드
		String TEMP_URL = this.uploadToFireBase(uploadFile, fileName);   	
		// 파일 업로드가 완료되면, 업로드된 파일을 삭제
		uploadFile.delete(); 
		
		return TEMP_URL;                     // 업로드된 파일의 다운로드 URL을 반환
		
		} catch (Exception e) {
			
        e.printStackTrace();
        
      
    }		return null;

}
	
private String uploadToFireBase(File uploadFile, String fileName) throws Exception {
	
	 BlobId blobId = BlobId.of("k404-cookey-99e43.appspot.com", fileName); 
	 // BlobId 객체를 생성하여 업로드할 파일을 지정
	 
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
    // BlobInfo 객체를 생성하여 해당 Blob에 대한 정보를 설정
    
    Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/serviceAccountKey.json"));
    // GoogleCredentials 객체를 생성
    
    Storage storage =  StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    // Storage 객체를 생성
    
    storage.create(blobInfo, Files.readAllBytes(uploadFile.toPath()));
    //create 메소드를 사용하여 Blob을 생성하고 업로드
    
    return String.format("https://firebasestorage.googleapis.com/v0/b/k404-cookey-99e43.appspot.com/o/%s?alt=media", URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    // 업로드된 Blob의 다운로드 URL을 반환
    // URL은 Firebase Storage의 Bucket 이름과 파일 이름으로 구성, URLEncoder를 사용하여 파일 이름을 인코딩
}
	
	//MultipartFile 객체를 File 객체로 변환하는 함수
	private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
		File tempFile = new File(fileName);
	        
		try (FileOutputStream fos = new FileOutputStream(tempFile)) {
	            fos.write(multipartFile.getBytes());
	            fos.close();
	    }
		return tempFile;
	    
	}
	
	// 파일 이름에서 확장자를 추출
	private String getExtension(String fileName) {
		
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
