package com.popovich.myspace.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
public class Planet {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "master_id")
    private Master master;

    public Planet(String name) {
        this.name = name;
    }
}
