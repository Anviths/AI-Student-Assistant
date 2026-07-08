package com.anv.service;

import com.anv.dto.FeeReceiptResponse;

import java.util.List;

public interface FeeReceiptService {

    FeeReceiptResponse saveReceipt(FeeReceiptResponse response);

    FeeReceiptResponse getReceipt(String receiptNumber);

    List<FeeReceiptResponse> getAllReceipts();

    FeeReceiptResponse updateReceipt(String receiptNumber, FeeReceiptResponse response);

    void deleteReceipt(String receiptNumber);

}
