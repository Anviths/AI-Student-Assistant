package com.anv.controller;

import com.anv.dto.BatchResponse;
import com.anv.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public BatchResponse save(@RequestBody BatchResponse response) {
        return batchService.saveBatch(response);
    }

    @GetMapping("/{batchCode}")
    public BatchResponse get(@PathVariable String batchCode) {
        return batchService.getBatchByCode(batchCode);
    }

    @GetMapping
    public List<BatchResponse> getAll() {
        return batchService.getAllBatches();
    }

    @PutMapping("/{batchCode}")
    public BatchResponse update(@PathVariable String batchCode,
                                @RequestBody BatchResponse response) {
        return batchService.updateBatch(batchCode, response);
    }

    @DeleteMapping("/{batchCode}")
    public void delete(@PathVariable String batchCode) {
        batchService.deleteBatch(batchCode);
    }
}
