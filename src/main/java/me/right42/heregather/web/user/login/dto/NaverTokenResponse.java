package me.right42.heregather.web.user.login.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NaverTokenResponse {

	private String accessToken;

	private String refreshToken;

	private String tokenType;

	private int expiredIn;
}
