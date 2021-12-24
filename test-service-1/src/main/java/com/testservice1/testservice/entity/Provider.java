package com.testservice1.testservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test_Class_1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Test_Class_1_id;
    private String name;


}
