package com.learnspring.userdetailsapi.benchprofiles.service;

import com.learnspring.userdetailsapi.benchprofiles.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BenchProfilesService {
    void createUserDetails(MultipartFile file) throws Exception;

    void updateUserDetails(Long id, BenchProfilesInfo benchProfilesInfo) throws UserNotFoundException;

    Optional<List<BenchProfilesInfo>> getUserDetails();
}
