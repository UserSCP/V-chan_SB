package com.reichsacht.v_chan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.reichsacht.v_chan.model.User;
import com.reichsacht.v_chan.repository.UserRepository;
import com.reichsacht.v_chan.service.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class PasswordController {

    @Autowired
    private UserServices userService;
    @Autowired
	private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpServletRequest request, HttpServletResponse response,
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

            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                model.addAttribute("message", "La contraseña actual es incorrecta.");
            	model.addAttribute("content", "profile/settings");
        		model.addAttribute("title", "my profile settings");
                return "index";
            }

            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("message", "Las contraseñas no coinciden.");
            	model.addAttribute("content", "profile/settings");
        		model.addAttribute("title", "my profile settings");
                return "index";
            }

            user.setPassword(passwordEncoder.encode(newPassword));  // Cifrado de la nueva contraseña
            userService.updateUser(user, user.getId());

            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
                HttpSession testSession = request.getSession(false);
                if (testSession == null) {
                    System.out.print("Sesión cerrada correctamente");
                } else {
                    System.out.print("Error: La sesión sigue activa");
                }
            } else {
                System.out.print("No hay sesión activa para cerrar");
            }


            SecurityContextHolder.clearContext();
        }

        return "redirect:/login?passwordChanged";
    }

}
