package com.anv.dto;

import com.anv.entity.BatchStatus;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BatchResponse {
    private String batchCode;
    private String course;
    private String trainerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String timing;
    private String roomNo;
    private String mode;
    private BatchStatus status;

}
