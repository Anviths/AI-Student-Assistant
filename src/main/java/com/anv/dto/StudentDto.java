package com.anv.dto;

import com.anv.entity.StudentStatus;
import lombok.Data;

@Data
public class StudentDto {

    private String studentId;
    private String name;
    private String email;
    private String phone;
    private String course;
    private String branch;
    private String batchCode;
    private Double totalFee;
    private Double feePaid;
    private Double attendancePercentage;
    private StudentStatus status;

}
