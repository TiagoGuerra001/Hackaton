package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.BodyPartRepository;

import com.example.demo.entity.MainPart;
import com.example.demo.entity.BodyPart;
import com.example.demo.entity.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MainBodyPartService {

    @Autowired
    private MainPartRepository mainBodyPartRepository;
    @Autowired
    private BodyPartRepository bodyPartRepository;

    public void populateDatabase() {
        // Create a MainPart
        MainPart brain = new MainPart();
        brain.setName("Brain");
        brain.setInfo("Stuff about the brain.");

        // Create a BodyPart
        BodyPart cortex = new BodyPart();
        cortex.setName("Cortex");
        cortex.setInfo("Stuff about the cortex.");
        cortex.setInfo("#00FF00");
        cortex.setMainPart(brain);

        BodyPart FrontLobe = new BodyPart();
        FrontLobe.setName("Front Lobe");
        FrontLobe.setInfo("Stuff about the Front Lobe.");
        FrontLobe.setInfo("#FF0000");
        FrontLobe.setMainPart(brain);

        BodyPart Cerebellum = new BodyPart();
        Cerebellum.setName("Cerebellum");
        Cerebellum.setInfo("Stuff about the Cerebellum.");
        FrontLobe.setInfo("#0000FF");
        Cerebellum.setMainPart(brain);

        // Create a Question
        List<String> questions = new ArrayList<>();
        questions.add("wrong");
        questions.add("wong");
        questions.add("wrong");
        Question question1 = new Question();
        question1.setDifficulty(1);
        question1.setWrongAnswers(questions);
        question1.setCorrectAnswer("right");

        Question question2 = new Question();
        question2.setDifficulty(1);
        question2.setWrongAnswers(questions);
        question2.setCorrectAnswer("right");

        Question question3 = new Question();
        question3.setDifficulty(1);
        question3.setWrongAnswers(questions);
        question3.setCorrectAnswer("right");


        //save mainpart
        List<BodyPart> bodyParts = new ArrayList<>();
        bodyParts.add(cortex);
        bodyParts.add(FrontLobe);
        bodyParts.add(Cerebellum);
        brain.setBodyParts(bodyParts);
        mainBodyPartRepository.save(brain);

        //save bodypart
        
        bodyPartRepository.save(cortex);
        bodyPartRepository.save(FrontLobe);
        bodyPartRepository.save(Cerebellum);

        //save questions
        
        
    }
}