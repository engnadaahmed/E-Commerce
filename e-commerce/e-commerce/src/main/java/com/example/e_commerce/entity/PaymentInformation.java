package com.example.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "payment_information")
public class PaymentInformation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "cardholder_name")
    private String cardholderName;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expriration_date")
    private LocalDate expirationDate;
    @Column(name = "cvv")
    private String cvv;
}
