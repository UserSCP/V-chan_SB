package com.reichsacht.v_chan.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.reichsacht.v_chan.model.Account_type;
import com.reichsacht.v_chan.model.User;
import com.reichsacht.v_chan.repository.UserRepository;
import com.reichsacht.v_chan.service.UserServices;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserServices userService;
    @Autowired
	private UserRepository repo;
    @PostMapping("/update-photo")
    public String updateProfilePhoto(@RequestParam("profilePhoto") MultipartFile file, Principal principal, RedirectAttributes redirectAttributes,Model m) {
        String username = principal.getName();
        Optional<User> user = userService.findByUsername(username);  // Debes tener un método que busque al usuario por su nombre de usuario
        Long userId = user.get().getId();
        
        try {
            String fileName = storeFile(file,userId);
            userService.updateProfilePhoto(username, fileName); 
            redirectAttributes.addFlashAttribute("showModal", true);
            return "redirect:/profile/me"; 
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Hubo un problema al subir la foto.");
            return "redirect:/profile/me";  
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/profile/me";
        }
    }
    private String storeFile(MultipartFile file, Long userId) throws IOException {
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path userDirectory = Paths.get("src/main/resources/static/images/users/" + userId);
        if (!Files.exists(userDirectory)) {
            Files.createDirectories(userDirectory);
            System.out.println("Directorio creado para el usuario " + userId);
        }
        Path path = userDirectory.resolve(fileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        if (Files.exists(path)) {
            System.out.println("Archivo guardado correctamente: " + path.toString());
        } else {
            System.out.println("Hubo un problema al guardar el archivo.");
        }
        Path markerFile = userDirectory.resolve("marker.txt");
        Files.createFile(markerFile);
        Files.delete(markerFile);  // Elimina el archivo de marcador después de crear la carpeta

        return fileName;  
    }

	@GetMapping("/me")
	public String showMyProfilePage(Model m) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> userOptional = repo.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            m.addAttribute("email", user.getEmail());
            m.addAttribute("profileFoto", user.getProfile_photo());
            m.addAttribute("id",user.getId());
            if(user.getAccount_type()==Account_type.PUBLIC) {
                m.addAttribute("type","Cuenta Publica");
            }else {
                m.addAttribute("type","Cuenta Privada");

            }

        }
		m.addAttribute("content", "profile/me");
		m.addAttribute("title", "My Profile");
		return "index";
	}}
