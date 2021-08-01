package com.springtutorials.controller.home;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "web")
public class HomeController {

	@RequestMapping(value = {"/web-home", "/"}, method = RequestMethod.GET)
	public String webHome(Model model, Principal principal) {
		if (principal != null) {
			model.addAttribute("name", principal.getName());
		}
		return "home/home";
	}
	
}
