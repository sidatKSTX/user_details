package com.learnspring.userdetailsapi.dailysubmissions.service;

import com.learnspring.userdetailsapi.benchprofiles.dto.BenchProfilesDto;
import com.learnspring.userdetailsapi.benchprofiles.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import com.learnspring.userdetailsapi.dailysubmissions.dto.DailySubmissionsDto;
import com.learnspring.userdetailsapi.dailysubmissions.model.DailySubmissionsInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DailySubmissionsService {
    void createSubmissionDetails(MultipartFile file) throws Exception;

    void createSubmissionInfoDetails(DailySubmissionsDto dailySubmissionsDto);

    void updateSubmissionDetails(Long id, DailySubmissionsInfo dailySubmissionsInfo) throws UserNotFoundException;

    Optional<List<DailySubmissionsInfo>> getSubmissionDetails();
}
