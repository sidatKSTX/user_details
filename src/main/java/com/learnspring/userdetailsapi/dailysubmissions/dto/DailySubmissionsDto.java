package com.learnspring.userdetailsapi.dailysubmissions.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DailySubmissionsDto(

        LocalDate dateOfEntry,
        @NotNull
        @Size(min=7, max=30, message = " Name should be Minimum 7 characters")
        String recruiterName,
        @NotNull
        @Size(min=6, max=30, message = " Consultant Name should be Minimum 7 characters")
        String consultantName,
        String technology,
        String priority,
        String skill,
        String allocatedStatus,
        String clientType,
        String clientName,
        String requirementSource,
        String feedback,
        String comments,
        LocalDateTime dateCreated,
        LocalDateTime lastUpdated) {

}

