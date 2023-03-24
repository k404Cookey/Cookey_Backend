package com.k404.Cookey.domain.user.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import com.k404.Cookey.Security.JwtTokenProvider;
import com.k404.Cookey.domain.common.Result;
import com.k404.Cookey.domain.recipe.dto.UserDto;
import com.k404.Cookey.domain.user.entity.User;
import com.k404.Cookey.domain.user.repository.UserRepository;
import com.k404.Cookey.domain.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/users/me")
    public Result<UserDto> get(Principal principal){
        User user = (User) ((Authentication) principal).getPrincipal();

        return new Result<UserDto>(new UserDto(user));
    }

    @GetMapping("/users/{id}")
    public Result<UserDto> get(@PathVariable Long id){

        User user = userService.get(id);

        return new Result<UserDto>(new UserDto(user));
    }

    @PostMapping("/join")
    public Result<Long> join(@RequestBody Map<String, String> user) {

        Long userId = userService.join(user.get("name"), user.get("email"), user.get("imageUrl"),"ROLE_USER");

        return new Result<Long>(userId);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody Map<String, String> user) {

        return new Result<String>(userService.makeJwtByEmail(user.get("email")));
    }
    
    @PutMapping("/user/image")
    public Result<Boolean> imageUpdate(@RequestBody Map<String, String> imageUrl, Principal principal) {
    	User user = (User) ((Authentication) principal).getPrincipal();
		userService.imageUpdate(user, imageUrl.get("imageUrl"));
		return new Result<Boolean>(true);
    }
    
}
