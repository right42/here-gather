package me.right42.heregather.web.dto.region;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionSearchDto {

	@Min(1)
	private int level;

	@NotBlank
	private String regionName;
}
