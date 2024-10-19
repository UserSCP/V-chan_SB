package com.personas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.personas.modal.personas;
import com.personas.repository.personasRepository;

import java.util.List;

@Controller
@RequestMapping("/personas")
public class personasController {

    @Autowired
    private personasRepository repo;

    @GetMapping("/buscar")
    public String mostrarFormulario() {
        return "formulario"; 
    }

    @GetMapping("/todas")
    public String buscarPorNombre( Model model) {
        try {
            List<personas> listaPersonas = repo.findAll();
            model.addAttribute("personas", listaPersonas);
            return "resultado"; // Redirige a la vista de resultados
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al buscar personas: " + e.getMessage());
            return "error"; // Redirige a la vista de error
        }
    }
}
