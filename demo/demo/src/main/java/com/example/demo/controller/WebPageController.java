package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MainPart;
import com.example.demo.repository.MainPartRepository;

@Controller
public class WebPageController {

    @Autowired
    private MainPartRepository mainPartRepository;

    @GetMapping("/")
    public String index(Model model) {

        List<MainPart> mainParts = mainPartRepository.findAll();
        model.addAttribute("mainParts", mainParts);

        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String getMethodName() {
        return "dashboard";
    }
        

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
