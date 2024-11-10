//package com.reichsacht.v_chan.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.reichsacht.v_chan.model.User;
//import com.reichsacht.v_chan.service.UserService;
//
//@Controller
//public class AuthController {
//	@Autowired 
//	private UserService service;
//	@GetMapping("/register")
//	public String showRegisterFrom(Model m) {
//		m.addAttribute("user",new User());
//	    m.addAttribute("content", "register");
//		return "index";
//	}
//	@PostMapping("/register")
//	public String registerUser(@ModelAttribute User user) {
//		service.save(user);
//		return "redirect:/login";
//	}
//	@GetMapping("/login")
//	public String showLoginForm(Model m) {
//	    m.addAttribute("content", "login");
//	    if (userIsAuthenticated()) {
//	        return "redirect:/home"; 
//	    }
//		return "index";
//		
//	}
//	public boolean userIsAuthenticated() {
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    if (authentication != null) {
//	        System.out.println("Authenticated user: " + authentication.getName());
//	    }
//	    return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
//	}
//
//}
