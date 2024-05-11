package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.BodyPartRepository;
import com.example.demo.repository.QuestionsRepository;

import com.example.demo.entity.MainPart;
import com.example.demo.entity.BodyPart;
import com.example.demo.entity.Question;

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

    public void populateDatabase() {
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

        //save mainpart
        List<BodyPart> bodyParts = new ArrayList<>();
        bodyParts.add(cortex);
        bodyParts.add(FrontLobe);
        bodyParts.add(Cerebellum);
        brain.setBodyParts(bodyParts);
        mainBodyPartRepository.save(brain);

        //save bodypart
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        cortex.setQuestions(questions);
        bodyPartRepository.save(cortex);
        bodyPartRepository.save(FrontLobe);
        bodyPartRepository.save(Cerebellum);

        //save questions
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        
        // Create another MainPart Arm
        MainPart arm = new MainPart();
        arm.setName("Arm");
        arm.setInfo("Stuff about the arm.");

        // Create a BodyPart
        BodyPart bicep = new BodyPart();
        bicep.setName("Bicep");
        bicep.setInfo("Stuff about the bicep.");
        bicep.setColor("#00FF00");
        bicep.setMainPart(arm);

        BodyPart tricep = new BodyPart();
        tricep.setName("Tricep");
        tricep.setInfo("Stuff about the tricep.");
        tricep.setColor("#FF0000");

        BodyPart forearm = new BodyPart();
        forearm.setName("Forearm");
        forearm.setInfo("Stuff about the forearm.");
        forearm.setColor("#0000FF");

        // Create a Question
        List<String> questionsStrings2 = new ArrayList<>();
        questionsStrings2.add("wrong");
        questionsStrings2.add("wong");
        questionsStrings2.add("wrong");
        Question question4 = new Question();
        question4.setDifficulty(1);
        question4.setWrongAnswers(questionsStrings2);
        question4.setCorrectAnswer("right");

        Question question5 = new Question();
        question5.setDifficulty(1);
        question5.setWrongAnswers(questionsStrings2);
        question5.setCorrectAnswer("right");

        Question question6 = new Question();
        question6.setDifficulty(1);
        question6.setWrongAnswers(questionsStrings2);
        question6.setCorrectAnswer("right");

        //save mainpart
        List<BodyPart> bodyParts2 = new ArrayList<>();
        bodyParts2.add(bicep);
        bodyParts2.add(tricep);
        bodyParts2.add(forearm);
        arm.setBodyParts(bodyParts2);
        mainBodyPartRepository.save(arm);

        //save bodypart
        List<Question> questions2 = new ArrayList<>();
        questions2.add(question4);
        questions2.add(question5);
        questions2.add(question6);
        bicep.setQuestions(questions2);
        bodyPartRepository.save(bicep);
        bodyPartRepository.save(tricep);
        bodyPartRepository.save(forearm);

        //save questions
        questionRepository.save(question4);
        questionRepository.save(question5);
        questionRepository.save(question6);


    }
}