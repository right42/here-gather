package me.right42.heregather.web.group;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.service.group.GroupQueryService;
import me.right42.heregather.service.group.GroupService;
import me.right42.heregather.web.dto.UserDto;
import me.right42.heregather.web.dto.group.GroupCreateDto;
import me.right42.heregather.web.dto.group.GroupJoinDto;
import me.right42.heregather.web.dto.group.GroupResponseDto;
import me.right42.heregather.web.dto.group.GroupSearchDto;
import me.right42.heregather.web.dto.group.GroupUpdateDto;
import me.right42.heregather.web.dto.response.IdResponse;
import me.right42.heregather.web.dto.response.ListResponse;

@RestController
@RequiredArgsConstructor
public class GroupController {

	private final GroupService groupService;

	private final GroupQueryService groupQueryService;

	@GetMapping("/groups")
	public ListResponse<List<GroupResponseDto>> groups(@ModelAttribute @Valid GroupSearchDto groupSearchDto){
		return new ListResponse<>(groupQueryService.findAll(groupSearchDto));
	}

	@PostMapping("/group")
	public IdResponse<Long> group(@RequestBody @Valid GroupCreateDto groupCreateDto){
		return new IdResponse<>(groupService.createGroup(groupCreateDto));
	}

	@PostMapping("/join")
	public IdResponse<Long> join(@RequestBody @Valid GroupJoinDto groupJoinDto) {
		return new IdResponse<>(groupService.joinGroup(groupJoinDto, new UserDto(2L, "test2")));
	}

	@PatchMapping("/group")
	public IdResponse<Long> update(@RequestBody @Valid GroupUpdateDto dto) {
		return new IdResponse<>(groupService.changeGroup(dto));
	}
}
