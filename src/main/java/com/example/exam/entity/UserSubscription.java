package com.example.exam.entity;

import com.example.exam.constants.Period;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.List;

/*
 сутність UserSubscription, містить дані про підписку на оплату
 */
@Entity
@Data
public class UserSubscription {
    @Id
    Integer id;

    // id User
    Integer userId;
    // null якщо не обрано, інакше - id Tariff
    Integer tariffId;

    // початок дії підписки
    Date startDate;

    // кінець дії підписки
    Date endDate;

    // місяць або рік
    Period period;

    //true якщо автоматична оплата
    boolean automatePayment;

    // null якщо не встановлено автоматичну оплату
    Integer cardInfoId;

    // список платежів за даною підпискою
    List<Invoice> payments;

}
