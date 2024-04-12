package com.learnspring.userdetailsapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService {
    void createUserDetails(MultipartFile file) throws Exception;
}
