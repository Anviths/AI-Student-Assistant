package com.anv.service.imp;

import com.anv.dao.AttendanceRepository;
import com.anv.dao.BatchRepository;
import com.anv.dao.FeeReceiptRepository;
import com.anv.dao.StudentRepository;
import com.anv.dto.AttendanceResponse;
import com.anv.dto.BatchResponse;
import com.anv.dto.FeeReceiptResponse;
import com.anv.dto.StudentDto;
import com.anv.entity.Attendance;
import com.anv.entity.Batch;
import com.anv.entity.FeeReceipt;
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
    private final BatchRepository batchRepository;
    @Override
    public StudentDto saveStudent(StudentDto dto) {
       Batch batch = batchRepository.findByBatchCode(dto.getBatchCode())
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
    public StudentDto getStudent(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return mapToDto(student);
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
    public StudentDto updateStudent(String studentId, StudentDto dto) {
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
    public BatchResponse getBatch(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return mapToBatchResponse(student.getBatch());
    }



    @Override
    public AttendanceResponse getLatestAttendance(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Attendance attendance = attendanceRepository
                .findFirstByStudentOrderByDateDesc(student)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        AttendanceResponse response = new AttendanceResponse();

        response.setStudentId(student.getStudentId());
        response.setStudentName(student.getName());
        response.setDate(attendance.getDate());
        response.setStatus(attendance.getStatus());
        response.setRemarks(attendance.getRemarks());

        return response;
    }

    @Override
    public FeeReceiptResponse getLatestFeeReceipt(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        FeeReceipt receipt = feeReceiptRepository
                .findTopByStudentOrderByPaymentDateDesc(student)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));

        FeeReceiptResponse response = new FeeReceiptResponse();

        response.setReceiptNumber(receipt.getReceiptNumber());
        response.setStudentId(student.getStudentId());
        response.setStudentName(student.getName());
        response.setAmount(receipt.getAmount());
        response.setPaymentDate(receipt.getPaymentDate());
        response.setPaymentMode(receipt.getPaymentMode());
        response.setTransactionId(receipt.getTransactionId());
        response.setPaymentStatus(receipt.getPaymentStatus());

        return response;
    }

    @Override
    public Double getRemainingFee(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getTotalFee() - student.getFeePaid();
    }

    @Override
    public BatchResponse getBatchDetails(String studentId) {
        return getBatch(studentId);
    }


    @Override
    public StudentDto getStudentProfile(String studentId) {
        return getStudent(studentId);
    }

    private BatchResponse mapToBatchResponse(Batch batch) {

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
