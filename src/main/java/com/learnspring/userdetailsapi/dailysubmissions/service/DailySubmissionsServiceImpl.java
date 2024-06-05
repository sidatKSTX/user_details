package com.learnspring.userdetailsapi.dailysubmissions.service;

import com.learnspring.userdetailsapi.dailysubmissions.dto.DailySubmissionsDto;
import com.learnspring.userdetailsapi.dailysubmissions.model.DailySubmissionsInfo;
import com.learnspring.userdetailsapi.dailysubmissions.repository.DailySubmissionsRepository;
import com.learnspring.userdetailsapi.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DailySubmissionsServiceImpl implements DailySubmissionsService {

    private final DailySubmissionsRepository dailySubmissionsRepository;
    private final DailySubmissionsExcelReaderService dailySubmissionsExcelReaderService;

    public DailySubmissionsServiceImpl(DailySubmissionsRepository dailySubmissionsRepository, DailySubmissionsExcelReaderService dailySubmissionsExcelReaderService) {
        this.dailySubmissionsRepository = dailySubmissionsRepository;
        this.dailySubmissionsExcelReaderService = dailySubmissionsExcelReaderService;
    }

    @Override
    public void createSubmissionDetails(MultipartFile file) throws Exception {
        List<DailySubmissionsInfo> d = dailySubmissionsExcelReaderService.readExcelFile(file);
        List<DailySubmissionsInfo> dailySubmissionsInfo = dailySubmissionsExcelReaderService.readExcelFile(file);
        dailySubmissionsRepository.saveAll(dailySubmissionsInfo);
    }

    @Override
    public DailySubmissionsInfo createSubmissionInfoDetails(DailySubmissionsDto dailySubmissionsDto) {
        DailySubmissionsInfo dailySubmissionsInfo = new DailySubmissionsInfo();
        dailySubmissionsInfo.setDateOfEntry(dailySubmissionsDto.dateOfEntry());
        dailySubmissionsInfo.setRecruiterName(dailySubmissionsDto.recruiterName());
        dailySubmissionsInfo.setConsultantName(dailySubmissionsDto.consultantName());
        dailySubmissionsInfo.setTechnology(dailySubmissionsDto.technology());
        dailySubmissionsInfo.setPriority(dailySubmissionsDto.priority());
        dailySubmissionsInfo.setSkill(dailySubmissionsDto.skill());
        dailySubmissionsInfo.setAllocatedStatus(dailySubmissionsDto.allocatedStatus());
        dailySubmissionsInfo.setClientType(dailySubmissionsDto.clientType());
        dailySubmissionsInfo.setClientName(dailySubmissionsDto.clientName());
        dailySubmissionsInfo.setRequirementSource(dailySubmissionsDto.requirementSource());
        dailySubmissionsInfo.setFeedback(dailySubmissionsDto.feedback());
        dailySubmissionsInfo.setComments(dailySubmissionsDto.comments());
        dailySubmissionsInfo.setDateCreated(dailySubmissionsDto.dateCreated());
        dailySubmissionsInfo.setLastUpdated(dailySubmissionsDto.lastUpdated());

        return dailySubmissionsRepository.save(dailySubmissionsInfo);
    }

    public void updateSubmissionDetails(Long id, DailySubmissionsInfo dailySubmissionsInfo) throws UserNotFoundException {
        DailySubmissionsInfo existingUser = dailySubmissionsRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setDateOfEntry(dailySubmissionsInfo.getDateOfEntry());
        existingUser.setRecruiterName(dailySubmissionsInfo.getRecruiterName());
        existingUser.setConsultantName(dailySubmissionsInfo.getConsultantName());
        existingUser.setTechnology(dailySubmissionsInfo.getTechnology());
        existingUser.setPriority(dailySubmissionsInfo.getPriority());
        existingUser.setSkill(dailySubmissionsInfo.getSkill());
        existingUser.setAllocatedStatus(dailySubmissionsInfo.getAllocatedStatus());
        existingUser.setClientType(dailySubmissionsInfo.getClientType());
        existingUser.setClientName(dailySubmissionsInfo.getClientName());
        existingUser.setRequirementSource(dailySubmissionsInfo.getRequirementSource());
        existingUser.setFeedback(dailySubmissionsInfo.getFeedback());
        existingUser.setComments(dailySubmissionsInfo.getComments());

        dailySubmissionsRepository.save(existingUser);
    }

    @Override
    public Optional<List<DailySubmissionsInfo>> getSubmissionDetails() {
        return Optional.of(dailySubmissionsRepository.findAll());
    }

    @Override
    public Optional<Optional<DailySubmissionsInfo>> getSubmissionDetailsByID(Long id) {
        return Optional.of(dailySubmissionsRepository.findById(id));
    }

    @Override
    public void deleteSubmissionInfoById(long id) {
        dailySubmissionsRepository.deleteById(id);
    }

    @Override
    public void deleteAllSubmissionDetails() {
        dailySubmissionsRepository.deleteAll();
    }
}