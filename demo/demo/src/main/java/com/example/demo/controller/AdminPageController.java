package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.entity.MainPart;
import com.example.demo.entity.BodyPart;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.BodyPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional; 

@Controller

public class AdminPageController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MainPartRepository MPRepository;
    @Autowired
    private BodyPartRepository BPRepository;

    @GetMapping({ "/user/list", "/admin/user" })
    public String listUser() {
        return "user-list";
    }

    @GetMapping("/user/list2")
    public String listUser2(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-list2";
    }

    @GetMapping("/user/add")
    public String showFormUser(Model model) {
        model.addAttribute("user", new User());
        return "user-add";
    }

    @PostMapping("/user/add")
    public String addUser(Model model, User user) {
        userRepository.save(user);
        model.addAttribute("user", new User());
        return "user-list";
    }

    @GetMapping("/bodypart/{id}")
    public String getBodyPart(Model model, @PathVariable long id)  {    
        Optional<MainPart> mainPartopt = MPRepository.findById(id);
        var mainPart = mainPartopt.get();
        if (mainPart != null) {
            model.addAttribute("mainPart", mainPart);
            model.addAttribute("bodyParts", mainPart.getBodyParts());
        }
        return "bodypart";
    }

    @GetMapping("/bodypart/{id}/quizzes")
    public String getQuizzes(Model model, @PathVariable long id)  {    
        Optional<MainPart> mainPartopt = MPRepository.findById(id);
        var mainPart = mainPartopt.get();
        if (mainPart != null) {
            model.addAttribute("mainPart", mainPart);
            model.addAttribute("bodyParts", mainPart.getBodyParts());
        }
        return "bodypart-quizzes";
    }

}
