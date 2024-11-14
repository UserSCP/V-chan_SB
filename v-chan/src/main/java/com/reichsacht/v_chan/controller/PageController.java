package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class PageController {

	@GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("content", "home/home"); 
        model.addAttribute("title", "Home"); 
        return "index";  
    }
	@GetMapping("/settings")
	public String showSettingsPage(Model m, HttpServletRequest request) {
		m.addAttribute("content", "home/settings");
        m.addAttribute("title", "Settings"); 
        m.addAttribute("requestURI", request.getRequestURI());
		return "index";
	}
	@GetMapping("/test")
	public String showTestPage(Model m , HttpServletRequest request) {
		m.addAttribute("content", "home/test");
        m.addAttribute("title", "Test Zone"); 
        m.addAttribute("requestURI", request.getRequestURI());

		return "index";
	}
	@GetMapping("/art")
	public String showArtPage(Model m , HttpServletRequest request) {
		m.addAttribute("content", "home/art");
        m.addAttribute("title", "Art"); 
        m.addAttribute("requestURI", request.getRequestURI());
		return "index";
	}
	@GetMapping("/blocked")
	public String showBlockedPage(Model m) {
		m.addAttribute("content", "auth/blocked");
		m.addAttribute("title", "Blocked Page");
		return "index";
	}

}
