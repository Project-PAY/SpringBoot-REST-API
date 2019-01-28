package com.pay.as.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@ToString(exclude = {"paymentDomainList"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @Column(length = 8, insertable = false)
    private String available;

    @Column(length = 32, nullable = false, unique = true)
    private String identify;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 32, nullable = false)
    private String name;

    private Long capital;

    private Long income;

    @Column(insertable = false)
    private Long outcome;

    private Long fixedIncome;

    private String cycleIncome;

    @OneToMany(mappedBy = "user")
    private List<PaymentDomain> paymentDomainList;

}
