package com.learnspring.userdetailsapi.service;

import com.learnspring.userdetailsapi.model.UserInfo;
import com.learnspring.userdetailsapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserRepository repository;
    private final ExcelReaderService excelReaderService;

    public UserInfoServiceImpl(UserRepository repository, ExcelReaderService excelReaderService) {
        this.repository = repository;
        this.excelReaderService = excelReaderService;
    }

    @Override
    public void createUserDetails(MultipartFile file) throws Exception {
        List<UserInfo> userInfo = excelReaderService.readExcelFile(file);
        repository.saveAll(userInfo);
    }
}
