package com.reichsacht.v_chan.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reichsacht.v_chan.model.Account_type;
import com.reichsacht.v_chan.model.Role;
import com.reichsacht.v_chan.model.User;
import com.reichsacht.v_chan.service.UserServices;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/users")
@Controller
public class UserController {
	 @Autowired
	 private UserServices service;
	 @GetMapping("/")
	 public String getAllUsers(Model m, HttpServletRequest request) {
		 m.addAttribute("users", service.getAllUsers());
	     m.addAttribute("content", "users/userTable");
	     m.addAttribute("title", "User Table"); 
	        m.addAttribute("requestURI", request.getRequestURI());
		 return "index";
	 }
	 @GetMapping("/delete/{id}")
	 public String deleteUser(@PathVariable("id") Long id) {
		 service.deleteUser(id);
	     return "redirect:/users/";
	 }
	 @GetMapping("/create")
	 public String showUserForm(Model m) {
		 m.addAttribute("user", new User());
		 m.addAttribute("roleUser", Role.values());
		 m.addAttribute("typeUser", Account_type.values());
	     m.addAttribute("content", "users/userCreate");
	     m.addAttribute("title", "Form User"); 
		 return "index";
	 }
	 @PostMapping("/save")
	 public String saveUser(@ModelAttribute User user) {
		 service.saveUser(user);
		 return "redirect:/users/";
	 }
	 @GetMapping("/edit/{id}")
	 public String editUser(@PathVariable("id") Long id, Model m) {
	     Optional<User> user = service.getUserById(id);
	     if (user.isPresent()) {
	         m.addAttribute("user", user.get());
	         m.addAttribute("roles", Role.values());
	         m.addAttribute("types", Account_type.values());
	         m.addAttribute("content", "users/userEdit");
	         m.addAttribute("title", "Form User"); 
	         return "index";
	     } else {
	         m.addAttribute("error", "Usuario no encontrado");
	         return "redirect:/users/";
	     }
	 }

	 @PostMapping("/update/{id}")
	 public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
	     service.updateUser( user,id);
	     return "redirect:/users/";
	 }


}
