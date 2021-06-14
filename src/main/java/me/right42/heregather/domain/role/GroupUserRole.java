package me.right42.heregather.domain.role;

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
import me.right42.heregather.domain.common.BaseEntity;
import me.right42.heregather.domain.group.GroupUser;
import me.right42.heregather.domain.user.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GroupUserRole extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "group_user_role_id")
	private Long id;

	private String name;

	private Integer level;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "group_user_id")
	private GroupUser groupUser;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "role_id")
	private Role role;
}

