package me.right42.heregather.config;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QueryDslConfig {

	@Bean
	public JPAQueryFactory queryFactory(EntityManager entityManager){
		return new JPAQueryFactory(entityManager);
	}

}
