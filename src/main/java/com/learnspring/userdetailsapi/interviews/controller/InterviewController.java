package com.learnspring.userdetailsapi.interviews.controller;

import com.learnspring.userdetailsapi.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.interviews.dto.InterviewDto;
import com.learnspring.userdetailsapi.interviews.model.InterviewInfo;
import com.learnspring.userdetailsapi.interviews.service.InterviewService;
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

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping(value = "/upload-interview-excel", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        interviewService.createInterviewDetails(file);
        return new ResponseEntity<>("Excel data uploaded and inserted into database successfully.", HttpStatus.OK);
    }

    @PostMapping("/create-interview")
    public ResponseEntity<InterviewInfo> createInterviewInfo(@Valid @RequestBody InterviewDto interviewDto) {
        var newInterviewInfo = interviewService.createInterviewInfoDetails(interviewDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newInterviewInfo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Interview Details")
    public ResponseEntity<String> updateInterviewInfo(@PathVariable Long id, @RequestBody InterviewInfo interviewInfo) {
        interviewService.updateInterviewDetails(id, interviewInfo);
        return new ResponseEntity<>("Interview details updated successfully.", HttpStatus.OK);
    }

    @GetMapping("/fetch-interviews")
    @Operation(summary = "Fetch Interview Details")
    public ResponseEntity<List<InterviewInfo>> fetchInterviewDetails() {
        Optional<List<InterviewInfo>> interviews = interviewService.getInterviewDetails();

        return interviews.map(interviewDetails -> new ResponseEntity<>(interviewDetails, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException("No interviews found.."));
    }

    @GetMapping("/fetch-interview-by-ID/{id}")
    @Operation(summary = "Fetch Interview Details By ID")
    public ResponseEntity<Optional<InterviewInfo>> fetchInterviewDetailsByID(@PathVariable Long id) {
        Optional<Optional<InterviewInfo>> interview = Optional.ofNullable(interviewService.getInterviewDetailsByID(id));

        return interview.map(interviewDetails -> new ResponseEntity<>(interviewDetails, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException("Interview not found with id: " + id));
    }

    @DeleteMapping("/delete-all-interviews")
    @Operation(summary = "Delete All Interviews")
    public ResponseEntity<HttpStatus> deleteAllInterviewInfo() {
        interviewService.deleteAllInterviewInfo();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-interview-by-id/{id}")
    @Operation(summary = "Delete Interview By Id")
    public ResponseEntity<HttpStatus> deleteInterviewInfoById(@PathVariable("id") long id) {
        interviewService.deleteInterviewInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
