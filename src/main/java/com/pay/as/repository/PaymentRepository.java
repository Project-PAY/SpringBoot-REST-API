package com.pay.as.repository;

import com.pay.as.domain.PaymentDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDomain, Long> {

    List<PaymentDomain> findAllByUser_Index(Long index);

}
