package com.anv.tool;


import com.anv.dto.AttendanceResponse;
import com.anv.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendanceTool {

    private final AttendanceService attendanceService;

    @Tool(description = "Get attendance percentage of a student")
    public Double getAttendancePercentage(Long studentId) {

        return attendanceService.getAttendancePercentage(studentId);
    }

    @Tool(description = "Get attendance history")
    public List<AttendanceResponse> getAttendance(Long studentId) {

        return attendanceService.getAttendanceByStudent(studentId);
    }

    @Tool(description = "Get today's attendance")
    public AttendanceResponse getTodayAttendance(Long studentId) {

        return attendanceService.getTodayAttendance(studentId);
    }

}
