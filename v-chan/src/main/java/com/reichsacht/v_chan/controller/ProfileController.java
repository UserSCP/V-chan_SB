package com.reichsacht.v_chan.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/profile") // Define que todas las rutas en este controlador comienzan con "/profile".
public class ProfileController {

    @Autowired
    private UserServices userService; // Servicio para realizar operaciones relacionadas con el usuario.
    
    @Autowired
	private UserRepository repo; // Repositorio para interactuar con la base de datos de usuarios.

    /**
     * Maneja la subida y actualización de la foto de perfil.
     * @param file - Archivo de imagen subido por el usuario.
     * @param principal - Objeto que representa al usuario autenticado.
     * @param redirectAttributes - Usado para enviar mensajes flash a la vista.
     * @param m - Modelo para pasar datos a la vista.
     * @return Redirige a la página de configuración del perfil del usuario.
     */
    @PostMapping("/update-photo")
    public String updateProfilePhoto(@RequestParam("profilePhoto") MultipartFile file, Principal principal, RedirectAttributes redirectAttributes, Model m) {
        String username = principal.getName(); // Obtiene el nombre de usuario del usuario autenticado.
        Optional<User> user = userService.findByUsername(username); // Busca al usuario en la base de datos.
        Long userId = user.get().getId(); // Obtiene el ID del usuario.

        try {
            // Almacena el archivo y actualiza la foto de perfil en la base de datos.
            String fileName = storeFile(file, userId);
            userService.updateProfilePhoto(username, fileName); 
            redirectAttributes.addFlashAttribute("showModal", true); // Envía un mensaje para mostrar un modal de éxito.
            return "redirect:/profile/me/settings"; 
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Hubo un problema al subir la foto."); // Mensaje de error en caso de fallo.
            return "redirect:/profile/me/settings";  
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage()); // Otro tipo de error manejado.
            return "redirect:/profile/me/settings";
        }
    }

    /**
     * Método privado para almacenar el archivo subido en una carpeta específica del usuario.
     * @param file - Archivo subido.
     * @param userId - ID del usuario (para crear una carpeta única por usuario).
     * @return El nombre del archivo almacenado.
     * @throws IOException Si ocurre un error durante el almacenamiento.
     */
    private String storeFile(MultipartFile file, Long userId) throws IOException {
        // Genera un nombre único para el archivo usando UUID y la extensión original del archivo.
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path userDirectory = Paths.get("src/main/resources/static/images/users/" + userId); // Carpeta de usuario.

        if (!Files.exists(userDirectory)) {
            Files.createDirectories(userDirectory); // Crea el directorio si no existe.
            System.out.println("Directorio creado para el usuario " + userId);
        }

        Path path = userDirectory.resolve(fileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING); // Sobrescribe el archivo existente si ya existe.

        if (Files.exists(path)) {
            System.out.println("Archivo guardado correctamente: " + path.toString());
        } else {
            System.out.println("Hubo un problema al guardar el archivo.");
        }

        // Archivo marcador (marker) para verificar que la carpeta se creó correctamente.
        Path markerFile = userDirectory.resolve("marker.txt");
        Files.createFile(markerFile);
        Files.delete(markerFile);  // Elimina el archivo de marcador inmediatamente.

        return fileName;  
    }

    /**
     * Muestra la página principal del perfil del usuario autenticado.
     * @param m - Modelo para pasar datos a la vista.
     * @return La vista "index" con los datos del perfil.
     */
	@GetMapping("/me")
	public String showMyProfilePage(Model m) {
		// Obtiene el usuario autenticado desde el contexto de seguridad.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        // Busca al usuario en la base de datos y pasa su información al modelo.
        Optional<User> userOptional = repo.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            m.addAttribute("email", user.getEmail());
            m.addAttribute("profileFoto", user.getProfile_photo());
            m.addAttribute("id", user.getId());
            if (user.getAccount_type() == Account_type.PUBLIC) {
                m.addAttribute("type", "Cuenta Pública");
            } else {
                m.addAttribute("type", "Cuenta Privada");
            }
        }

        m.addAttribute("content", "profile/me"); // Especifica el contenido para cargar dinámicamente en la vista.
        m.addAttribute("title", "My Profile"); // Título de la página.
        return "index";
	}

    /**
     * Muestra la página de configuración del perfil del usuario autenticado.
     * @param m - Modelo para pasar datos a la vista.
     * @return La vista "index" con los datos de configuración del perfil.
     */
	@GetMapping("/me/settings")
	public String showConfigurationpage(Model m) {
		// Similar a "showMyProfilePage", obtiene la información del usuario autenticado

