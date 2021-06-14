package me.right42.heregather.web.user.login;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import org.apache.catalina.util.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import me.right42.heregather.web.user.login.dto.NaverAuthorizeResponse;
import me.right42.heregather.web.user.login.dto.NaverTokenResponse;

@Controller
@PropertySource("classpath:/naver-api.properties")
public class NaverLoginController {

	private static final String REDIRECT_URI = "http://localhost:8080/naver-callback";

	@Value("${naver.login.clientId}")
	private String clientId;

	@Value("${naver.login.secret}")
	private String clientSecret;

	private String authorizeApiURI = "https://nid.naver.com/oauth2.0/authorize?response_type=code";

	private String tokenApiURI = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";

	@GetMapping("/login")
	public String login(Model model){
		model.addAttribute("authorizeApiURI", createAuthorizeApiURI());
		return "login";
	}

	@GetMapping("/naver-callback")
	public String naverCallback(@ModelAttribute NaverAuthorizeResponse response, Model model){
		RestTemplate restTemplate = new RestTemplate();
		NaverTokenResponse tokenRes = restTemplate.getForObject(
			createTokenApiURI(response.getCode(), response.getState()), NaverTokenResponse.class);

		model.addAttribute("token", tokenRes);
		return "register";
	}

	private String createAuthorizeApiURI() {
		authorizeApiURI += "&client_id=" + clientId;
		authorizeApiURI += "&redirect_uri=" + redirectUriEncode();
		authorizeApiURI += "&state=" + createState();
		return authorizeApiURI;
	}

	private String createTokenApiURI(String code, String state){
		tokenApiURI += "client_id=" + clientId;
		tokenApiURI += "&client_secret=" + clientSecret;
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
