package me.right42.heregather.web.dto.meeting;

import lombok.Data;
import me.right42.heregather.web.dto.UserDto;

@Data
public class MeetingJoinDto {

	private Long meetingId;

	private UserDto userDto;

}
