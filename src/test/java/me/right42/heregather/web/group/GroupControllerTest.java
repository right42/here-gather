package me.right42.heregather.web.group;

import static me.right42.heregather.ApiDocumentUtils.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import me.right42.heregather.domain.group.Group;
import me.right42.heregather.util.TestUtil;
import me.right42.heregather.web.dto.group.GroupCreateDto;
import me.right42.heregather.web.dto.group.GroupInterestIdDto;
import me.right42.heregather.web.dto.group.GroupJoinDto;
import me.right42.heregather.web.dto.group.GroupRegionIdDto;
import me.right42.heregather.web.dto.group.GroupUpdateDto;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class GroupControllerTest {

	@Autowired
	MockMvc mockMvc;

	@PersistenceContext
	private EntityManager entityManger;

	@Test
	@Rollback
	@Transactional
	void 그룹생성_테스트() throws Exception {
		GroupCreateDto groupCreateDto = new GroupCreateDto();
		groupCreateDto.setGroupName("test");
		groupCreateDto.setDescription("기타그룹");
		groupCreateDto.setMaximumPeople(5);
		groupCreateDto.setGroupInterests(Arrays.asList(new GroupInterestIdDto(1L), new GroupInterestIdDto(2L)));
		groupCreateDto.setGroupRegions(Arrays.asList(new GroupRegionIdDto(1L), new GroupRegionIdDto(2L)));

		ResultActions result = mockMvc.perform(
			post("/group")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(groupCreateDto))
		);
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").isNotEmpty());

		result.andDo(
			document(
				"group-create"
				, getDocumentRequest()
				, getDocumentResponse()
				, requestFields(
					fieldWithPath("groupName").type(JsonFieldType.STRING).description("그룹 이름"),
					fieldWithPath("maximumPeople").type(JsonFieldType.NUMBER).description("그룹 최대인원"),
					fieldWithPath("description").type(JsonFieldType.STRING).description("설명"),
					fieldWithPath("groupRegions[].regionId").type(JsonFieldType.NUMBER).description("그룹 지역 고유 번호"),
					fieldWithPath("groupInterests[].id").type(JsonFieldType.NUMBER).description("그룹 관심사 고유번호")
				), responseFields(
					fieldWithPath("id").type(JsonFieldType.NUMBER).description("생성된 그룹 고유번호")
				)
			));
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

	@Test
	void 그룹조회() throws Exception {

		mockMvc.perform(
			get("/groups")
			.param("keyword", "test")
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].groupName").value("test"))
		;
	}

	@Test
	void 그룹정보변경() throws Exception {
		GroupUpdateDto dto = new GroupUpdateDto();
		dto.setId(1L);
		dto.setName("test1");
		dto.setMaximumPeople(5);

		mockMvc.perform(
			patch("/group")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(dto))
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").isNotEmpty());
	}

}