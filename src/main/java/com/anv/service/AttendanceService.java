package com.anv.service;

import com.anv.dto.AttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse saveAttendance(AttendanceResponse attendanceResponse);

    AttendanceResponse getAttendance(Long id);

    List<AttendanceResponse> getAllAttendance();

    AttendanceResponse updateAttendance(Long id, AttendanceResponse attendanceResponse);

    void deleteAttendance(Long id);

}
