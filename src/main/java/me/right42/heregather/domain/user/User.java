package me.right42.heregather.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.right42.heregather.domain.common.BaseEntity;
import me.right42.heregather.domain.user.type.UserStatus;
import me.right42.heregather.domain.user.type.UserType;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String userSnsId;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	private String accessToken;

	private String refreshToken;

	private String userName;

	private String profileImageLink;

	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
}
