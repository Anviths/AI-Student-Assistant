package com.anv.dao;

import com.anv.entity.FeeReceipt;
import com.anv.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeeReceiptRepository extends JpaRepository<FeeReceipt, Long> {

    List<FeeReceipt> findByStudent(Student student);

    Optional<FeeReceipt> findTopByStudentOrderByPaymentDateDesc(Student student);

    Optional<FeeReceipt> findByReceiptNumber(String receiptNumber);
}
