package com.example.spring_boot_web_socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
///el controlador solo manda la vista
@Controller
public class ChatController {
	@GetMapping("/")
	public String showView() {
		return "index";
	}
}
