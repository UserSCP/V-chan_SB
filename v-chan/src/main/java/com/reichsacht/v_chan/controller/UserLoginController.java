package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserLoginController {
	@GetMapping("/login")
	public String showLoginPage(@RequestParam(value = "passwordChanged", required = false) String passwordChanged,
			Model m) {
		if (passwordChanged != null) {
			m.addAttribute("message", "Tu contraseña se ha actualizado. Por favor, inicia sesión.");
		}
		m.addAttribute("content", "auth/login");
		m.addAttribute("title", "Log In");

		return "index";
	}
}
