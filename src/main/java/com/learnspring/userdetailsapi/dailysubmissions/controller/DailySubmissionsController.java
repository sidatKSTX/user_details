package com.learnspring.userdetailsapi.dailysubmissions.controller;

import com.learnspring.userdetailsapi.dailysubmissions.dto.DailySubmissionsDto;
import com.learnspring.userdetailsapi.dailysubmissions.model.DailySubmissionsInfo;
import com.learnspring.userdetailsapi.dailysubmissions.service.DailySubmissionsService;
import com.learnspring.userdetailsapi.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        dailySubmissionsService.createSubmissionDetails(file);
        return new ResponseEntity<>("Excel data uploaded and inserted into database successfully.", HttpStatus.OK);
    }

    @PostMapping("/create-daily-submissions")
    public ResponseEntity<DailySubmissionsInfo> createSubmissionInfo(@Valid @RequestBody DailySubmissionsDto dailySubmissionsDto) {
        var newDailySubmissionInfo = dailySubmissionsService.createSubmissionInfoDetails(dailySubmissionsDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDailySubmissionInfo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/daily-submissions/{id}")
    @Operation(summary = "Update daily submissions User Details")
    public ResponseEntity<String> updateSubmissionInfo(@PathVariable Long id, @RequestBody DailySubmissionsInfo dailySubmissionsInfo) {
        dailySubmissionsService.updateSubmissionDetails(id, dailySubmissionsInfo);
        return new ResponseEntity<>("User details updated successfully.", HttpStatus.OK);
    }

    @GetMapping("/fetch-daily-submissions")
    @Operation(summary = "Fetch daily submissions User Details")
    public ResponseEntity<List<DailySubmissionsInfo>> fetchSubmissionDetails() {
        Optional<List<DailySubmissionsInfo>> users = dailySubmissionsService.getSubmissionDetails();

        return users.map(userDetails -> new ResponseEntity<>(userDetails, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException("No users found.."));
    }

    @GetMapping("/fetch-daily-submissions-by-ID/{id}")
    @Operation(summary = "Fetch daily submissions User Details by ID")
    public ResponseEntity<Optional<DailySubmissionsInfo>> fetchSubmissionDetailsByID(@PathVariable Long id) {
        Optional<Optional<DailySubmissionsInfo>> users = dailySubmissionsService.getSubmissionDetailsByID(id);

        return users.map(userDetails -> new ResponseEntity<>(userDetails, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @DeleteMapping("delete-all-submissions")
    @Operation(summary = "Delete All Submissions")
    public ResponseEntity<HttpStatus> deleteAllUserInfo() {
        dailySubmissionsService.deleteAllSubmissionDetails();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("delete-submission-by-id/{id}")
    @Operation(summary = "Delete Submission By Id")
    public ResponseEntity<HttpStatus> deleteUserInfoById(@PathVariable("id") long id) {
        dailySubmissionsService.deleteSubmissionInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
