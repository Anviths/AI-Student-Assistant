package com.anv.tool;

import com.anv.dto.AttendanceResponse;
import com.anv.dto.BatchResponse;
import com.anv.dto.FeeReceiptResponse;
import com.anv.dto.StudentDto;
import com.anv.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
@RequiredArgsConstructor
public class StudentTool {
    private final StudentService studentService;

    @Tool(description = "Get student profile using student id")
    public StudentDto getStudentProfile(String studentId) {
        return studentService.getStudentProfile(studentId);
    }

    @Tool(description = "Get batch details of a student using student id")
    public BatchResponse getBatchDetails(String studentId) {
        return studentService.getBatchDetails(studentId);
    }

    @Tool(description = "Get latest attendance of a student using student id")
    public AttendanceResponse getLatestAttendance(String studentId) {
        return studentService.getLatestAttendance(studentId);
    }

    @Tool(description = "Get latest fee receipt using student id")
    public FeeReceiptResponse getLatestFeeReceipt(String studentId) {
        return studentService.getLatestFeeReceipt(studentId);
    }

    @Tool(description = "Get remaining fee amount of a student using student id")
    public Double getRemainingFee(String studentId) {
        return studentService.getRemainingFee(studentId);
    }

}
