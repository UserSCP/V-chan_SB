package com.reichsacht.v_chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String showChatPage(Model model) {
        model.addAttribute("content", "chat/community"); 
        model.addAttribute("title", "Community");
        return "index"; 
    }
}