package me.right42.heregather.web.dto.group;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GroupJoinDto {

	@NotNull
	private Long groupId;

}
