package com.popovich.myspace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
public class Master {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;

    @OneToMany(mappedBy = "master")
    @JsonBackReference
    private List<Planet> planets = new ArrayList<>();

    public Master(String name, Integer age){
        this.name = name;
        this.age = age;
    }
}


