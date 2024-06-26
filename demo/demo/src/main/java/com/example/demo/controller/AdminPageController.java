package com.example.demo.controller;

import com.example.demo.entity.UserDAO;
import com.example.demo.entity.MainPart;
import com.example.demo.config.QuizForm;
import com.example.demo.config.UserDataModel;
import com.example.demo.entity.BodyPart;
import com.example.demo.entity.Question;
import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.BodyPartRepository;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.UserDAORepository;

import groovyjarjarantlr4.v4.codegen.model.SrcOp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private QuestionsRepository QRepository;;

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
    public String getBodyPart(Model model, @PathVariable Long id) {
        //Access current user
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = userDAORepository.findByUsername(user.getUsername()).get();
        model.addAttribute("userEnter", userDAO);
        
        Optional<MainPart> mainPartopt = MPRepository.findById(id);
        var mainPart = mainPartopt.get();
        if (mainPart != null) {
            model.addAttribute("mainPart", mainPart);
            model.addAttribute("mainPartName", mainPart.getName().toLowerCase());
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
            //Main and Bodyparts
            MainPart mainPart = mainPartOpt.get();
            List<BodyPart> bodyParts = mainPart.getBodyParts();

            //List of body parts related to the user (represent the quizzes)
            List<BodyPart> userBodyParts = userDAO.getBodyParts();

            //Holds the states related to completion of quizzes
            List<String> quizStates = new ArrayList<>(bodyParts.size());

            // initialize with empty strings
            for (int i = 0; i < bodyParts.size(); i++) {
                quizStates.add("");
            }

            for (int i = 0; i < bodyParts.size(); i++) {
                if(userBodyParts.contains(bodyParts.get(i))){
                    //Done state
                    quizStates.add(i, "done");
                }
                else{
                    //Active state
                    quizStates.add(i, "active");
                    break;
                }
            }
            
            //list of margins for the path
            List<Long> margins = new ArrayList<>();
            List<Long> pattern = List.of(0L, 80L, 160L, 80L); // Define the repeating pattern

            int bodyPartsSize = bodyParts.size(); // Assuming you have a valid 'bodyParts' list

            for (int i = 0; i < bodyPartsSize; i++) {
                long margin = pattern.get(i % pattern.size()); // Get the corresponding value from the pattern
                margins.add(margin);
            }

            model.addAttribute("mainPart", mainPart);
            model.addAttribute("bodyParts", bodyParts);
            model.addAttribute("quizStates", quizStates);
            model.addAttribute("pathMargins", margins);
            
        }
        return "bodypart-quizzes";
    }

    @PostMapping("/open-chest")
    public ResponseEntity<String> openChest(@RequestBody String requestBody) {
        // Process the request and interact with your database
        // For demonstration purposes, let's just return a success message
        //TODO: add the money
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDAO userDAO = userDAORepository.findByUsername(user.getUsername()).get();

        //Adds the coins
        int userCoins = userDAO.getCoins();
        userDAO.setCoins(userCoins + 30);

        //Saves user
        userDAORepository.save(userDAO);

        return ResponseEntity.ok("Chest opened successfully!");
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
    public String submitQuiz(@PathVariable long mainPartId, @PathVariable long bodyPartId, QuizForm form) {
        
        //Cntr questions
        int cntrQuestions =  0;

        // Process submitted quiz data
        var quizForm = form;

        //Process questions
        List<Long> questionIds = quizForm.getQuestionIds();
        List<String> answers = quizForm.getAnswers();

        for (int i = 0; i < questionIds.size(); i++) {

            Optional<Question> questionOpt = QRepository.findById(questionIds.get(i));
            var question  = questionOpt.get();
            if (question != null) {
                String correctAnswer = question.getCorrectAnswer();
                //Checks if the answer is similar to the correct one
                if(correctAnswer.equals(answers.get(i))){
                    cntrQuestions ++;
                    System.out.println("correct question");
                }
                else{
                    System.out.println("failed question");
                }
            }
        }

        if(cntrQuestions == 3){
            //Change the completed quizzes by the user
            
            //Get user
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDAO userDAO = userDAORepository.findByUsername(user.getUsername()).get();

            //Get bodypart
            Optional<BodyPart> bodyPartOpt = BPRepository.findById(bodyPartId);
            BodyPart bodyPart = bodyPartOpt.get();

            if (userDAO != null && bodyPart != null) {
                //Update bodyparts
                List<BodyPart> userBodyParts = userDAO.getBodyParts();
                if(!userBodyParts.contains(bodyPart)){
                    userBodyParts.add(bodyPart);
                    userDAO.setBodyParts(userBodyParts);
                }

                //Update coins
                int coinsInc = 10;
                int userCoins = userDAO.getCoins();
                userDAO.setCoins(userCoins + coinsInc);

                //save
                userDAORepository.save(userDAO);
            }
        }
  
        // Redirect to a result page or return a view name
        return "redirect:/bodypart/"+  mainPartId + "/quizzes";
    }
}
