package me.right42.heregather.service.meeting;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.domain.meeting.Meeting;
import me.right42.heregather.domain.meeting.MeetingJoin;
import me.right42.heregather.domain.meeting.repository.MeetingJoinRepositroy;
import me.right42.heregather.domain.meeting.type.MeetingJoinStatus;
import me.right42.heregather.domain.user.User;

@Service
@RequiredArgsConstructor
public class MeetingJoinService {

	private final MeetingJoinRepositroy meetingJoinRepositroy;

	public void join(Meeting meeting, User user) {
		MeetingJoin meetingJoin = MeetingJoin.builder()
			.user(user)
			.meeting(meeting)
			.status(MeetingJoinStatus.JOIN)
			.build();

		meetingJoinRepositroy.save(meetingJoin);
	}
}
