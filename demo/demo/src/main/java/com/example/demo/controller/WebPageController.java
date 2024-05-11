package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.UserDataModel;
import com.example.demo.entity.MainPart;
import com.example.demo.entity.UserDAO;
import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.UserDAORepository;

@Controller
public class WebPageController {

    @Autowired
    private MainPartRepository mainPartRepository;

    @Autowired
    private UserDAORepository userRepository;

    @GetMapping("/")
    public String index(Model model) {

        List<MainPart> mainParts = mainPartRepository.findAll();
        model.addAttribute("mainParts", mainParts);

        // i want to get the authenticated user here using the securiry context
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = userRepository.findByUsername(user.getUsername()).get();
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

    @GetMapping("/shop")
    public String shop() {
        return "shop";
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
