package com.learnspring.userdetailsapi.interviews.service;

import com.learnspring.userdetailsapi.common.CommonExcelReaderService;
import com.learnspring.userdetailsapi.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.interviews.dto.InterviewDto;
import com.learnspring.userdetailsapi.interviews.model.InterviewInfo;
import com.learnspring.userdetailsapi.interviews.repository.InterviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final CommonExcelReaderService interviewExcelReaderService;

    public InterviewServiceImpl(InterviewRepository interviewRepository, CommonExcelReaderService interviewExcelReaderService) {
        this.interviewRepository = interviewRepository;
        this.interviewExcelReaderService = interviewExcelReaderService;
    }

    @Override
    public void createInterviewDetails(MultipartFile file) throws Exception {
        List<InterviewInfo> interviewInfoList = interviewExcelReaderService.readInterviewsExcelFile(file);
        interviewRepository.saveAll(interviewInfoList);
    }

    @Override
    public InterviewInfo createInterviewInfoDetails(InterviewDto interviewDto) {
        var interviewInfo = new InterviewInfo();
        interviewInfo.setRecruiterName(interviewDto.recruiterName());
        interviewInfo.setRound(interviewDto.round());
        interviewInfo.setInterviewDate(interviewDto.interviewDate());
        interviewInfo.setInterviewTime(String.valueOf(interviewDto.interviewTime()));
        interviewInfo.setConsultantName(interviewDto.consultantName());
        interviewInfo.setOwnSupport(interviewDto.ownSupport());
        interviewInfo.setTechnology(interviewDto.technology());
        interviewInfo.setClientType(interviewDto.clientType());
        interviewInfo.setClientName(interviewDto.clientName());
        interviewInfo.setLocation(interviewDto.location());
        interviewInfo.setRate(String.valueOf(interviewDto.rate()));
        interviewInfo.setVendor(interviewDto.vendor());
        interviewInfo.setFeedback(interviewDto.feedback());
        interviewInfo.setComments(interviewDto.comments());

        return interviewRepository.save(interviewInfo);
    }

    @Override
    public void updateInterviewDetails(Long id, InterviewInfo interviewInfo) throws UserNotFoundException {
        var existingInterview = interviewRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Interview not found with id: " + id));

        existingInterview.setRecruiterName(interviewInfo.getRecruiterName());
        existingInterview.setRound(interviewInfo.getRound());
        existingInterview.setInterviewDate(interviewInfo.getInterviewDate());
        existingInterview.setInterviewTime(interviewInfo.getInterviewTime());
        existingInterview.setConsultantName(interviewInfo.getConsultantName());
        existingInterview.setOwnSupport(interviewInfo.getOwnSupport());
        existingInterview.setTechnology(interviewInfo.getTechnology());
        existingInterview.setClientType(interviewInfo.getClientType());
        existingInterview.setClientName(interviewInfo.getClientName());
        existingInterview.setLocation(interviewInfo.getLocation());
        existingInterview.setRate(interviewInfo.getRate());
        existingInterview.setVendor(interviewInfo.getVendor());
        existingInterview.setFeedback(interviewInfo.getFeedback());
        existingInterview.setComments(interviewInfo.getComments());

        interviewRepository.save(existingInterview);
    }

    @Override
    public Optional<List<InterviewInfo>> getInterviewDetails() {
        return Optional.of(interviewRepository.findAll());
    }

    @Override
    public Optional<InterviewInfo> getInterviewDetailsByID(Long id) {
        return interviewRepository.findById(id);
    }

    @Override
    public void deleteInterviewInfoById(long id) {
        interviewRepository.deleteById(id);
    }

    @Override
    public void deleteAllInterviewInfo() {
        interviewRepository.deleteAll();
    }
}
