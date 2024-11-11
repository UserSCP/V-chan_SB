package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLogoutController {
	@GetMapping("/logout")
    public String showFormLogout(Model m) {
        return "redirect:/login?logout"; 
	}
}
