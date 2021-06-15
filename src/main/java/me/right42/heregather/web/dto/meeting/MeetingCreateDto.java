package me.right42.heregather.web.dto.meeting;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MeetingCreateDto {

	@NotNull
	private Long groupId;

	@NotBlank
	private String title;

	@NotNull
	private Integer cost;

	@NotNull
	private Integer maximumUser;

	@NotNull
	private String startTime;

	@NotNull
	private String endTime;

	@NotNull
	private Long regionId;
}
