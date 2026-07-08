package com.anv.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String course;

    private String branch;

    private Double totalFee;

    private Double feePaid;

    private Double attendancePercentage;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private Batch batch;
}
