package com.reichsacht.v_chan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.reichsacht.v_chan.service.UserService;

@Controller
public class UserController {
	 @Autowired
	 private UserService service;
	 @GetMapping("/users")
	 public String getAllUsers(Model m) {
		 m.addAttribute("users", service.getAllUsers());
		 return "userTable";
	 }
}
