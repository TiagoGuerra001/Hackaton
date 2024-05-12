package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_MainPart")
public class MainPart {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    // connection with BodyPart entity
    // a main part can have multiple body parts
    @Column(name = "bodyparts")
    @OneToMany(mappedBy = "mainPart")
    private List<BodyPart> bodyParts;

    @Column(name = "info" )
    @Lob
    private String info;

}
