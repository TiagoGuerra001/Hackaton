package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.service.annotation.PatchExchange;

import com.example.demo.config.UserDataModel;
import com.example.demo.entity.MainPart;
import com.example.demo.entity.UserDAO;
import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.UserDAORepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class WebPageController {

    @Autowired
    private MainPartRepository mainPartRepository;

    @Autowired
    private UserDAORepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

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

    @PostMapping("/shop/{id}")
    public String buyItem(@PathVariable long id) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = userRepository.findByUsername(user.getUsername()).get();
        int userCoins = userDAO.getCoins();
        var item = itemRepository.findById(id).get();
        int itemPrice = item.getPrice();
        if (userCoins >= itemPrice) {
            userDAO.setCoins(userCoins - itemPrice);
           var isto = userDAO.getItems();
            isto.add(item);
            userDAO.setItems(isto);
            userRepository.save(userDAO);
        }

                
        return "home";
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

   
    @GetMapping("/shop")
    public String shop(Model model) {
        //current user
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = userRepository.findByUsername(user.getUsername()).get();
        
        model.addAttribute("userEnter", userDAO);
        model.addAttribute("shopItems", itemRepository.findAll());
        
        return "shop";
    }

}
