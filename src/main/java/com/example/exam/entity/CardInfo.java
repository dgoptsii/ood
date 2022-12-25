package com.example.exam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

// сутність CardInfo, інформація про картку конкретного користувача
/*
 на мою думку варто окремо зберігати такі дані як сутність в БД, тоді в майбутньому є можливість для користувача занести інформацію
 про декілька свої карток і пізніше обирати яку прив'язати до підписки на оплату (за таким принципом працює Amazon)
 */
@Entity
@Data
public class CardInfo {
    @Id
    Integer id;

    Integer userId;
    Integer number;
    Integer code;
}
