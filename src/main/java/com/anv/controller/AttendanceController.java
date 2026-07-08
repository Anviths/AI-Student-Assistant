package com.anv.controller;

import com.anv.dto.AttendanceResponse;
import com.anv.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public AttendanceResponse save(@RequestBody AttendanceResponse response) {
        return attendanceService.saveAttendance(response);
    }

    @GetMapping("/{id}")
    public AttendanceResponse get(@PathVariable Long id) {
        return attendanceService.getAttendance(id);
    }

    @GetMapping
    public List<AttendanceResponse> getAll() {
        return attendanceService.getAllAttendance();
    }

    @PutMapping("/{id}")
    public AttendanceResponse update(@PathVariable Long id,
                                     @RequestBody AttendanceResponse response) {
        return attendanceService.updateAttendance(id, response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }
}
