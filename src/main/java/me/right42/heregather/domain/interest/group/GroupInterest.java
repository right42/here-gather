package me.right42.heregather.domain.interest.group;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
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
import me.right42.heregather.domain.group.Group;
import me.right42.heregather.domain.interest.Interest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GroupInterest extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_interest_id")
	private Long id;

	@ManyToOne(fetch = LAZY, optional = false)
	@JoinColumn(name = "interest_id")
	private Interest interest;

	@ManyToOne(fetch = LAZY, optional = false)
	@JoinColumn(name = "group_id")
	private Group group;

	private Integer priority;
}
