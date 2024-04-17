package com.learnspring.userdetailsapi.service;

import com.learnspring.userdetailsapi.model.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {
    void createUserDetails(MultipartFile file) throws Exception;

    Optional<List<UserInfo>> getUserDetails();
}
