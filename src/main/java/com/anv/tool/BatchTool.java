package com.anv.tool;

import com.anv.dto.BatchResponse;
import com.anv.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BatchTool {

    private final BatchService batchService;

    @Tool(description = "Get batch details using batch code")
    public BatchResponse getBatchDetails(String batchCode) {

        return batchService.getBatchByCode(batchCode);
    }

    @Tool(description = "Get all students in a batch")
    public List<String> getStudents(String batchCode) {

        return batchService.getStudents(batchCode);
    }

    @Tool(description = "Get trainer name of a batch")
    public String getTrainer(String batchCode) {

        return batchService.getTrainer(batchCode);
    }

    @Tool(description = "Get batch schedule")
    public String getSchedule(String batchCode) {

        return batchService.getSchedule(batchCode);
    }

}
