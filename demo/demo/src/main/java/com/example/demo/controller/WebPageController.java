package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.UserDataModel;
import com.example.demo.entity.MainPart;
import com.example.demo.entity.UserDAO;
import com.example.demo.repository.MainPartRepository;

@Controller
public class WebPageController {

    @Autowired
    private MainPartRepository mainPartRepository;

    @GetMapping("/")
    public String index(Model model) {

        List<MainPart> mainParts = mainPartRepository.findAll();
        model.addAttribute("mainParts", mainParts);

        // i want to get the authenticated user here using the securiry context
        UserDataModel user = (UserDataModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = new UserDAO();
        userDAO.setUsername(user.getUsername());
        userDAO.setEmail(user.getEmail());
        userDAO.setPassword(user.getPassword());
        model.addAttribute("userEnter", userDAO);
        for (MainPart mainPart : mainParts) {
            model.addAttribute(mainPart.getName(), mainPart.getInfo());
        }

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
