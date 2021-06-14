package me.right42.heregather.web.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.user.User;
import me.right42.heregather.service.user.UserService;
import me.right42.heregather.web.dto.user.UserResponseDto;
import me.right42.heregather.web.dto.user.UserRegisterDto;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public UserResponseDto register(@RequestBody UserRegisterDto userRegisterDto){
		User register = userService.register(userRegisterDto);
		return new UserResponseDto(register.getId());
	}

}
