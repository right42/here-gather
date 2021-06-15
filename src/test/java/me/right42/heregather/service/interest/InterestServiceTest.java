package me.right42.heregather.service.interest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import me.right42.heregather.web.dto.interst.InterestGroupingDto;

@SpringBootTest
class InterestServiceTest {

	@Autowired
	InterestService interestService;

	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional(readOnly = true)
	void cacheTest(){
		List<InterestGroupingDto> groupingDtos = interestService.findAll();
		System.out.println(groupingDtos);

		entityManager.clear();

		List<InterestGroupingDto> all = interestService.findAll();
		System.out.println(all);
	}

}