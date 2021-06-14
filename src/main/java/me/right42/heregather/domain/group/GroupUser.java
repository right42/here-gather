package me.right42.heregather.domain.group;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import me.right42.heregather.domain.group.type.JoinStatus;
import me.right42.heregather.domain.user.User;

/**
 * 모임의 참가한 유저
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GroupUser extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_user_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

	@Enumerated(EnumType.STRING)
	private JoinStatus joinStatus; // 가입상태

}
