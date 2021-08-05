package com.springtutorials.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springtutorials.model.UserModel;
import com.springtutorials.service.IUserService;

@Controller(value = "admin")
public class HomeController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminHome(Model model, Principal principal) {
		if (principal != null) {
			model.addAttribute("name", userService.findOneByUsername(principal.getName()).getFullname());
		}
		return "admin/home";
	}
	
	@RequestMapping(value = "/admin-info", method = RequestMethod.GET)
	@ResponseBody
	public String adminProductList(Model model) {
		String str = "";
		for (UserModel user : userService.find()) {
			str += "\n{"
					+ "\n\tName: " + user.getFullname()
					+ "\n\tUsername: " + user.getUsername()
					+ "\n\tPassword: " + user.getPassword()
					+ "\n}";
		}
		return str;
	}
	
}
