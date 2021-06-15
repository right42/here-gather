package me.right42.heregather.web.dto.group;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInterestIdDto {

	@NotNull
	private Long id;

}
