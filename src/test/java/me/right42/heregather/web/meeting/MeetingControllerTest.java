package me.right42.heregather.web.meeting;

import static me.right42.heregather.ApiDocumentUtils.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoField;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import me.right42.heregather.util.TestUtil;
import me.right42.heregather.web.dto.UserDto;
import me.right42.heregather.web.dto.meeting.MeetingCreateDto;
import me.right42.heregather.web.dto.meeting.MeetingJoinDto;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class MeetingControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	@Transactional
	@Rollback(value = true)
	void 미팅생성() throws Exception {

		MeetingCreateDto meetingCreateDto = new MeetingCreateDto();
		meetingCreateDto.setCost(1000);
		meetingCreateDto.setGroupId(1L);
		meetingCreateDto.setRegionId(1L);
		meetingCreateDto.setMaximumUser(5);
		meetingCreateDto.setTitle("즐거운 만남");
		meetingCreateDto.setStartTime(LocalDateTime.now().toString());
		meetingCreateDto.setEndTime(LocalDateTime.now().plusDays(1).toString());


		mockMvc.perform(
			post("/meeting")
			.contentType(MediaType.APPLICATION_JSON)
			.content(TestUtil.objToJson(meetingCreateDto))
		)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").isNumber())


		.andDo(
			document(
				"meeting-create"
				, getDocumentRequest()
				, getDocumentResponse()
				, requestFields(
					fieldWithPath("cost").type(JsonFieldType.NUMBER).description("참가 금액"),
					fieldWithPath("maximumUser").type(JsonFieldType.NUMBER).description("참가 최대인원"),
					fieldWithPath("title").type(JsonFieldType.STRING).description("모임 제목"),
					fieldWithPath("regionId").type(JsonFieldType.NUMBER).description("지역 고유번호"),
					fieldWithPath("groupId").type(JsonFieldType.NUMBER).description("그룹 고유번호"),
					fieldWithPath("startTime").type(JsonFieldType.STRING).description("모임 시작날짜"),
					fieldWithPath("endTime").type(JsonFieldType.STRING).description("모임 종료날짜")
				), responseFields(
					fieldWithPath("id").type(JsonFieldType.NUMBER).description("생성된 모임 고유번호")
				)
		));
	}

	@Test
	@Transactional
	@Rollback(value = true)
	void 미팅참가() throws Exception {

		MeetingJoinDto dto = new MeetingJoinDto();
		dto.setMeetingId(162L);
		dto.setUserDto(new UserDto(1L, "test"));

		mockMvc.perform(
			post("/meeting-join")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(dto))
		)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").isNumber());
	}

}