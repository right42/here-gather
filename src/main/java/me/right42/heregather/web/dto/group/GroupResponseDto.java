package me.right42.heregather.web.dto.group;

import java.util.List;

import lombok.Data;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.interest.group.GroupInterest;
import me.right42.heregather.domain.region.GroupRegion;
import me.right42.heregather.web.dto.interst.InterestResponseDto;
import me.right42.heregather.web.dto.region.RegionResponseDto;

@Data
public class GroupResponseDto {

	private Long groupId;

	private String groupName;

	private Integer maximumUser;

	private List<RegionResponseDto> regions;

	private List<InterestResponseDto> interests;

	private Long joinUserCount;

	public GroupResponseDto(Group findGroup, List<InterestResponseDto> groupInterests, List<RegionResponseDto> groupRegions,
		Long joinCount) {

		this.groupId = findGroup.getId();
		this.groupName = findGroup.getName();
		this.maximumUser = findGroup.getMaximumPeople();
		this.joinUserCount = joinCount;

		regions = groupRegions;
		interests = groupInterests;
	}
}
