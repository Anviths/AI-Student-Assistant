package com.anv.dto;

import com.anv.entity.AttendanceStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceResponse {

    private String studentId;
    private String studentName;
    private LocalDate date;
    private AttendanceStatus status;
    private String remarks;

}
