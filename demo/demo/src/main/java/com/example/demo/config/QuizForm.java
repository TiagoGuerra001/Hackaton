package com.example.demo.config;

public class QuizForm {
    private Long questionId0;
    private Long questionId1;
    private Long questionId2;

    private String answer0;
    private String answer1;
    private String answer2;

    // getters and setters
    public QuizForm(String answer0,String answer1,String answer2, Long questionId0, Long questionId1, Long questionId2){
        this.answer0 = answer0;
        this.answer1 = answer1;
        this.answer2 = answer2;

        this.questionId0 = questionId0;
        this.questionId1 = questionId1;
        this.questionId2 = questionId2;
    }
    public String getAnswer0(){
        return answer0;
    }
    public String getAnswer1(){
        return answer1;
    }
    public String getAnswer2(){
        return answer2;
    }

    public void setAnswer0(String answer){
        answer0 =  answer;
    }
    public void setAnswer1(String answer){
        answer2 = answer;
    }
    public void setAnswer2(String answer){
        answer2 = answer;
    }

    // Getter and setter for questionId0
    public Long getQuestionId0() {
        return questionId0;
    }

    public void setQuestionId0(Long questionId0) {
        this.questionId0 = questionId0;
    }

    // Getter and setter for questionId1
    public Long getQuestionId1() {
        return questionId1;
    }

    public void setQuestionId1(Long questionId1) {
        this.questionId1 = questionId1;
    }

    // Getter and setter for questionId2
    public Long getQuestionId2() {
        return questionId2;
    }

    public void setQuestionId2(Long questionId2) {
        this.questionId2 = questionId2;
    }

}
