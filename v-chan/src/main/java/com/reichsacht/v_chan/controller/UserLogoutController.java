package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserLogoutController {
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Obtener la sesión actual si existe
	    if (session != null) {
	        session.invalidate();  // Invalidar la sesión manualmente
	    }
	    return "redirect:/login?logout";  // Redirigir al login
	}
}
