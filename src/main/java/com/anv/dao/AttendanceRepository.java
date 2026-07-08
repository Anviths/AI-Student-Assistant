package com.anv.dao;

import com.anv.entity.Attendance;
import com.anv.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudent(Student student);

    List<Attendance> findByStudentAndDate(Student student, LocalDate date);

    List<Attendance> findByDate(LocalDate date);
}
