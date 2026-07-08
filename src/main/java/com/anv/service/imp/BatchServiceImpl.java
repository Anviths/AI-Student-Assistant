package com.anv.service.imp;


import com.anv.dao.BatchRepository;
import com.anv.dto.BatchResponse;
import com.anv.entity.Batch;
import com.anv.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;

    @Override
    public BatchResponse saveBatch(BatchResponse response) {

        Batch batch = new Batch();

        batch.setBatchCode(response.getBatchCode());
        batch.setCourse(response.getCourse());
        batch.setTrainerName(response.getTrainerName());
        batch.setStartDate(response.getStartDate());
        batch.setEndDate(response.getEndDate());
        batch.setTiming(response.getTiming());
        batch.setRoomNo(response.getRoomNo());
        batch.setMode(response.getMode());
        batch.setStatus(response.getStatus());

        batchRepository.save(batch);

        return mapToResponse(batch);
    }

    @Override
    public BatchResponse getBatchByCode(String batchCode) {

        Batch batch = batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        return mapToResponse(batch);
    }

    @Override
    public List<BatchResponse> getAllBatches() {

        return batchRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public BatchResponse updateBatch(String batchCode, BatchResponse response) {

        Batch batch = batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        batch.setCourse(response.getCourse());
        batch.setTrainerName(response.getTrainerName());
        batch.setStartDate(response.getStartDate());
        batch.setEndDate(response.getEndDate());
        batch.setTiming(response.getTiming());
        batch.setRoomNo(response.getRoomNo());
        batch.setMode(response.getMode());
        batch.setStatus(response.getStatus());

        batchRepository.save(batch);

        return mapToResponse(batch);
    }

    @Override
    public void deleteBatch(String batchCode) {

        Batch batch = batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        batchRepository.delete(batch);
    }

    // ====================== Mapper ======================

    private BatchResponse mapToResponse(Batch batch) {

        BatchResponse response = new BatchResponse();

        response.setBatchCode(batch.getBatchCode());
        response.setCourse(batch.getCourse());
        response.setTrainerName(batch.getTrainerName());
        response.setStartDate(batch.getStartDate());
        response.setEndDate(batch.getEndDate());
        response.setTiming(batch.getTiming());
        response.setRoomNo(batch.getRoomNo());
        response.setMode(batch.getMode());
        response.setStatus(batch.getStatus());

        return response;
    }
}
