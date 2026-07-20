package com.anv.tool;

import com.anv.dto.StudentDto;
import com.anv.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentTool {

    private final StudentService studentService;

    @Tool(description = "Get student profile using student id")
    public StudentDto getStudentProfile(String studentId) {
        return studentService.getStudent(studentId);
    }

    @Tool(description = "Get complete student details")
    public StudentDto getStudentDetails(String studentId) {
        return studentService.getStudent(studentId);
    }

    @Tool(description = "Get student's course")
    public String getCourse(String studentId) {
        return studentService.getStudent(studentId).getCourse();
    }

    @Tool(description = "Get student's email")
    public String getEmail(String studentId) {
        return studentService.getStudent(studentId).getEmail();
    }

    @Tool(description = "Get student's phone number")
    public String getPhone(String studentId) {
        return studentService.getStudent(studentId).getPhone();
    }

    @Tool(description = "Get student status")
    public String getStudentStatus(String studentId) {
        return studentService.getStudent(studentId).getStatus().name();
    }

}