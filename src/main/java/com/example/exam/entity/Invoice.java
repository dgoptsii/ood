package com.example.exam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

// сутність Invoice, містить інформацію про про оплату за конкретною підпискою


@Entity
@Data
public class Invoice {
  @Id
  Integer id;
  // дата створення рахунку на оплату за конкретною підпискою
  Date invoiceDate;
  // null,отримує значення в день успішної оплати
  Date invoicePaymentDate;
  // id підписки
  Integer userSubscriptionId;

  // true якщо за цією підпискою стоїть автоматична оплата
  boolean automaticPayment;
  // true - якщо сплачено
  boolean payed;

  Integer paymentAttempts;

  Integer price;

}
