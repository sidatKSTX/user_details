package com.learnspring.userdetailsapi.dailysubmissions.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DailySubmissionsDto(

        LocalDate dateOfEntry,

        @NotNull
        @Size(min = 7, max = 30, message = "Recruiter name should be between 7 and 30 characters")
        String recruiterName,

        @NotNull
        @Size(min = 6, max = 30, message = "Consultant name should be between 6 and 30 characters")
        String consultantName,

        @Size(max = 15, message = "Technology can be max of 15 characters")
        String technology,

        @Size(max = 15, message = "Priority can be max of 15 characters")
        String priority,

        @Size(max = 15, message = "Skill can be max of 15 characters")
        String skill,

        @Size(max = 15, message = "AllocatedStatus can be max of 15 characters")
        String allocatedStatus,

        @Size(max = 15, message = "ClientType can be max of 15 characters")
        String clientType,

        @Size(max = 15, message = "ClientName can be max of 15 characters")
        String clientName,

        @Size(max = 15, message = "RequirementSource can be max of 15 characters")
        String requirementSource,

        @Size(max = 300, message = "Feedback can be max of 300 characters")
        String feedback,

        @Size(max = 400, message = "Comments can be max of 400 characters")
        String comments,

        LocalDateTime dateCreated,

        LocalDateTime lastUpdated) {

}

