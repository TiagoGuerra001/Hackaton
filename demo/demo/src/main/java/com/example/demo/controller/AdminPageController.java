package com.example.demo.controller;

import com.example.demo.entity.UserDAO;
import com.example.demo.entity.MainPart;
import com.example.demo.config.UserDataModel;
import com.example.demo.entity.BodyPart;
import com.example.demo.entity.Question;
import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.BodyPartRepository;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.UserDAORepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AdminPageController {
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private MainPartRepository MPRepository;
    @Autowired
    private BodyPartRepository BPRepository;
    @Autowired
    private QuestionsRepository QRepository;

    @GetMapping({ "/user/list", "/admin/user" })
    public String listUser() {
        return "user-list";
    }

    @GetMapping("/user/list2")
    public String listUser2(Model model) {
        model.addAttribute("users", userDAORepository.findAll());
        return "user-list2";
    }

    @GetMapping("/user/add")
    public String showFormUser(Model model) {
        model.addAttribute("user", new UserDAO());
        return "user-add";
    }

    @PostMapping("/user/add")
    public String addUser(Model model, UserDAO user) {
        userDAORepository.save(user);
        model.addAttribute("user", new UserDAO());
        return "user-list";
    }

    @GetMapping("/bodypart/{id}")
    public String getBodyPart(Model model, @PathVariable long id) {

        UserDataModel user = (UserDataModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = new UserDAO();
        userDAO.setUsername(user.getUsername());
        userDAO.setEmail(user.getEmail());
        userDAO.setPassword(user.getPassword());
        model.addAttribute("userEnter", userDAO);

        Optional<MainPart> mainPartopt = MPRepository.findById(id);
        var mainPart = mainPartopt.get();
        if (mainPart != null) {
            model.addAttribute("mainPart", mainPart);
            model.addAttribute("bodyParts", mainPart.getBodyParts());
        }
        return "bodypart";
    }

    @GetMapping("/bodypart/{id}/quizzes")
    public String getQuizzes(Model model, @PathVariable long id) {
        UserDataModel user = (UserDataModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = new UserDAO();
        userDAO.setUsername(user.getUsername());
        userDAO.setEmail(user.getEmail());
        userDAO.setPassword(user.getPassword());
        model.addAttribute("userEnter", userDAO);

        Optional<MainPart> mainPartOpt = MPRepository.findById(id);
        if (mainPartOpt.isPresent()) {
            MainPart mainPart = mainPartOpt.get();
            List<BodyPart> bodyParts = mainPart.getBodyParts();

            Map<Long, List<Question>> bodyPartQuestionsMap = new HashMap<>();

            for (BodyPart bodyPart : bodyParts) {
                List<Question> questions = bodyPart.getQuestions();
                bodyPartQuestionsMap.put(bodyPart.getId(), questions);
            }

            model.addAttribute("mainPart", mainPart);
            model.addAttribute("bodyParts", bodyParts);
            model.addAttribute("bodyPartQuestionsMap", bodyPartQuestionsMap);
        }
        return "bodypart-quizzes";
    }

    @GetMapping("/bodypart/{mainPartId}/quizzes/{bodyPartId}")
    public String getQuiz(Model model, @PathVariable long mainPartId, @PathVariable long bodyPartId) {
        Optional<MainPart> mainPartOpt = MPRepository.findById(mainPartId);
        Optional<BodyPart> bodyPartOpt = BPRepository.findById(bodyPartId);
        if (mainPartOpt.isPresent()) {
            MainPart mainPart = mainPartOpt.get();
            BodyPart bodyPart = bodyPartOpt.get();

            List<Question> questions = bodyPart.getQuestions(); // Assuming there's a method to retrieve questions for a
                                                                // body part

            model.addAttribute("mainPart", mainPart);
            model.addAttribute("bodyPart", bodyPart);
            model.addAttribute("questions", questions);
        }
        return "quiz";
    }
}
