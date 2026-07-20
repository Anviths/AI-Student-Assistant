package com.anv.service.imp;

import com.anv.dao.FeeReceiptRepository;
import com.anv.dao.StudentRepository;
import com.anv.dto.FeeReceiptResponse;
import com.anv.entity.FeeReceipt;
import com.anv.entity.Student;
import com.anv.service.FeeReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeReceiptServiceImpl implements FeeReceiptService {

    private final FeeReceiptRepository feeReceiptRepository;
    private final StudentRepository studentRepository;

    @Override
    public FeeReceiptResponse saveReceipt(FeeReceiptResponse response) {

        Student student = studentRepository.findByStudentId(response.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        FeeReceipt receipt = new FeeReceipt();

        receipt.setReceiptNumber(response.getReceiptNumber());
        receipt.setStudent(student);
        receipt.setAmount(response.getAmount());
        receipt.setPaymentDate(response.getPaymentDate());
        receipt.setPaymentMode(response.getPaymentMode());
        receipt.setTransactionId(response.getTransactionId());
        receipt.setPaymentStatus(response.getPaymentStatus());

        return map(feeReceiptRepository.save(receipt));
    }

    @Override
    public FeeReceiptResponse getReceipt(String receiptNumber) {

        FeeReceipt receipt = feeReceiptRepository.findByReceiptNumber(receiptNumber)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));

        return map(receipt);
    }

    @Override
    public List<FeeReceiptResponse> getAllReceipts() {

        return feeReceiptRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public FeeReceiptResponse updateReceipt(String receiptNumber,
                                            FeeReceiptResponse response) {

        FeeReceipt receipt = feeReceiptRepository.findByReceiptNumber(receiptNumber)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));

        receipt.setAmount(response.getAmount());
        receipt.setPaymentDate(response.getPaymentDate());
        receipt.setPaymentMode(response.getPaymentMode());
        receipt.setTransactionId(response.getTransactionId());
        receipt.setPaymentStatus(response.getPaymentStatus());

        return map(feeReceiptRepository.save(receipt));
    }

    @Override
    public void deleteReceipt(String receiptNumber) {

        FeeReceipt receipt = feeReceiptRepository.findByReceiptNumber(receiptNumber)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));

        feeReceiptRepository.delete(receipt);
    }

    @Override
    public Double getPendingFee(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getTotalFee() - student.getFeePaid();
    }

    @Override
    public List<FeeReceiptResponse> getPaymentHistory(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return feeReceiptRepository.findByStudent(student)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public FeeReceiptResponse getLatestReceipt(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        FeeReceipt receipt = feeReceiptRepository
                .findFirstByStudentOrderByPaymentDateDesc(student)
                .orElseThrow(() -> new RuntimeException("No receipt found"));

        return map(receipt);
    }

    private FeeReceiptResponse map(FeeReceipt receipt) {

        FeeReceiptResponse response = new FeeReceiptResponse();

        response.setStudentId(receipt.getStudent().getStudentId());
        response.setReceiptNumber(receipt.getReceiptNumber());
        response.setStudentName(receipt.getStudent().getName());
        response.setAmount(receipt.getAmount());
        response.setPaymentDate(receipt.getPaymentDate());
        response.setPaymentMode(receipt.getPaymentMode());
        response.setTransactionId(receipt.getTransactionId());
        response.setPaymentStatus(receipt.getPaymentStatus());

        return response;
    }
}