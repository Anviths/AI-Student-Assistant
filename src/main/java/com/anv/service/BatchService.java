package com.anv.service;

import com.anv.dto.BatchResponse;

import java.util.List;

public interface BatchService {

    BatchResponse saveBatch(BatchResponse batchResponse);

    BatchResponse getBatchByCode(String batchCode);

    List<BatchResponse> getAllBatches();

    BatchResponse updateBatch(String batchCode, BatchResponse batchResponse);

    void deleteBatch(String batchCode);

    List<String> getStudents(String batchCode);

    String getTrainer(String batchCode);

    String getSchedule(String batchCode);

    String getRoomNumber(String batchCode);

    String getBatchTiming(String batchCode);

    String getBatchMode(String batchCode);

}