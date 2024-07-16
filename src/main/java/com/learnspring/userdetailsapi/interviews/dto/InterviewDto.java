package com.learnspring.userdetailsapi.interviews.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record InterviewDto(
        @NotNull
        @Size(min = 7, max = 30, message = "Recruiter name should be between 7 and 30 characters")
        String recruiterName,

        @NotNull
        @Size(min = 1, max = 15, message = "Round should be between 1 and 15 characters")
        String round,

        @Past
        LocalDate interviewDate,

        @NotNull
        LocalTime interviewTime,

        @NotNull
        @Size(min = 6, max = 30, message = "Consultant name should be between 6 and 30 characters")
        String consultantName,

        @Size(max = 15, message = "OwnSupport can be max of 15 characters")
        String ownSupport,

        @Size(max = 15, message = "Technology can be max of 15 characters")
        String technology,

        @Size(max = 15, message = "ClientType can be max of 15 characters")
        String clientType,

        @Size(max = 30, message = "ClientName can be max of 30 characters")
        String clientName,

        @Size(max = 30, message = "Location can be max of 30 characters")
        String location,

        String rate,

        @Size(max = 30, message = "Vendor can be max of 30 characters")
        String vendor,

        @Size(max = 500, message = "Feedback can be max of 500 characters")
        String feedback,

        @Size(max = 400, message = "Comments can be max of 400 characters")
        String comments,

        LocalDateTime dateCreated,

        LocalDateTime lastUpdated
) {
}
