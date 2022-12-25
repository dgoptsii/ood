package com.example.exam.service;

import com.example.exam.dto.InvoiceDTO;
import com.example.exam.dto.UserSubscriptionDTO;
import com.example.exam.entity.Invoice;
import com.example.exam.entity.UserSubscription;
import com.example.exam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubscriptionService {


    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private CardInfoRepository cardInfoRepository;


    // метод для створення підписки на оплату для користувача
    public UserSubscription createSubscription(UserSubscriptionDTO userSubscriptionInfo) {
        // створюється підписка на оплату з відповідними параметрами тарифу
        UserSubscription userSubscription = new UserSubscription();
       /*
       1. перевірка що користувач з таким userId, тариф з таким tariffId існують в базі даних, інакше throw new Exception()
       2. перевірити що всі дані в DTO надійшли коректні, інакше throw new Exception()
       3. перенести всі поля з userSubscriptionInfo в userSubscription
       4. якщо оплата автоматична - створити новий CardInfo, зберігти його в базі даних -  cardInfoRepository.save(cardInfo), занести як поле в userSubscription
        (пояснення до цього рішення в класі CardInfo)
       5. зберігти userSubscription в базі даних - userSubscriptionRepository.save(userSubscription)
       */
        return userSubscriptionRepository.save(userSubscription);
    }




    /*
    тут можуть бути інші методи для редагування, видалення підписок
    */

}
