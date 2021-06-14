package me.right42.heregather;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest
class QueryDslTest {

	@Autowired
	EntityManager entityManager;

	JPAQueryFactory queryFactory;

	@Test
	void init(){
		queryFactory = new JPAQueryFactory(entityManager);

	}
}
