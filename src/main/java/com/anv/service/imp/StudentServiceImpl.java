package com.anv.service.imp;

import com.anv.dao.AttendanceRepository;
import com.anv.dao.FeeReceiptRepository;
import com.anv.dao.StudentRepository;
import com.anv.dto.AttendanceResponse;
import com.anv.dto.BatchResponse;
import com.anv.dto.FeeReceiptResponse;
import com.anv.dto.StudentDto;
import com.anv.entity.Student;
import com.anv.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final FeeReceiptRepository feeReceiptRepository;

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        batch = batchRepository.findByBatchCode(dto.getBatchCode())
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        Student student = new Student();

        student.setStudentId(dto.getStudentId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setCourse(dto.getCourse());
        student.setBranch(dto.getBranch());
        student.setTotalFee(dto.getTotalFee());
        student.setFeePaid(dto.getFeePaid());
        student.setAttendancePercentage(dto.getAttendancePercentage());
        student.setStatus(dto.getStatus());
        student.setBatch(batch);

        studentRepository.save(student);

        return dto;
    }

    @Override
    public StudentDto getStudentByStudentId(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return mapToDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public StudentDto updateStudent(String studentId, StudentDto studentDto) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Batch batch = batchRepository.findByBatchCode(dto.getBatchCode())
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setCourse(dto.getCourse());
        student.setBranch(dto.getBranch());
        student.setTotalFee(dto.getTotalFee());
        student.setFeePaid(dto.getFeePaid());
        student.setAttendancePercentage(dto.getAttendancePercentage());
        student.setStatus(dto.getStatus());
        student.setBatch(batch);

        studentRepository.save(student);

        return mapToDto(student);
    }

    @Override
    public void deleteStudent(String studentId) {

        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student);
    }

    private StudentDto mapToDto(Student student) {

        StudentDto dto = new StudentDto();

        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setCourse(student.getCourse());
        dto.setBranch(student.getBranch());
        dto.setTotalFee(student.getTotalFee());
        dto.setFeePaid(student.getFeePaid());
        dto.setAttendancePercentage(student.getAttendancePercentage());
        dto.setStatus(student.getStatus());

        if (student.getBatch() != null) {
            dto.setBatchCode(student.getBatch().getBatchCode());
        }

        return dto;
    }
    @Override
    public StudentDto getStudentProfile(String studentId) {
        return null;
    }

    @Override
    public BatchResponse getBatchDetails(String studentId) {
        return null;
    }

    @Override
    public AttendanceResponse getLatestAttendance(String studentId) {
        return null;
    }

    @Override
    public FeeReceiptResponse getLatestFeeReceipt(String studentId) {
        return null;
    }

    @Override
    public Double getRemainingFee(String studentId) {
        return null;
    }
}
