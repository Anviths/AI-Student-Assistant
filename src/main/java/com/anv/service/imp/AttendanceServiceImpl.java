package com.anv.service.imp;

import com.anv.dao.AttendanceRepository;
import com.anv.dao.StudentRepository;
import com.anv.dto.AttendanceResponse;
import com.anv.entity.Attendance;
import com.anv.entity.AttendanceStatus;
import com.anv.entity.Student;
import com.anv.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        return map(attendanceRepository.save(attendance));
    }

    @Override
    public AttendanceResponse getAttendance(Long attendanceId) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return map(attendance);
    }

    @Override
    public List<AttendanceResponse> getAttendanceByStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return attendanceRepository.findByStudent(student)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<AttendanceResponse> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public AttendanceResponse updateAttendance(Long attendanceId,
                                               AttendanceResponse response) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setDate(response.getDate());
        attendance.setStatus(response.getStatus());
        attendance.setRemarks(response.getRemarks());

        return map(attendanceRepository.save(attendance));
    }

    @Override
    public void deleteAttendance(Long attendanceId) {

        attendanceRepository.deleteById(attendanceId);

    }

    @Override
    public Double getAttendancePercentage(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Attendance> attendanceList =
                attendanceRepository.findByStudent(student);

        if (attendanceList.isEmpty()) {
            return 0.0;
        }

        long presentDays = attendanceList.stream()
                .filter(a -> a.getStatus() == AttendanceStatus.PRESENT)
                .count();

        return (presentDays * 100.0) / attendanceList.size();
    }

    @Override
    public AttendanceResponse getTodayAttendance(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Attendance> attendanceList =
                attendanceRepository.findByStudentAndDate(
                        student,
                        LocalDate.now()
                );

        if (attendanceList.isEmpty()) {
            throw new RuntimeException("Attendance not found");
        }

        return map(attendanceList.get(0));
    }

    @Override
    public List<AttendanceResponse> getAttendanceHistory(Long studentId) {

        return getAttendanceByStudent(studentId);

    }

    private AttendanceResponse map(Attendance attendance) {

        AttendanceResponse response = new AttendanceResponse();

        response.setStudentId(attendance.getStudent().getStudentId());

        response.setStudentName(attendance.getStudent().getName());

        response.setDate(attendance.getDate());

        response.setStatus(attendance.getStatus());

        response.setRemarks(attendance.getRemarks());

        return response;
    }
}