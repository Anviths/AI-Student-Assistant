package com.anv.controller;

import com.anv.dto.FeeReceiptResponse;
import com.anv.service.FeeReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
public class FeeReceiptController {

    private final FeeReceiptService feeReceiptService;

    @PostMapping
    public FeeReceiptResponse save(@RequestBody FeeReceiptResponse response) {
        return feeReceiptService.saveReceipt(response);
    }

    @GetMapping("/{receiptNumber}")
    public FeeReceiptResponse get(@PathVariable String receiptNumber) {
        return feeReceiptService.getReceipt(receiptNumber);
    }

    @GetMapping
    public List<FeeReceiptResponse> getAll() {
        return feeReceiptService.getAllReceipts();
    }

    @PutMapping("/{receiptNumber}")
    public FeeReceiptResponse update(@PathVariable String receiptNumber,
                                     @RequestBody FeeReceiptResponse response) {
        return feeReceiptService.updateReceipt(receiptNumber, response);
    }

    @DeleteMapping("/{receiptNumber}")
    public void delete(@PathVariable String receiptNumber) {
        feeReceiptService.deleteReceipt(receiptNumber);
    }
}
