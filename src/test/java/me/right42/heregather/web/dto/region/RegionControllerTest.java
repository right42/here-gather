package me.right42.heregather.web.dto.region;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import me.right42.heregather.util.TestUtil;

@SpringBootTest
@AutoConfigureMockMvc
class RegionControllerTest {

	@Autowired
	MockMvc mockMvc;


	@Test
	void regionSearch() throws Exception {
		RegionSearchDto dto = new RegionSearchDto(1, "서울");

		mockMvc.perform(
			get("/regions")
			.contentType(MediaType.APPLICATION_JSON)
			.content(TestUtil.objToJson(dto))
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].regionName").value("서울"));

	}


	@Test
	void regionSearch_badRequest_level() throws Exception {
		RegionSearchDto dto = new RegionSearchDto(0, "서울");

		mockMvc.perform(
			get("/regions")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(dto))
		)
		.andExpect(status().isBadRequest());
	}

	@Test
	void regionSearch_badRequest_regionName() throws Exception {
		RegionSearchDto dto = new RegionSearchDto(1, "");

		mockMvc.perform(
			get("/regions")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.objToJson(dto))
		)
		.andExpect(status().isBadRequest());
	}

}