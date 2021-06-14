package me.right42.heregather.web.user.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverAuthorizeResponse {

	private String code;

	private String state;

}
