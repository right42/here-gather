package me.right42.heregather.domain.interest.user;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.interest.Interest;
import me.right42.heregather.domain.user.User;

/**
 * 유저 관심사
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserInterest {

	@Id
	@GeneratedValue
	@Column(name = "user_interest_id")
	private Long id;

	@ManyToOne(fetch = LAZY, optional = false)
	@JoinColumn(name = "interest_id")
	private Interest interest;

	@ManyToOne(fetch = LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	private Integer priority;

}
