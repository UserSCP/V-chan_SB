package com.reichsacht.v_chan.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reichsacht.v_chan.dto.UserRegisterDTO;
import com.reichsacht.v_chan.model.Role;
import com.reichsacht.v_chan.service.EmailVerificationService;
import com.reichsacht.v_chan.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class UserRegisterController {
	private UserService userService;
	private final EmailVerificationService emailVerificationService;

	public UserRegisterController(UserService userService, EmailVerificationService emailVerificationService) {
		super();
		this.userService = userService;
		this.emailVerificationService = emailVerificationService;
	}

	@ModelAttribute("user")
	public UserRegisterDTO returnNewUserRegister() {
		return new UserRegisterDTO();
	}

	@GetMapping
	public String showFromRegister(Model m) {
		m.addAttribute("content", "auth/register");
		m.addAttribute("title", "Register");
		return "index";
	}

	@PostMapping
	public String confirmationAccount(@ModelAttribute("user") UserRegisterDTO userRegisterDTO, HttpSession session,
	        Model m) {
	    String verificationCode = emailVerificationService.generatedVerificationCode();
	    emailVerificationService.sendVerificationEmail(userRegisterDTO.getEmail(), verificationCode);
	    session.setAttribute("verificationCode", verificationCode);
	    session.setAttribute("email", userRegisterDTO.getEmail());
	    session.setAttribute("username", userRegisterDTO.getUsername()); // Guardar el nombre de usuario
	    session.setAttribute("password", userRegisterDTO.getPassword()); // Guardar la contraseña
	    
	    m.addAttribute("content", "auth/confirmEmail");
	    m.addAttribute("title", "Email Confirmation");
	    return "index";
	}

	@PostMapping("/verify-email")
	public String verifyEmail(@RequestParam String verificationCode, @RequestParam String email, Model m,
	        HttpSession session) {
	    String generatedCode = (String) session.getAttribute("verificationCode");
	    if (verificationCode.equals(generatedCode)) {
	        String username = (String) session.getAttribute("username");
	        String password = (String) session.getAttribute("password");
	        String email1 = (String) session.getAttribute("email");

		    LocalDate highDate = null;

	        
	        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(username, email1, password,Role.USER,"default1.png",LocalDate.now(),highDate);
	        userService.guardar(userRegisterDTO); // Llamar al servicio para guardar el usuario
	        
	        session.removeAttribute("verificationCode");
	        session.removeAttribute("username");
	        session.removeAttribute("password");
	        session.removeAttribute("email");
	        
	        return "redirect:/register?exito";
	    } else {
	        m.addAttribute("content", "home");
	        m.addAttribute("title", "Home");
	        return "index";
	    }
	}
}
