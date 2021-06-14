package me.right42.heregather.domain.meeting;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.common.BaseEntity;
import me.right42.heregather.domain.meeting.type.MeetingJoinStatus;
import me.right42.heregather.domain.user.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MeetingJoin extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "meeting_join_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "meeting_id")
	private Meeting meeting;

	@Enumerated(EnumType.STRING)
	private MeetingJoinStatus status;
}
