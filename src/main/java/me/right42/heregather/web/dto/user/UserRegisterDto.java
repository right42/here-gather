package me.right42.heregather.web.dto.user;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import me.right42.heregather.web.dto.interst.InterestRequestDto;
import me.right42.heregather.web.dto.region.RegionRequestDto;

@Data
public class UserRegisterDto {

	@NotBlank
	private String name;

	@Size(min = 1, max = 3)
	private List<RegionRequestDto> regions;

	@Size(min = 1, max = 5)
	private List<InterestRequestDto> interests;

}
