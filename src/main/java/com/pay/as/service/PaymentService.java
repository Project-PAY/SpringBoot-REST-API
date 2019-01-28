package com.pay.as.service;

import com.pay.as.domain.PaymentDomain;
import com.pay.as.domain.UserDomain;
import com.pay.as.repository.PaymentRepository;
import com.pay.as.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;



    public void addIncome(UserDomain userDomain,
                          String content,
                          Long price) {
        userDomain.setCapital(userDomain.getCapital() + price);
        userDomain.setIncome(userDomain.getIncome() + price);

        paymentRepository.save(PaymentDomain.builder()
                .user(userRepository.save(userDomain))
                .content(content)
                .price(price)
                .build()
        );
    }

}
