package me.right42.heregather.domain.user;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String userId;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	private String accessToken;

	private String refreshToken;

	private String userName;

	private String profileImageLink;

	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
}
