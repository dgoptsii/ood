package com.example.exam.repository;

import com.example.exam.entity.CardInfo;
import com.example.exam.entity.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// репозиторій для роботи з базою даних для сутності Invoice
// в даному випадку на прикладі MongoRepository
@Repository
public interface InvoiceRepository  extends MongoRepository<Invoice, String> {
     Invoice findById(Integer id);
}
