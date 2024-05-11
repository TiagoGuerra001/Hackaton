
package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_item")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

     @Column(name = "info")
    private String info;

    @Column(name = "price")
    private double price;

    @Column(name = "imageID")
    private String imageID;

}
