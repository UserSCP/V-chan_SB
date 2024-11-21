package com.reichsacht.v_chan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reichsacht.v_chan.model.User;
import com.reichsacht.v_chan.repository.UserRepository;
import com.reichsacht.v_chan.service.UserService;
import com.reichsacht.v_chan.service.UserServices;

@Controller
public class PasswordController {

    @Autowired
    private UserServices userService; // Servicio para manejar la lógica de usuarios
    @Autowired
	private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder; // Codificador de contraseñas

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> currentUser = repo.findByUsername(username);
        if (currentUser.isPresent()) {
            User user = currentUser.get();
        // Validar que la contraseña actual sea correcta
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("menssage", "La contraseña actual es incorrecta.");
    		model.addAttribute("content", "profile/settings");
    		model.addAttribute("title", "my profile settings");
    		return "index";        }

        // Validar que las contraseñas nuevas coincidan
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("menssage", "Las contraseñas no coinciden.");
            model.addAttribute("content", "profile/settings");
    		model.addAttribute("title", "my profile settings");
    		return "index";         }

        // Actualizar la contraseña
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.updateUser(user,user.getId());
        }
        model.addAttribute("menssage", "La contraseña se ha actualizado correctamente.");
        model.addAttribute("content", "profile/settings");
		model.addAttribute("title", "my profile settings");
		return "index";     }
}
