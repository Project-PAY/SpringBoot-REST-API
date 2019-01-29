package com.pay.as.user;

import com.pay.as.domain.UserDomain;
import com.pay.as.repository.PaymentRepository;
import com.pay.as.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    public void 로그인() {
        UserDomain userDomain = userRepository.findByIdentifyAndPasswordAndAvailable("test", "testPassword", "able");

        System.out.println(userDomain.toString());
    }

}
