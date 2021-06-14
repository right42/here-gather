package me.right42.heregather.web.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import me.right42.heregather.domain.user.User;
import me.right42.heregather.service.user.UserService;
import me.right42.heregather.util.TestUtil;
import me.right42.heregather.web.dto.user.UserRegisterDto;

@WebMvcTest
@ContextConfiguration(classes = {
	UserService.class,
	UserController.class
})
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserService userService;

	@Test
	void 회원가입_테스트() throws Exception {
		UserRegisterDto dto = new UserRegisterDto();
		User user = User.builder()
				.id(1L)
				.build();

		when(userService.register(dto))
			.thenReturn(user);


		mockMvc.perform(
				post("/register")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(TestUtil.objToJson(dto))
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId").value("1"))
			;
	}

}