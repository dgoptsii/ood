package com.example.exam.dto;

import lombok.Data;

import java.util.Date;

/*
DTO що містить необхідну інформацію для створення підписки на оплату
 */
@Data
public class UserSubscriptionDTO {


    // id User
    Integer userId;
    // id Tariff
    Integer tariffId;

    //true якщо автоматична оплата
    boolean automatePayment;

    // інформація про картку, може бути null якщо не автоматична опалта
    Integer number;
    Integer code;
    Date date;
}
