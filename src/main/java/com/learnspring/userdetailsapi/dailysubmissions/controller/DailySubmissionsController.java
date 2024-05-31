package com.learnspring.userdetailsapi.dailysubmissions.controller;

import com.learnspring.userdetailsapi.benchprofiles.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import com.learnspring.userdetailsapi.benchprofiles.service.BenchProfilesService;
import com.learnspring.userdetailsapi.dailysubmissions.dto.DailySubmissionsDto;
import com.learnspring.userdetailsapi.dailysubmissions.model.DailySubmissionsInfo;
import com.learnspring.userdetailsapi.dailysubmissions.service.DailySubmissionsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class DailySubmissionsController {
    private final DailySubmissionsService dailySubmissionsService;


    public DailySubmissionsController(DailySubmissionsService dailySubmissionsService) {
        this.dailySubmissionsService = dailySubmissionsService;
    }

    @PostMapping(value = "/upload-daily-submissions-excel", consumes = {"multipart/form-data"})
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            dailySubmissionsService.createSubmissionDetails(file);
            return "Excel data uploaded and inserted into database successfully.";
        } catch (Exception e) {
            return "Error uploading Excel data: " + e.getMessage();
        }
    }

    @PostMapping("/create-submission-details")
    public String createSubmissionInfo(@Valid @RequestBody DailySubmissionsDto dailySubmissionsDto) {
        try {
            dailySubmissionsService.createSubmissionInfoDetails(dailySubmissionsDto);
            return "data uploaded successfully.";
        } catch (Exception e) {
            return "Error uploading Excel data: " + e.getMessage();
        }
    }

    @PutMapping("/daily-submissions/{id}")
    @Operation(summary = "Update Bench profiles User Details")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody DailySubmissionsInfo dailySubmissionsInfo) {
        try {
            dailySubmissionsService.updateSubmissionDetails(id, dailySubmissionsInfo);
            return new ResponseEntity<>("User details updated successfully.", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating user details: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-daily-submissions-users")
    @Operation(summary = "Fetch Bench profiles User Details")
    public ResponseEntity<List<DailySubmissionsInfo>> fetchUserDetails() {
        Optional<List<DailySubmissionsInfo>> users = dailySubmissionsService.getSubmissionDetails();

        return users.map(userDetails -> new ResponseEntity<>(userDetails, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException("No users found.."));
    }
}
