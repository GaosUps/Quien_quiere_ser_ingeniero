package io.github.gaosups.qqi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class HomeController {

	@GetMapping(headers = "Connection!=Upgrade")
	public String getIndex() {
		return "";
	}
}
