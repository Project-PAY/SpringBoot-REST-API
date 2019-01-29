package com.pay.as.service;

import com.pay.as.domain.PaymentDomain;
import com.pay.as.domain.UserDomain;
import com.pay.as.repository.PaymentRepository;
import com.pay.as.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;



    @Transactional
    public void join(String identify,
                     String password,
                     String name,
                     Long fixedIncome,
                     String cycleIncome,
                     Long salary) {

        try {
            paymentRepository.save(PaymentDomain.builder()
                    .user(
                            userRepository.save(UserDomain.builder()
                                    .identify(identify)
                                    .password(password)
                                    .name(name)
                                    .capital(salary)
                                    .income(salary)
                                    .fixedIncome(fixedIncome)
                                    .cycleIncome(cycleIncome)
                                    .build()
                            )
                    )
                    .kind("수입")
                    .content("소지금")
                    .price(salary)
                    .build()
            );
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



    public UserDomain login(String identify,
                            String password) {
        return userRepository.findByIdentifyAndPasswordAndAvailable(identify, password, "able");
    }

}
