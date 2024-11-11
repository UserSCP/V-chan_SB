package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class UserLoginController {
	@GetMapping("/login")
    public String showLoginPage(Model m) {
	     m.addAttribute("content", "login");
	     m.addAttribute("title", "Log In"); 
        return "index";  // Redirige a la página de login (login.html)
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";  // Redirige a login después de cerrar sesión
    }
}
