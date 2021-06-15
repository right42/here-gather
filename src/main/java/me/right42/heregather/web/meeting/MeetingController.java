package me.right42.heregather.web.meeting;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.service.meeting.MeetingService;
import me.right42.heregather.web.dto.UserDto;
import me.right42.heregather.web.dto.meeting.MeetingCreateDto;
import me.right42.heregather.web.dto.meeting.MeetingJoinDto;
import me.right42.heregather.web.dto.response.IdResponse;

@RestController
@RequiredArgsConstructor
public class MeetingController {

	private final MeetingService meetingService;

	@PostMapping("/meeting")
	public IdResponse<Long> meetingCreate(@RequestBody @Valid MeetingCreateDto dto){
		return new IdResponse<>(meetingService.meetingCreate(dto, new UserDto(1L, "test")));
	}

	@PostMapping("/meeting-join")
	public IdResponse<Long> meetingJoin(@RequestBody @Valid MeetingJoinDto dto){
		return new IdResponse<>(meetingService.meetingJoin(dto));
	}
}
