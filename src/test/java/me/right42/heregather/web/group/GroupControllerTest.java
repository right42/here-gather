package me.right42.heregather.web.group;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import me.right42.heregather.util.TestUtil;
import me.right42.heregather.web.dto.group.GroupCreateDto;
import me.right42.heregather.web.dto.group.GroupInterestIdDto;
import me.right42.heregather.web.dto.group.GroupRegionIdDto;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {

	@Autowired
	MockMvc mockMvc;

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

}