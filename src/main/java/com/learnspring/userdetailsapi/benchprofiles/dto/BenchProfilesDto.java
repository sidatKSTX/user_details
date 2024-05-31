package com.learnspring.userdetailsapi.benchprofiles.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BenchProfilesDto(

        @NotNull
        @Size(min=7, max=30, message = " Name should be Minimum 7 characters")
        String recruiterName,
        @NotNull
        @Size(min=6, max=30, message = " Consultant Name should be Minimum 7 characters")
        String consultantName,
                          String allocatedStatus,
                          String status,
                          Integer turboCheck,
                          String priority,
                          String technology,
                          String organization,
                          Integer experience,
                          String location,
                          String relocation,
                          String modeOfStaying,
                          String newOrExisting,
                          String sourcedBy,
                          String visaStatus,
                          String marketingVisaStatus,
                          String contactNumber,
                          String emailId,
                          Integer rate,
                          LocalDate originalDob,
                          LocalDate marketingDob,
                          String whatsappNumber,
                          LocalDate marketingStartDate,
                          LocalDate marketingEndDate,
                          String comments,
                          LocalDateTime dateCreated,
                          LocalDateTime lastUpdated) {

}

