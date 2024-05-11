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

import groovyjarjarantlr4.v4.codegen.model.SrcOp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;

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
        //Access current user
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = userDAORepository.findByUsername(user.getUsername()).get();
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
        //Access current user
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = userDAORepository.findByUsername(user.getUsername()).get();
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
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = userDAORepository.findByUsername(user.getUsername()).get();
        model.addAttribute("userEnter", userDAO);

        Optional<MainPart> mainPartOpt = MPRepository.findById(mainPartId);
        Optional<BodyPart> bodyPartOpt = BPRepository.findById(bodyPartId);

        MainPart mainPart = mainPartOpt.get();
        BodyPart bodyPart = bodyPartOpt.get();

        //Shuffle the questions
        List<Question> questions = bodyPart.getQuestions();
        Collections.shuffle(questions);
        questions = questions.subList(0, 3);

        //Shuffle the answers
        List<List<String>> questionsStrings = new ArrayList<>(); 
        for (Question question : questions) {
            List<String> strings = question.getWrongAnswers();
            strings.add(question.getCorrectAnswer());
            Collections.shuffle(strings);
            questionsStrings.add(strings);    
        }
        
        model.addAttribute("mainPart", mainPart);
        model.addAttribute("bodyPart", bodyPart);
        model.addAttribute("questions", questions);
        model.addAttribute("questionsStrings", questionsStrings);
        return "quizz";
    }

    @PostMapping("/bodypart/{mainPartId}/quizzes/{bodyPartId}/submit-quiz")
    public String submitQuiz(@PathVariable long mainPartId, @PathVariable long bodyPartId, @RequestParam("answers") String[] answers) {
        
        // Process submitted quiz data
        for (int i = 0; i < answers.length; i++) {
            String answer = answers[i];
            // Process each question ID and its corresponding answer
            System.out.println(", Answer: " + answer);
        }
        // Redirect to a result page or return a view name
        return "redirect:/bodypart/"+  mainPartId + "/quizzes";
    }

}
