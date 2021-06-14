package me.right42.heregather.web.dto.sns;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@PropertySource("classpath:/naver-api.properties")
@Getter
public class NaverApiProperties {

	@Value("${naver.login.clientId}")
	private String clientId;

	@Value("${naver.login.secret}")
	private String clientSecret;

}
