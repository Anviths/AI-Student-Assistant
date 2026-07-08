package com.anv.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "batches")
@Getter
@Setter
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String batchCode;

    private String course;

    private String trainerName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String timing;

    private String roomNo;

    private String mode;

    @Enumerated(EnumType.STRING)
    private BatchStatus status;

    @OneToMany(mappedBy = "batch")
    private List<Student> students;
}
