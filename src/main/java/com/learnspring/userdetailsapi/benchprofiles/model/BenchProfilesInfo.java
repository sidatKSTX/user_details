package com.learnspring.userdetailsapi.benchprofiles.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bench_profiles")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BenchProfilesInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "RECRUITER_NAME")
    private String recruiterName;

    @Column(name = "CONSULTANT_NAME")
    private String consultantName;

    @Column(name = "ALLOCATED_STATUS")
    private String allocatedStatus;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TURBO_CHECK")
    private Integer turboCheck;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "TECHNOLOGY")
    private String technology;

    @Column(name = "ORGANIZATION")
    private String organization;

//    Check if we need to change the datatype to string (Ex: if input is 8+)
    @Column(name = "EXPERIENCE")
    private Integer experience;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "RELOCATION")
    private String relocation;

    @Column(name = "MODE_OF_STAYING")
    private String modeOfStaying;

    //Need to change the name into more meaningful
    @Column(name = "NEW_OR_EXISTING")
    private String newOrExisting;

    @Column(name = "SOURCED_BY")
    private String sourcedBy;

    @Column(name = "VISA_STATUS")
    private String visaStatus;

    @Column(name = "MARKETING_VISA_STATUS")
    private String marketingVisaStatus;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "EMAIL_ID")
    private String emailId;

//    Check if we need to change to string datatype
    @Column(name = "RATE")
    private Integer rate;

    @Column(name = "ORIGINAL_DOB")
    private LocalDate originalDob;

    @Column(name = "MARKETING_DOB")
    private LocalDate marketingDob;

    @Column(name = "WHATSAPP_NUMBER")
    private String whatsappNumber;

    @Column(name = "MARKETING_START_DATE")
    private LocalDate marketingStartDate;

    @Column(name = "MARKETING_END_DATE")
    private LocalDate marketingEndDate;

    @Column(name = "COMMENTS")
    private String comments;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
