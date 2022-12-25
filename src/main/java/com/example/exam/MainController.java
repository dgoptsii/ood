package com.example.exam;

import com.example.exam.dto.InvoiceDTO;
import com.example.exam.dto.UserSubscriptionDTO;
import com.example.exam.entity.Invoice;
import com.example.exam.entity.UserSubscription;
import com.example.exam.service.BlockingService;
import com.example.exam.service.InvoiceService;
import com.example.exam.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

/*
 головний контролер, точка входу в API
 */
@Controller
public class MainController {

    @Autowired
    private InvoiceService paymentService;

    @Autowired
    private BlockingService blockingService;

    @Autowired
    private SubscriptionService subscriptionService;


    /**
     * Користувач обирає тариф, період оплати (місяць, рік), автоматична/ручна оплата, вводить дані банк.картки (для автоматичної оплати)
    */
    // POST - викликається коли користувач створює підписку на оплату
    // приймає в DTO всю необіхдну інформацію для стоврення підписки на оплату
    public UserSubscription createSubscription(UserSubscriptionDTO userSubscriptionInfo){
        return subscriptionService.createSubscription(userSubscriptionInfo);

    }

    /**
     * Для кожного користувача в залежності від обраного періоду оплати у відповідний день місяця/року формується рахунок згідно із обраним тарифом
      При автоматичній оплаті відбувається спроба списання коштів із картки через платіжний сервіс
     */
    // POST - викликається ззовні коли приходить час створити рахунок на оплату за конкретною підпискою
    // викликає метод створення рахунку, повертає дані про створений рахунок
    // метод створення рахунку спробує провести оплату якщо вона автоматична і надасть полю payed значення true якщо вона була успішна
    // якщо оплата автоматична і рахунок не сплачено -  це дає інформацію назовні про потребу викликати повторну спробу оплати
      /*
    Припускаємо що цей метод викликається з-зовні через певний проміжок часу. Альтернативно його можна було б реалізувати за допомогою Scheduled task
     */
    public Invoice createInvoice (InvoiceDTO invoiceInfo){
        return paymentService.createInvoiceForSubscription(invoiceInfo);
    }

    // GET - знайти інформацію про конкретний рахунок на оплату за підпискою
    // буде використовуватись ззовні щоб перевірити чи конкретний рахунок було сплачено, чи має він автоматичну оплату
    // якщо оплата автоматична і рахунок не сплачено -  це дає інформацію назовні про потребу викликати повторну спробу оплати
    public Invoice getInvoiceInfo(Integer invoiceId){
        return paymentService.getInvoiceInfo(invoiceId);
    }

    /**
     * При невдачі автоматичної оплати відбувається декілька повторних спроб із інтервалом в день
     */
    // PUT - виклик повторної спроби автоматичної оплати рахунку
    /*
    Припускаємо що цей метод викликається з-зовні через певний проміжок часу. Альтернативно його можна було б реалізувати за допомогою Scheduled task
     */
    public Invoice callInvoicePayment(Integer invoiceId){
        return paymentService.callInvoicePayment(invoiceId);
    }



    //PUT - виклик перевірки чи хтось зі списку користувачів не має заборгованості більше ніж на дозволену кількість платежів в певний період часу
    /* блокує користувачів які мають таку заборгованість, повертає список id заблокованих користувачів
    Тут я припускаю що ця перевірка має викликатись з-зовні. Якщо вона має працювати з-середини - її просто можна задати за допомогою Scheduled task
    тоді перевірка робитиметься автоматично через певний період і блокуватиме користувачів з заборгованістю
    */
    public List<Integer> checkUsers(List<Integer> userIds, Date startDate, Date endDate){
        return blockingService.checkUserAndBlock(userIds,  startDate,  endDate);
    }




}
