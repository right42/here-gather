package me.right42.heregather.service.group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.group.repository.GroupQueryRepository;
import me.right42.heregather.domain.group.repository.interest.GroupInterestQueryRepository;
import me.right42.heregather.domain.group.repository.region.GroupRegionQueryRepository;
import me.right42.heregather.domain.group.repository.user.GroupUserQueryRepository;
import me.right42.heregather.domain.group.type.JoinStatus;
import me.right42.heregather.web.dto.group.GroupResponseDto;
import me.right42.heregather.web.dto.group.GroupSearchDto;
import me.right42.heregather.web.dto.interst.InterestResponseDto;
import me.right42.heregather.web.dto.region.RegionResponseDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupQueryService {

	private final GroupQueryRepository queryRepository;

	private final GroupRegionQueryRepository groupRegionQueryRepository;

	private final GroupInterestQueryRepository groupInterestQueryRepository;

	private final GroupUserQueryRepository groupUserQueryRepository;

	public List<GroupResponseDto> findAll(GroupSearchDto groupSearchDto){
		List<Group> findGroups = queryRepository.findAll(groupSearchDto);

		return findGroups.stream()
			.map(findGroup -> {
				List<InterestResponseDto> groupInterests = groupInterestQueryRepository.findAllByGroupWithInterest(findGroup);
				List<RegionResponseDto> groupRegions = groupRegionQueryRepository.findAllByGroupWithRegion(findGroup);
				Long joinCount = groupUserQueryRepository.countByGroupIdAndJoinStatus(findGroup.getId(), JoinStatus.JOIN);

				return new GroupResponseDto(findGroup, groupInterests, groupRegions, joinCount);
			})
			.collect(Collectors.toUnmodifiableList());
	}

}
