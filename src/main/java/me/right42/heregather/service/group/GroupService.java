package me.right42.heregather.service.group;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.group.repository.GroupInterestRepository;
import me.right42.heregather.domain.group.repository.GroupRegionRepository;
import me.right42.heregather.domain.group.repository.GroupRepository;
import me.right42.heregather.domain.interest.Interest;
import me.right42.heregather.domain.interest.group.GroupInterest;
import me.right42.heregather.domain.interest.repository.InterestRepository;
import me.right42.heregather.domain.region.GroupRegion;
import me.right42.heregather.domain.region.Region;
import me.right42.heregather.domain.region.repository.RegionRepository;
import me.right42.heregather.domain.user.User;
import me.right42.heregather.domain.user.repository.UserRepository;
import me.right42.heregather.web.dto.group.GroupCreateDto;

@Service
@RequiredArgsConstructor
public class GroupService {

	private final GroupRepository groupRepository;

	private final GroupRegionRepository groupRegionRepository;

	private final GroupInterestRepository groupInterestRepository;

	private final UserRepository userRepository;

	private final RegionRepository regionRepository;

	private final InterestRepository interestRepository;

	private final GroupUserService groupUserService;

	@Transactional
	public Long createGroup(GroupCreateDto dto){
		Group group = dto.toGroup();
		Group savedGroup = groupRepository.save(group);

		// 유저 받아오게 변경
		User user = userRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

		List<GroupRegion> groupRegions = createGroupRegions(dto, savedGroup);
		List<GroupInterest> groupInterests = createGroupInterests(dto, savedGroup);

		groupRegionRepository.saveAll(groupRegions);
		groupInterestRepository.saveAll(groupInterests);

		groupUserService.join(user, savedGroup);

		return group.getId();
	}

	private List<GroupRegion> createGroupRegions(GroupCreateDto dto, Group savedGroup) {
		return dto.getGroupRegions().stream()
			.map((groupRegionDto -> {
				Region region = regionRepository.findById(groupRegionDto.getRegionId())
					.orElseThrow(EntityNotFoundException::new);

				return GroupRegion.builder()
					.group(savedGroup)
					.region(region)
					.priority(1)
					.build();
			}))
			.collect(Collectors.toList());
	}

	private List<GroupInterest> createGroupInterests(GroupCreateDto dto, Group savedGroup) {
		return dto.getGroupInterests().stream()
			.map((groupInterestDto -> {
				Interest interest = interestRepository.findById(groupInterestDto.getId())
					.orElseThrow(EntityNotFoundException::new);

				return GroupInterest.builder()
					.group(savedGroup)
					.interest(interest)
					.priority(1)
					.build();
			}))
			.collect(Collectors.toList());
	}
}
