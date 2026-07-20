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

        return map(batchRepository.save(batch));
    }

    @Override
    public BatchResponse getBatchByCode(String batchCode) {

        Batch batch = batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        return map(batch);
    }

    @Override
    public List<BatchResponse> getAllBatches() {

        return batchRepository.findAll()
                .stream()
                .map(this::map)
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

        return map(batchRepository.save(batch));
    }

    @Override
    public void deleteBatch(String batchCode) {

        Batch batch = batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        batchRepository.delete(batch);
    }

    @Override
    public List<String> getStudents(String batchCode) {

        Batch batch = batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        return batch.getStudents()
                .stream()
                .map(student -> student.getStudentId())
                .toList();
    }

    @Override
    public String getTrainer(String batchCode) {

        return batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"))
                .getTrainerName();
    }

    @Override
    public String getSchedule(String batchCode) {

        Batch batch = batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        return batch.getStartDate() + " to " + batch.getEndDate();
    }

    @Override
    public String getRoomNumber(String batchCode) {

        return batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"))
                .getRoomNo();
    }

    @Override
    public String getBatchTiming(String batchCode) {

        return batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"))
                .getTiming();
    }

    @Override
    public String getBatchMode(String batchCode) {

        return batchRepository.findByBatchCode(batchCode)
                .orElseThrow(() -> new RuntimeException("Batch not found"))
                .getMode();
    }

    private BatchResponse map(Batch batch) {

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