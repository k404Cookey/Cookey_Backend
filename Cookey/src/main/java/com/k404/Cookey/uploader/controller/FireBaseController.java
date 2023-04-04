package com.k404.Cookey.uploader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.k404.Cookey.domain.common.Result;
import com.k404.Cookey.uploader.service.FireBaseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FireBaseController {
	
	private FireBaseService fireBaseService;
	
	// @PostMapping 어노테이션 -> HTTP POST 요청을 처리하기 위한 메소드
	@PostMapping("/upload/step")
	public Result uploadStep(@RequestParam("data") MultipartFile multipartFile)
			throws Exception {
		
		System.out.println("업로드실행");
		
		String imageUrl = fireBaseService.upload(multipartFile);
		return new Result(imageUrl);
	}
	
	// 각각의 API 엔드포인트에서 MultipartFile 객체를 파라미터로 받아와서,
	// 해당 파일을 Firebase에 업로드하고, 업로드된 이미지의 URL을 반환
	// Result 객체를 생성하여 해당 URL을 JSON 형태로 반환 
	
	@PostMapping("/upload/profile")
	public Result uploadProfile(@RequestParam("data") MultipartFile multipartFile)
			throws Exception {
		
		String imageUrl = fireBaseService.upload(multipartFile);
		return new Result(imageUrl);
	}
	
	@PostMapping("/upload/thumbnail")
	public Result upload(@RequestParam("data") MultipartFile multipartFile)
			throws Exception {
		
		String imageUrl = fireBaseService.upload(multipartFile);
		return new Result(imageUrl);
	}
	
		
}
