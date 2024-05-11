package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_question")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "wrongAnswers")
    private List<String> wrongAnswers;

    @Column(name = "correctAnswer")
    private String correctAnswer;

    // associate with BodyPart entity
    @JoinColumn(name = "bodyPart")
    @ManyToOne
    private BodyPart bodyPart;
}
