package me.right42.heregather.service.sns;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import org.apache.catalina.util.URLEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.web.dto.sns.NaverApiProperties;

@Service
@RequiredArgsConstructor
public class NaverApiService {
	
	private static final String REDIRECT_URI = "http://localhost:8080/naver-callback";

	private static final String AUTHORIZE_API_URI = "https://nid.naver.com/oauth2.0/authorize?response_type=code";

	private static final String TOKEN_API_URI = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	
	private final NaverApiProperties properties;

	public String createAuthorizeApiURI() {
		String authorizeApiURI = AUTHORIZE_API_URI;
		authorizeApiURI += "&client_id=" + properties.getClientId();
		authorizeApiURI += "&redirect_uri=" + redirectUriEncode();
		authorizeApiURI += "&state=" + createState();
		return authorizeApiURI;
	}

	public String createTokenApiURI(String code, String state){
		String tokenApiURI = TOKEN_API_URI;
		tokenApiURI += "client_id=" + properties.getClientId();
		tokenApiURI += "&client_secret=" + properties.getClientSecret();
		tokenApiURI += "&redirect_uri=" + redirectUriEncode();
		tokenApiURI += "&code=" + code;
		tokenApiURI += "&state=" + state;
		return tokenApiURI;
	}

	private String redirectUriEncode() {
		return new URLEncoder().encode(REDIRECT_URI, StandardCharsets.UTF_8);
	}

	private String createState(){
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString();
	}

}
