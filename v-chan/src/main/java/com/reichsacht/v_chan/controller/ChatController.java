package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ChatController {

    @GetMapping("chat/community")
    public String showChatPage(Model model,HttpServletRequest request) {
        model.addAttribute("content", "chat/community"); 
        model.addAttribute("title", "Community");
        model.addAttribute("requestURI", request.getRequestURI());

        return "index"; 
    }
}