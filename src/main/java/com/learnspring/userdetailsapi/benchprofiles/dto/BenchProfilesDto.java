package com.learnspring.userdetailsapi.benchprofiles.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BenchProfilesDto(

        @NotNull
        @Size(min = 7, max = 30, message = "Recruiter name should be between 7 and 30 characters")
        String recruiterName,

        @NotNull
        @Size(min = 6, max = 30, message = "Consultant name should be between 6 and 30 characters")
        String consultantName,

        @Size(max = 15, message = "AllocatedStatus can be max of 15 characters")
        String allocatedStatus,

        @Size(max = 15, message = "Status can be max of 15 characters")
        String status,

        Integer turboCheck,

        @Size(max = 15, message = "Priority can be max of 15 characters")
        String priority,

        @Size(max = 15, message = "Technology can be max of 15 characters")
        String technology,

        @Size(max = 30, message = "Organization can be max of 30 characters")
        String organization,

        String experience,

        @Size(max = 30, message = "Location can be max of 30 characters")
        String location,

        @Size(max = 30, message = "Relocation can be max of 30 characters")
        String relocation,

        @Size(max = 15, message = "ModeOfStaying can be max of 15 characters")
        String modeOfStaying,

        @Size(max = 10, message = "NewOrExisting can be max of 10 characters")
        String newOrExisting,

        @Size(max = 15, message = "SourcedBy can be max of 15 characters")
        String sourcedBy,

        @Size(max = 15, message = "VisaStatus can be max of 15 characters")
        String visaStatus,

        @Size(max = 15, message = "MarketingVisaStatus can be max of 15 characters")
        String marketingVisaStatus,

        @Size(max = 20, message = "ContactNumber can be max of 20 characters")
        String contactNumber,

        @Email(message = "Email should be valid")
        String emailId,

        @Past
        LocalDate originalDob,

        LocalDate marketingDob,

        String whatsappNumber,

        LocalDate marketingStartDate,

        LocalDate marketingEndDate,

        @Size(max = 400, message = "Comments can be max of 400 characters")
        String comments,

        LocalDateTime dateCreated,

        LocalDateTime lastUpdated) {

}

