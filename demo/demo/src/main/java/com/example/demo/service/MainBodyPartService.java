package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.BodyPartRepository;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.UserDAORepository;
import com.example.demo.entity.MainPart;
import com.example.demo.entity.BodyPart;
import com.example.demo.entity.Question;
import com.example.demo.entity.UserDAO;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MainBodyPartService {

    @Autowired
    private MainPartRepository mainBodyPartRepository;
    @Autowired
    private BodyPartRepository bodyPartRepository;
    @Autowired
    private QuestionsRepository questionRepository;
    @Autowired
    private UserDAORepository userDAORepository;
    // get passwordencoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void populateDatabase() {
        // create user
        UserDAO user = new UserDAO();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("1@1");
        userDAORepository.save(user);
        // Create a MainPart
        MainPart brain = new MainPart();
        brain.setName("Brain");
        brain.setInfo("Stuff about the brain.");

        // Create a BodyPart
        BodyPart cortex = new BodyPart();
        cortex.setName("Cortex");
        cortex.setInfo("Stuff about the cortex.");
        cortex.setColor("#00FF00");
        cortex.setMainPart(brain);

        BodyPart FrontLobe = new BodyPart();
        FrontLobe.setName("Front Lobe");
        FrontLobe.setInfo("Stuff about the Front Lobe.");
        FrontLobe.setColor("#FF0000");
        FrontLobe.setMainPart(brain);

        BodyPart Cerebellum = new BodyPart();
        Cerebellum.setName("Cerebellum");
        Cerebellum.setInfo("Stuff about the Cerebellum.");
        FrontLobe.setColor("#0000FF");
        Cerebellum.setMainPart(brain);

        // Create a Question
        List<String> questionsStrings = new ArrayList<>();
        questionsStrings.add("wrong");
        questionsStrings.add("wong");
        questionsStrings.add("wrong");
        Question question1 = new Question();
        question1.setDifficulty(1);
        question1.setWrongAnswers(questionsStrings);
        question1.setCorrectAnswer("right");

        Question question2 = new Question();
        question2.setDifficulty(1);
        question2.setWrongAnswers(questionsStrings);
        question2.setCorrectAnswer("right");

        Question question3 = new Question();
        question3.setDifficulty(1);
        question3.setWrongAnswers(questionsStrings);
        question3.setCorrectAnswer("right");

        // save mainpart
        List<BodyPart> bodyParts = new ArrayList<>();
        bodyParts.add(cortex);
        bodyParts.add(FrontLobe);
        bodyParts.add(Cerebellum);
        brain.setBodyParts(bodyParts);
        mainBodyPartRepository.save(brain);

        // save bodypart
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        cortex.setQuestions(questions);
        bodyPartRepository.save(cortex);
        bodyPartRepository.save(FrontLobe);
        bodyPartRepository.save(Cerebellum);

        // save questions
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

    }
}