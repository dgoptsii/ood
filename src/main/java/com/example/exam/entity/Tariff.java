package com.example.exam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

// сутність Tariff, містить інформацію про тариф
@Entity
@Data
public class Tariff {

    @Id
    Integer id;

    String name;
    Integer price;

}
