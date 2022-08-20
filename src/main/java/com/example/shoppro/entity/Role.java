package com.example.shoppro.entity;


import org.hibernate.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;



    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(Integer id) {
        this.id = id;
    }


    public Role() {

    }




    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                "description='" + description + '\'' +
                '}';
    }
}
