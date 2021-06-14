package me.right42.heregather.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

	@GetMapping("/")
	public String index(){
		return "index";
	}

}
