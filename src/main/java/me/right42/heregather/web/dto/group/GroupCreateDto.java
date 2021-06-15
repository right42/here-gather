package me.right42.heregather.web.dto.group;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import me.right42.heregather.domain.group.Group;

@Data
public class GroupCreateDto {

	@NotBlank
	private String groupName;

	@Max(10) @Min(1)
	private int maximumPeople;

	@NotBlank
	private String description;

	@NotNull
	private List<GroupRegionIdDto> groupRegions;

	@NotNull
	private List<GroupInterestIdDto> groupInterests;

	public Group toGroup(){
		return Group.builder()
				.name(this.groupName)
				.maximumPeople(this.maximumPeople)
				.description(this.description)
				.build();
	}

}
