package com.k404.Cookey.domain.user.service;

import com.k404.Cookey.Security.JwtTokenProvider;
import com.k404.Cookey.domain.user.entity.User;
import com.k404.Cookey.domain.user.repository.UserRepository;
import com.k404.Cookey.domain.user_follow.entity.UserFollow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Transactional
    public void imageUpdate(User user, String imageUrl){
		user.setImageUrl(imageUrl);
		userRepository.save(user);
    }

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public User get(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 유저가 없습니다. id : " + id));

        return user;
    }

    @Transactional
    public Long join(String name, String email, String imageUrl, String role){
        return userRepository.save(User.builder()
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .roles(Collections.singletonList(role))
                .build()).getId();
    }

    @Transactional
    public String makeJwtByEmail(String email){
        User member = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        return jwtTokenProvider.createToken(member);

    }
    
}
