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
   
   @PostMapping("/upload/step")
   public Result uploadStep(@RequestParam("data") MultipartFile multipartFile, String nameFile)
         throws Exception {
      
      System.out.println("업로드실행");
      
       String imageUrl = fireBaseService.upload(multipartFile);
       return new Result(imageUrl);
   }
   
   @PostMapping("/upload/profile")
   public Result uploadProfile(@RequestParam("data") MultipartFile multipartFile, String nameFile)
         throws Exception {
      
       String imageUrl = fireBaseService.upload(multipartFile);
       return new Result(imageUrl);
   }
   
   @PostMapping("/upload/thumbnail")
   public Result upload(@RequestParam("data") MultipartFile multipartFile, String nameFile)
         throws Exception {
      
       String imageUrl = fireBaseService.upload(multipartFile);
       return new Result(imageUrl);
   }      
}