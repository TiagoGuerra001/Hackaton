package com.example.demo.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_BodyPart")
public class BodyPart {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "info")
    @Lob
    private String info;

    @Column(name = "color")
    private String color;

    @JoinColumn(name = "mainPart")
    @ManyToOne
    private MainPart mainPart;
    
    @Column(name = "questions")
    @OneToMany(mappedBy = "bodyPart")
    private List<Question> questions; 
}
