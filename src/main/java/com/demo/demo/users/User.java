package com.demo.demo.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private LocalDate dob;

    @Transient // specifies theres no need for it in the db since it can be calculated from dob
    private int age;

    public int getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public String getDob(){
        return this.dob.toString();
    }
}
