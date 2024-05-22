package com.learnspring.userdetailsapi.model;

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
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private long id;

    @Column(name = "recruiter_name")
    private String recruiterName;

    @Column(name = "consultant_name")
    private String consultantName;

    @Column(name = "assigned_unAssigned_status")
    private String assignedUnAssignedStatus;

    @Column(name = "status")
    private String status;

    @Column(name = "turbo_check")
    private Integer turboCheck;

    @Column(name = "priority")
    private String priority;

    @Column(name = "technology")
    private String technology;

    @Column(name = "org")
    private String org;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "Location")
    private String location;

    @Column(name = "relocation")
    private String relocation;

    @Column(name = "guestHouse_or_remote")
    private String guestHouseOrRemote;

    @Column(name = "new_or_existing")
    private String newOrExisting;

    @Column(name = "sourced_by")
    private String sourcedBy;

    @Column(name = "original_visa_status")
    private String originalVisaStatus;

    @Column(name = "marketing_visa_status")
    private String marketingVisaStatus;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "consultants_email_id")
    private String consultantsEmailId;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "orginal_dob")
    private LocalDate originalDob;

    @Column(name = "marketing_dob")
    private LocalDate marketingDob;

    @Column(name = "whatsapp_number")
    private String whatsappNumber;

    @Column(name = "marketing_start_date")
    private LocalDate marketingStartDate;

    @Column(name = "marketing_end_date")
    private LocalDate marketingEndDate;

    @Column(name = "comments")
    private String comments;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
