package com.pay.as.payment;

import com.pay.as.domain.PaymentDomain;
import com.pay.as.repository.AuthRepository;
import com.pay.as.repository.PaymentRepository;
import com.pay.as.repository.UserRepository;
import com.pay.as.service.AuthService;
import com.pay.as.service.PaymentService;
import com.pay.as.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    PaymentService paymentService;

    @Test
    @Transactional
    @Rollback(false)
    public void addIncome() {
        paymentService.addIncome(22L, "기타", 100000L);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void addOutcome() {
        paymentService.addOutcome(22L, "식비", 8000L, "체크카드");
    }


    @Test
    public void gets() {
        List<PaymentDomain> paymentDomainList = paymentService.gets(22L);

        for (PaymentDomain paymentDomain : paymentDomainList) {
            System.out.println(paymentDomain.toString());
        }
    }

}
