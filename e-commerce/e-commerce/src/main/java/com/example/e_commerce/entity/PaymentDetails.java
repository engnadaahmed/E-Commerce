package com.example.e_commerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class PaymentDetails {

    private String paymentMethod;
    private String status;
    private String paymentId;
    private String razerpayPaymentLinkId;
    private String razerpayPaymentLinkReferenceId;
    private String razerpayPaymentLinkStatus;
    private String razerpayPaymentId;
    public  PaymentDetails(){


    }
}
