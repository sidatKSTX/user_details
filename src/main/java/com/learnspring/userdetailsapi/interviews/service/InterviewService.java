package com.learnspring.userdetailsapi.interviews.service;


import com.learnspring.userdetailsapi.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.interviews.dto.InterviewDto;
import com.learnspring.userdetailsapi.interviews.model.InterviewInfo;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface InterviewService {
    void createInterviewDetails(MultipartFile file) throws Exception;

    InterviewInfo createInterviewInfoDetails(@Valid InterviewDto interviewDto);

    void updateInterviewDetails(Long id, InterviewInfo interviewInfo) throws UserNotFoundException;

    Optional<List<InterviewInfo>> getInterviewDetails();

    Optional<InterviewInfo> getInterviewDetailsByID(Long id);

    void deleteInterviewInfoById(long id);

    void deleteAllInterviewInfo();
}
