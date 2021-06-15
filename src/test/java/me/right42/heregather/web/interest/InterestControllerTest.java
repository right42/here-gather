package me.right42.heregather.web.interest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class InterestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void findAll() throws Exception{

		mockMvc.perform(
			get("/interests")
			.accept(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].groupName").value("운동/스포츠"));
	}

	@Test
	void findSearch() throws Exception{
		String keyword = "온라인게임";

		mockMvc.perform(
			get("/interests?keyword=" + keyword)
			.accept(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].interests[0].name").value("온라인게임"));
	}

}