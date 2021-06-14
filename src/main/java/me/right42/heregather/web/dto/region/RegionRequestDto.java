package me.right42.heregather.web.dto.region;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegionRequestDto {

	@NotNull
	private Long regionId;

	@NotBlank
	private String name;

}
