package com.anv.tool;


import com.anv.dto.FeeReceiptResponse;
import com.anv.service.FeeReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FeeTool {

    private final FeeReceiptService feeReceiptService;

    @Tool(description = "Get pending fee")
    public Double getPendingFee(Long studentId) {

        return feeReceiptService.getPendingFee(studentId);
    }

    @Tool(description = "Get payment history")
    public List<FeeReceiptResponse> paymentHistory(Long studentId) {

        return feeReceiptService.getPaymentHistory(studentId);
    }

    @Tool(description = "Get latest fee receipt")
    public FeeReceiptResponse latestReceipt(Long studentId) {

        return feeReceiptService.getLatestReceipt(studentId);
    }

}
