package me.right42.heregather.web.group;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.service.group.GroupService;
import me.right42.heregather.web.dto.UserDto;
import me.right42.heregather.web.dto.group.GroupCreateDto;
import me.right42.heregather.web.dto.group.GroupJoinDto;
import me.right42.heregather.web.dto.response.IdResponse;

@RestController
@RequiredArgsConstructor
public class GroupController {

	private final GroupService groupService;

	@PostMapping("/group")
	public IdResponse<Long> group(@RequestBody @Valid GroupCreateDto groupCreateDto){
		return new IdResponse<>(groupService.createGroup(groupCreateDto));
	}

	@PostMapping("/join")
	public IdResponse<Long> join(@RequestBody @Valid GroupJoinDto groupJoinDto) {
		return new IdResponse<>(groupService.joinGroup(groupJoinDto, new UserDto(2L, "test2")));
	}
}
