package com.dub.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	
	@Value("${gateway-path}")
	private String gatewayPath;
	
	@RequestMapping({"/", "/backHome", "/index"})
	public String home1(Model model) {
		 
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(auth);
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		model.addAttribute("gatewayPath", gatewayPath);
		 
		return "index";
	 }
	
	@RequestMapping("/logout")
	public String logout() {
	
		return "redirect:http://localhost:5555/dogs";
	}

}
