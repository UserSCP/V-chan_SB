package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserLoginController {
	@GetMapping("/login")
    public String showLoginPage(Model m) {
	     m.addAttribute("content", "auth/login");
	     m.addAttribute("title", "Log In"); 
        return "index";  
    }
}
