package com.learnspring.userdetailsapi.benchprofiles.service;

import com.learnspring.userdetailsapi.benchprofiles.dto.BenchProfilesDto;
import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import com.learnspring.userdetailsapi.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BenchProfilesService {
    void createUserDetails(MultipartFile file) throws Exception;

    BenchProfilesInfo createUserInfoDetails(BenchProfilesDto benchProfilesDto);

    void updateUserDetails(Long id, BenchProfilesInfo benchProfilesInfo) throws UserNotFoundException;

    Optional<List<BenchProfilesInfo>> getUserDetails();

    Optional<Optional<BenchProfilesInfo>> getUserDetailsByID(Long id);

    void deleteUserInfoById(long id);

    void deleteAllUserInfo();
}
