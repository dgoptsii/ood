package com.example.exam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

// сутність user, містить інформацію про користувача
@Entity
@Data
public class User {
    @Id
    Integer id;
    String email;
    // true якщо заблоковано
    boolean blocked;

    List<UserSubscription> subscriptions;


}
