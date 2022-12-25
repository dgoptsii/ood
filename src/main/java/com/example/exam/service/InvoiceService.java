package com.example.exam.service;

import com.example.exam.dto.InvoiceDTO;
import com.example.exam.dto.UserSubscriptionDTO;
import com.example.exam.entity.CardInfo;
import com.example.exam.entity.Invoice;
import com.example.exam.entity.UserSubscription;
import com.example.exam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {


    // максимально довзолена кількість несплачених рахунків
    private static Integer MAX_NUMBER_UNPAID = 3;

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private CardInfoRepository cardInfoRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;


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

    // метод для створення рахунку за підпискою на оплату
    public Invoice createInvoiceForSubscription(InvoiceDTO invoiceInfo) {
        Invoice invoice = new Invoice();
            /*
       1. перевірка що користувач з таким userId, підписка з таким userSubscriptionId існують в базі даних, інакше throw new Exception()
       2. перевірити що всі дані в DTO надійшли коректні, інакше throw new Exception()
       3. перенести всі поля з invoiceInfo в invoice
       4. дістати інформацію про підписку з бази даних userSubscriptionRepository.findById(userSubscriptionId)
       тоді матимемо інформацію про користувача, чи автоматична оплата і тд
       5. якщо оплата автоматична спробувати провести оплату за даними картки знайденої в бд cardInfoRepository.findById(cardInfoId)
       якщо успішна - встановаити в invoice payed = true,дату оплати в invoicePaymentDate
       при спробі оплати - збільшується paymentAttempts в invoice
       6. зберігти invoice в базі даних - invoiceRepository.save(invoice)
       */


        /*
        можна також додати логіку перевірки чи в списку платежів цієї підписки немає несплачений платежів, що перевищували б кількість довзолених неспласених платежів
       (MAX_NUMBER_UNPAID) за певний період разом з новим рахунком що тільки що було створено, якщо його не вдалось сплатити
       і тоді одразу блокувати користувача user.setBlocked(true)

       користувача можна дістати з бази даних userRepository.findById(userId) і відповідно зберігти всі зміни
         */

        return invoiceRepository.save(invoice);
    }


    // дістати інформацію про певний рахунок
    public Invoice getInvoiceInfo(Integer invoiceId) {
        // якщо такого рахунку немає в бд - throw new Exception()
        return invoiceRepository.findById(invoiceId);
    }

    // потворна спроба спалтити рахунок з автоматичною оплатою
    public Invoice callInvoicePayment(Integer invoiceId) {

        /*
        1. перевіряє що такий платіж з invoiceId існує в бд інакше throw new Exception()
        2. дістати інформацію про підписку за якою відпувається оплата за userSubscriptionId з userSubscriptionRepository
        3. якщо оплата автоматична спробувати провести оплату за даними картки знайденої в бд cardInfoRepository.findById(cardInfoId)
       якщо успішна - встановаити в invoice payed = true, дату оплати в invoicePaymentDate
       при спробі оплати - збільшується paymentAttempts в invoice
        4. зберігти invoice в базі даних - invoiceRepository.save(invoice)
         */

        /*
        можна також додати логіку зупинки спроб повторити платіж після певної їх кількості (paymentAttempts) і повідомлення користувача про це, наприклад
        якщо зберігати його пошту
         */
        return null;
    }


    // перевірка чи хтось зі списку користувачів не має заборгованості більше ніж на дозволену кількість платежів в певний період часу
    // блокує користувачів які мають таку заборгованість
    // повертає список id заблокованих користувачів
    // тут я припускаю що ця перевірка має викликатись з-зовні, якщо вона має працювати з-середини - її просто можна задати за допомогою Scheduled task
    // тоді перевірка робитиметься автоматично через певний період і блокуватиме користувачів з заборгованістю
    public List<Integer> checkUserAndBlock(List<Integer> userIds, Date startDate, Date endDate) {
        List<Integer> blockedUsers = new ArrayList<>();
        /* 1. перевіряє що всі користувачі з айді зі списку userIds існують в бд - інкаше throw new Exception()
        2. дістає з userSubscriptionRepository підписки з таким userId, перевряє що в писку платежів немає такої кількості неспалчених за
        період від startDate до endDate що перевищував би MAX_NUMBER_UNPAID, інакше user.setBlocked(true)
        3. зберігає зміни про користувача в userRepository
        4. додає айді заблокованих користувачів в blockedUsers
        */
        return blockedUsers;
    }



}
