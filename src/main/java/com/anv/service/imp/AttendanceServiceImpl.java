package com.anv.service.imp;


import com.anv.dao.AttendanceRepository;
import com.anv.dao.StudentRepository;
import com.anv.dto.AttendanceResponse;
import com.anv.entity.Attendance;
import com.anv.entity.Student;
import com.anv.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    @Override
    public AttendanceResponse saveAttendance(AttendanceResponse response) {

        Student student = studentRepository.findByStudentId(response.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Attendance attendance = new Attendance();

        attendance.setStudent(student);
        attendance.setDate(response.getDate());
        attendance.setStatus(response.getStatus());
        attendance.setRemarks(response.getRemarks());

        attendanceRepository.save(attendance);

        return mapToResponse(attendance);
    }

    @Override
    public AttendanceResponse getAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return mapToResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AttendanceResponse updateAttendance(Long id, AttendanceResponse response) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        Student student = studentRepository.findByStudentId(response.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        attendance.setStudent(student);
        attendance.setDate(response.getDate());
        attendance.setStatus(response.getStatus());
        attendance.setRemarks(response.getRemarks());

        attendanceRepository.save(attendance);

        return mapToResponse(attendance);
    }

    @Override
    public void deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }

    // ================= Mapper =================

    private AttendanceResponse mapToResponse(Attendance attendance) {

        AttendanceResponse response = new AttendanceResponse();

        response.setStudentId(attendance.getStudent().getStudentId());
        response.setStudentName(attendance.getStudent().getName());
        response.setDate(attendance.getDate());
        response.setStatus(attendance.getStatus());
        response.setRemarks(attendance.getRemarks());

        return response;
    }
}
