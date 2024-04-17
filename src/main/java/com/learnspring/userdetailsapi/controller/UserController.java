package com.learnspring.userdetailsapi.controller;

import com.learnspring.userdetailsapi.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.model.UserInfo;
import com.learnspring.userdetailsapi.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/users")
public class UserController {
    private final UserInfoService userInfoService;

    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping(value = "/upload-excel", consumes = {"multipart/form-data"})
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            userInfoService.createUserDetails(file);
            return "Excel data uploaded and inserted into database successfully.";
        } catch (Exception e) {
            return "Error uploading Excel data: " + e.getMessage();
        }
    }

    @GetMapping
    @Operation(summary = "Extract User Details From Excel")
    public ResponseEntity<List<UserInfo>> extractExcelDetails() {
        Optional<List<UserInfo>> users = userInfoService.getUserDetails();

        return users.map(userDetails -> new ResponseEntity<>(userDetails, HttpStatus.OK))
                .orElseThrow(() -> new UserNotFoundException("There are no users in Excel file.."));
    }
}
