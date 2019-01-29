package com.pay.as.user;

import com.pay.as.domain.PaymentDomain;
import com.pay.as.domain.UserDomain;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class JoinTests {

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
    public void 회원가입() {
        UserDomain userDomain = UserDomain.builder()
                .identify("test")
                .password("testPassword")
                .name("김용현")
                .capital(100000L)
                .income(100000L)
                .fixedIncome(null)
                .cycleIncome(null)
                .build();

        userRepository.save(userDomain);

        PaymentDomain paymentDomain = PaymentDomain.builder()
                .user(userDomain)
                .kind("수입")
                .content("소지금")
                .price(100000L)
                .build();

        System.out.println(userDomain.toString());
        System.out.println();
        System.out.println(paymentDomain.toString());
    }

}
