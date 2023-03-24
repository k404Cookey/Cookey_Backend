package com.k404.Cookey.domain.user.repository;

import java.util.Optional;

import com.k404.Cookey.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
