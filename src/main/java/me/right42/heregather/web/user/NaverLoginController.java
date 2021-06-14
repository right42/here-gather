package me.right42.heregather.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import me.right42.heregather.service.sns.NaverApiService;
import me.right42.heregather.web.dto.sns.NaverAuthorize;
import me.right42.heregather.web.dto.sns.NaverTokenResponse;

@Controller
@RequiredArgsConstructor
public class NaverLoginController {

	private final NaverApiService naverApiService;

	@GetMapping("/login")
	public String login(Model model){
		model.addAttribute("authorizeApiURI", naverApiService.createAuthorizeApiURI());
		return "login";
	}

	@GetMapping("/naver-callback")
	public String naverCallback(@ModelAttribute NaverAuthorize response, Model model){
		RestTemplate restTemplate = new RestTemplate();

		String tokenApiURI = naverApiService.createTokenApiURI(response.getCode(), response.getState());
		NaverTokenResponse tokenRes = restTemplate.getForObject(tokenApiURI, NaverTokenResponse.class);

		model.addAttribute("token", tokenRes);
		return "register";
	}

}
