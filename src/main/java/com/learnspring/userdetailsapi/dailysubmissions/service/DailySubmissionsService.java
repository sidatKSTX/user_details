package com.learnspring.userdetailsapi.dailysubmissions.service;

import com.learnspring.userdetailsapi.dailysubmissions.dto.DailySubmissionsDto;
import com.learnspring.userdetailsapi.dailysubmissions.model.DailySubmissionsInfo;
import com.learnspring.userdetailsapi.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DailySubmissionsService {
    void createSubmissionDetails(MultipartFile file) throws Exception;

    DailySubmissionsInfo createSubmissionInfoDetails(DailySubmissionsDto dailySubmissionsDto);

    void updateSubmissionDetails(Long id, DailySubmissionsInfo dailySubmissionsInfo) throws UserNotFoundException;

    Optional<List<DailySubmissionsInfo>> getSubmissionDetails();

    Optional<Optional<DailySubmissionsInfo>> getSubmissionDetailsByID(Long id);

    void deleteSubmissionInfoById(long id);

    void deleteAllSubmissionDetails();
}
