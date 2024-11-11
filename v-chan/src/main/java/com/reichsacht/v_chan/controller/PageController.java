package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

	@GetMapping("/")
    public String showHomePage(Model model,HttpSession session) {
	    model.addAttribute("loginSuccessMessage", session.getAttribute("loginSuccessMessage"));
        model.addAttribute("content", "home"); 
        model.addAttribute("title", "Home"); 
        return "index";  
    }
	@GetMapping("/settings")
	public String showSettingsPage(Model m) {
		m.addAttribute("content", "settings");
        m.addAttribute("title", "Settings"); 
		return "index";
	}
	@GetMapping("/test")
	public String showTestPage(Model m) {
		m.addAttribute("content", "test");
        m.addAttribute("title", "Test Zone"); 
		return "index";
	}
	
}
