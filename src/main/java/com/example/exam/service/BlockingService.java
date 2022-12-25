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
public class BlockingService {


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



    /* перевірка чи хтось зі списку користувачів не має заборгованості більше ніж на дозволену кількість платежів в певний період часу
    блокує користувачів які мають таку заборгованість, повертає список id заблокованих користувачів
  Тут я припускаю що ця перевірка має викликатись з-зовні. Якщо вона має працювати з-середини - її просто можна задати за допомогою Scheduled task
  тоді перевірка робитиметься автоматично через певний період і блокуватиме користувачів з заборгованістю
  */
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
