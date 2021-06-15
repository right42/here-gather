package me.right42.heregather.service.meeting;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.group.repository.GroupRepository;
import me.right42.heregather.domain.meeting.Meeting;
import me.right42.heregather.domain.meeting.repository.MeetingRepository;
import me.right42.heregather.domain.region.Region;
import me.right42.heregather.domain.region.repository.RegionRepository;
import me.right42.heregather.domain.user.User;
import me.right42.heregather.domain.user.repository.UserRepository;
import me.right42.heregather.exception.MeetingNotFoundException;
import me.right42.heregather.exception.NotGroupManagerException;
import me.right42.heregather.service.group.GroupUserService;
import me.right42.heregather.web.dto.UserDto;
import me.right42.heregather.web.dto.meeting.MeetingCreateDto;
import me.right42.heregather.web.dto.meeting.MeetingJoinDto;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingService {

	private final MeetingRepository meetingRepository;

	private final RegionRepository regionRepository;

	private final GroupRepository groupRepository;

	private final MeetingJoinService meetingJoinService;

	private final GroupUserService groupUserService;

	private final UserRepository userRepository;

	public Long meetingCreate(MeetingCreateDto dto, UserDto userDto){
		Long groupId = dto.getGroupId();
		Long regionId = dto.getRegionId();

		Region region = regionRepository.findById(regionId).orElseThrow(EntityNotFoundException::new);
		Group group = groupRepository.findById(groupId).orElseThrow(EntityNotFoundException::new);
		User user = userRepository.findById(userDto.getUserNo()).orElseThrow(EntityNotFoundException::new);

		if(!groupUserService.isGroupManager(group, user)) {
			throw new NotGroupManagerException();
		}

		Meeting meeting = Meeting.builder()
			.title(dto.getTitle())
			.group(group)
			.region(region)
			.maximumUser(dto.getMaximumUser())
			.startTime(LocalDateTime.parse(dto.getStartTime()))
			.endTime(LocalDateTime.parse(dto.getEndTime()))
			.cost(dto.getCost())
			.build();

		Meeting savedMeeting = meetingRepository.save(meeting);

		meetingJoinService.join(meeting, user);
		return savedMeeting.getId();
	}

	public Long meetingJoin(MeetingJoinDto dto){
		Meeting meeting = meetingRepository.findById(dto.getMeetingId()).orElseThrow(MeetingNotFoundException::new);

		UserDto userDto = dto.getUserDto();
		User user = userRepository.findById(userDto.getUserNo()).orElseThrow(EntityNotFoundException::new);

		meetingJoinService.join(meeting, user);
		return meeting.getId();
	}
}
