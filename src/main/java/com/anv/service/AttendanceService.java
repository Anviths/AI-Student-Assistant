package com.anv.service;

import com.anv.dto.AttendanceResponse;

import java.util.List;

public interface AttendanceService {

    AttendanceResponse saveAttendance(AttendanceResponse attendanceResponse);

    AttendanceResponse getAttendance(Long attendanceId);

    List<AttendanceResponse> getAttendanceByStudent(Long studentId);

    List<AttendanceResponse> getAllAttendance();

    AttendanceResponse updateAttendance(Long attendanceId,
                                        AttendanceResponse attendanceResponse);

    void deleteAttendance(Long attendanceId);

    Double getAttendancePercentage(Long studentId);

    AttendanceResponse getTodayAttendance(Long studentId);

    List<AttendanceResponse> getAttendanceHistory(Long studentId);

}