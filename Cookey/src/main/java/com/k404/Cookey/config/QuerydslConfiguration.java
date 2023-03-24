package com.k404.Cookey.config;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QuerydslConfiguration {
 
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em) {
	return new JPAQueryFactory(em);
	}

}


