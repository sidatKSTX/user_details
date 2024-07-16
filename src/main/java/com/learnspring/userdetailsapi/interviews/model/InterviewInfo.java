package com.learnspring.userdetailsapi.interviews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class InterviewInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "INTERVIEW_ID")
    private long id;

    @Column(name = "RECRUITER_NAME")
    private String recruiterName;

    @Column(name = "ROUND")
    private String round;

    @Column(name = "INTERVIEW_DATE")
    private LocalDate interviewDate;

    @Column(name = "TIME")
    private String interviewTime;

//    @Column(name = "MODE")
//    private String mode;

    @Column(name = "CONSULTANT_NAME")
    private String consultantName;

    @Column(name = "OWN_SUPPORT")
    private String ownSupport;

    @Column(name = "TECHNOLOGY")
    private String technology;

//    @Column(name = "JOB_TITLE")
//    private String jobTitle;

    @Column(name = "CLIENT_TYPE")
    private String clientType;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "RATE")
    private String rate;

    @Column(name = "VENDOR")
    private String vendor;

    @Column(name = "FEEDBACK")
    private String feedback;

    @Column(name = "COMMENTS")
    private String comments;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
