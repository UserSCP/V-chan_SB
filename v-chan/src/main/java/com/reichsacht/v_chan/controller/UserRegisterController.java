package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reichsacht.v_chan.dto.UserRegisterDTO;
import com.reichsacht.v_chan.service.UserService;

@Controller
@RequestMapping("/register")
public class UserRegisterController {
	private UserService userService;

	public UserRegisterController(UserService userService) {
		super();
		this.userService = userService;
	}
	@ModelAttribute("user")
	public UserRegisterDTO returnNewUserRegister() {
		return new UserRegisterDTO();
	}
	@GetMapping
	public String showFromRegister(Model m) {
	     m.addAttribute("content", "register");
	     m.addAttribute("title", "Register"); 
		return "index";
	}
	@PostMapping
	public String registerAccountUser(@ModelAttribute("user") UserRegisterDTO userRegisterDTO) {
		userService.guardar(userRegisterDTO);
		return "redirect:/register?exito";
	}
}
