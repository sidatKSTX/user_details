package com.learnspring.userdetailsapi.dailysubmissions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_submissions")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DailySubmissionsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "DATE_OF_ENTRY")
    private LocalDate dateOfEntry;

    @Column(name = "RECRUITER_NAME")
    private String recruiterName;

    @Column(name = "CONSULTANT_NAME")
    private String consultantName;

    @Column(name = "TECHNOLOGY")
    private String technology;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "SKILL")
    private String skill;

    @Column(name = "ALLOCATED_STATUS")
    private String allocatedStatus;

    @Column(name = "CLIENT_TYPE")
    private String clientType;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Column(name = "REQUIREMENT_SOURCE")
    private String requirementSource;

    @Column(name = "FEEDBACK")
    private String feedback;

    @Column(name = "COMMENTS")
    private String comments;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
