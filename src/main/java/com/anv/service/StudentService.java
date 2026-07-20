package com.anv.service;

import com.anv.dto.AttendanceResponse;
import com.anv.dto.BatchResponse;
import com.anv.dto.FeeReceiptResponse;
import com.anv.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto saveStudent(StudentDto studentDto);

    StudentDto getStudent(String studentId);

    List<StudentDto> getAllStudents();

    StudentDto updateStudent(String studentId, StudentDto studentDto);

    void deleteStudent(String studentId);

    StudentDto getStudentProfile(String studentId);

    BatchResponse getBatch(String studentId);

    AttendanceResponse getLatestAttendance(String studentId);

    FeeReceiptResponse getLatestFeeReceipt(String studentId);

    Double getRemainingFee(String studentId);

    BatchResponse getBatchDetails(String studentId);
     StudentDto getStudentByStudentId(String studentId);
}