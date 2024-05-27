package com.learnspring.userdetailsapi.benchprofiles.controller;

import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import com.learnspring.userdetailsapi.benchprofiles.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.benchprofiles.service.BenchProfilesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class BenchProfilesController {
    private final BenchProfilesService benchProfilesService;

    public BenchProfilesController(BenchProfilesService benchProfilesService) {
        this.benchProfilesService = benchProfilesService;
    }

    @PostMapping(value = "/upload-excel", consumes = {"multipart/form-data"})
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            benchProfilesService.createUserDetails(file);
            return "Excel data uploaded and inserted into database successfully.";
        } catch (Exception e) {
            return "Error uploading Excel data: " + e.getMessage();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Bench profiles User Details")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody BenchProfilesInfo benchProfilesInfo) {
        try {
            benchProfilesService.updateUserDetails(id, benchProfilesInfo);
            return new ResponseEntity<>("User details updated successfully.", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating user details: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-users")
    @Operation(summary = "Fetch Bench profiles User Details")
    public ResponseEntity<List<BenchProfilesInfo>> fetchUserDetails() {
        Optional<List<BenchProfilesInfo>> users = benchProfilesService.getUserDetails();

        return users.map(userDetails -> new ResponseEntity<>(userDetails, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException("No users found.."));
    }
}
