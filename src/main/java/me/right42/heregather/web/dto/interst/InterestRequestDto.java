package me.right42.heregather.web.dto.interst;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InterestRequestDto {

	@NotNull
	private Long interestId;

	@NotBlank
	private String name;

}
