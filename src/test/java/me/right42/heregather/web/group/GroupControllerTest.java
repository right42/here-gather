package me.right42.heregather.web.group;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import me.right42.heregather.domain.group.Group;
import me.right42.heregather.util.TestUtil;
import me.right42.heregather.web.dto.group.GroupCreateDto;
import me.right42.heregather.web.dto.group.GroupInterestIdDto;
import me.right42.heregather.web.dto.group.GroupJoinDto;
import me.right42.heregather.web.dto.group.GroupRegionIdDto;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {

	@Autowired
	MockMvc mockMvc;

	@PersistenceContext
	private EntityManager entityManger;

	@Test
	@Rollback
	void 그룹생성_테스트() throws Exception {
		GroupCreateDto groupCreateDto = new GroupCreateDto();
		groupCreateDto.setGroupName("test");
		groupCreateDto.setDescription("기타그룹");
		groupCreateDto.setMaximumPeople(5);
		groupCreateDto.setGroupInterests(Arrays.asList(new GroupInterestIdDto(1L), new GroupInterestIdDto(2L)));
		groupCreateDto.setGroupRegions(Arrays.asList(new GroupRegionIdDto(1L), new GroupRegionIdDto(2L)));


		mockMvc.perform(
			post("/group")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(groupCreateDto))
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").isNotEmpty())
		;
	}

	@Test
	@Rollback
	void 그룹참가_테스트() throws Exception {
		GroupJoinDto groupJoinDto = new GroupJoinDto();
		groupJoinDto.setGroupId(1L);

		mockMvc.perform(
			post("/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(groupJoinDto))
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").isNotEmpty());
	}

	@Test
	@Rollback
	@Transactional
	void 그룹참가_최대인원초과() throws Exception {
		Group group = Group.builder()
			.name("test2")
			.description("잘못된 그룹")
			.maximumPeople(0)
			.build();

		entityManger.persist(group);
		entityManger.flush();

		GroupJoinDto groupJoinDto = new GroupJoinDto();
		groupJoinDto.setGroupId(group.getId());

		mockMvc.perform(
			post("/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(groupJoinDto))
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value("그룹의 인원이 최대인원 입니다"));
	}

	@Test
	@Rollback
	@Transactional
	void 그룹참가_없는그룹() throws Exception {

		GroupJoinDto groupJoinDto = new GroupJoinDto();
		groupJoinDto.setGroupId(10000L);

		mockMvc.perform(
			post("/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(groupJoinDto))
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message").value("해당 그룹은 없는 그룹입니다."))
		;
	}

}