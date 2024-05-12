package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

public class QuizForm {
    
    /*
     * Quizz form structure that receives the values of form inputs related to a
     * quizz, composed of three questions.
     */

    private List<Long> questionIds = new ArrayList<>();
    private List<String> answers = new ArrayList<>();

    public QuizForm(String answer0,String answer1,String answer2, Long questionId0, Long questionId1, Long questionId2){

        answers.add(answer0);
        answers.add(answer1);
        answers.add(answer2);

        questionIds.add(questionId0);
        questionIds.add(questionId1);
        questionIds.add(questionId2);
    }

    //Answers getter and setter
    public List<String> getAnswers(){
        return this.answers;
    }
    public void setAnswers(List<String> answer) {
        this.answers = answer;
    }

    //question ids getter and setter
    public List<Long> getQuestionIds(){
        return this.questionIds;
    }
    public void setQuestionIds(List<Long> ids) {
        this.questionIds = ids;
    }

}
