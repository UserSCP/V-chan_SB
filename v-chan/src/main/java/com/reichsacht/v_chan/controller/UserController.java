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

import com.reichsacht.v_chan.model.User;
import com.reichsacht.v_chan.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {
	 @Autowired
	 private UserService service;
	 @GetMapping("/")
	 public String getAllUsers(Model m) {
		 m.addAttribute("users", service.getAllUsers());
		 return "userTable";
	 }
	 @GetMapping("/delete/{id}")
	 public String deleteUser(@PathVariable("id") Long id) {
		 service.deleteUser(id);
	     return "redirect:/users/";
	 }
	 @GetMapping("/create")
	 public String showUserForm(Model m) {
		 m.addAttribute("user", new User());
		 return "userCreate";
	 }
	 @PostMapping("/save")
	 public String saveUser(@ModelAttribute User user) {
		 service.saveUser(user);
		 return "redirect:/users/";
	 }
	 @GetMapping("/edit/{id}")
	 public String editUser(@PathVariable("id") Long id, Model m) {
	     Optional<User> userOptional = service.getUserById(id);
	     if (userOptional.isPresent()) {
	         m.addAttribute("user", userOptional.get());
	         return "userEdit";
	     } else {
	         m.addAttribute("error", "Usuario no encontrado");
	         return "redirect:/users/"; 
	     }
	 }
	 @PostMapping("/update/{id}")
	 public String updateUser(@PathVariable Long id,@ModelAttribute("user")User user) {
		 service.updateUser(id,user);
		 return "redirect:/users/";
	 }
}
