package com.springtutorials.controller.home;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springtutorials.service.IUserService;

@Controller(value = "web")
public class HomeController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = {"/web-home", "/"}, method = RequestMethod.GET)
	public String webHome(HttpServletRequest req, Principal principal) {
		if (principal != null) {
			req.getSession().setAttribute("name", userService.findOneByUsername(principal.getName()).getFullname());
		}
		return "home/home";
	}
	
}
