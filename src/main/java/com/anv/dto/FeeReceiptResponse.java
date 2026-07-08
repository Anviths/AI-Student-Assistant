package com.anv.dto;

import com.anv.entity.PaymentMode;
import com.anv.entity.PaymentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FeeReceiptResponse {

    private String receiptNumber;
    private String studentId;
    private String studentName;
    private Double amount;
    private LocalDate paymentDate;
    private PaymentMode paymentMode;
    private String transactionId;
    private PaymentStatus paymentStatus;
    private Double remainingFee;

}
