package com.example.exam.dto;

import lombok.Data;

import java.util.Date;

/*
DTO що містить необхідну інформацію для створення рахунку для конкретної підписки на оплату
 */
@Data
public class InvoiceDTO {

    // дата створення рахунку на оплату за конкретною підпискою
    Date invoiceDate;
    // id підписки
    Integer userSubscriptionId;
    Integer price;
}
