package com.learnspring.userdetailsapi.benchprofiles.service;

import com.learnspring.userdetailsapi.benchprofiles.exception.UserNotFoundException;
import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import com.learnspring.userdetailsapi.benchprofiles.repository.BenchProfilesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BenchProfilesServiceImpl implements BenchProfilesService {

    private final BenchProfilesRepository benchProfilesRepository;
    private final BenchProfilesExcelReaderService benchProfilesExcelReaderService;

    public BenchProfilesServiceImpl(BenchProfilesRepository benchProfilesRepository, BenchProfilesExcelReaderService benchProfilesExcelReaderService) {
        this.benchProfilesRepository = benchProfilesRepository;
        this.benchProfilesExcelReaderService = benchProfilesExcelReaderService;
    }

    @Override
    public void createUserDetails(MultipartFile file) throws Exception {
        List<BenchProfilesInfo> benchProfilesInfo = benchProfilesExcelReaderService.readExcelFile(file);
        benchProfilesRepository.saveAll(benchProfilesInfo);
    }

    public void updateUserDetails(Long id, BenchProfilesInfo benchProfilesInfo) throws UserNotFoundException {
        BenchProfilesInfo existingUser = benchProfilesRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Update fields
        existingUser.setRecruiterName(benchProfilesInfo.getRecruiterName());
        existingUser.setConsultantName(benchProfilesInfo.getConsultantName());
        existingUser.setAllocatedStatus(benchProfilesInfo.getAllocatedStatus());
        existingUser.setStatus(benchProfilesInfo.getStatus());
        existingUser.setTurboCheck(benchProfilesInfo.getTurboCheck());
        existingUser.setPriority(benchProfilesInfo.getPriority());
        existingUser.setTechnology(benchProfilesInfo.getTechnology());
        existingUser.setOrganization(benchProfilesInfo.getOrganization());
        existingUser.setExperience(benchProfilesInfo.getExperience());
        existingUser.setLocation(benchProfilesInfo.getLocation());
        existingUser.setRelocation(benchProfilesInfo.getRelocation());
        existingUser.setModeOfStaying(benchProfilesInfo.getModeOfStaying());
        existingUser.setNewOrExisting(benchProfilesInfo.getNewOrExisting());
        existingUser.setSourcedBy(benchProfilesInfo.getSourcedBy());
        existingUser.setVisaStatus(benchProfilesInfo.getVisaStatus());
        existingUser.setMarketingVisaStatus(benchProfilesInfo.getMarketingVisaStatus());
        existingUser.setContactNumber(benchProfilesInfo.getContactNumber());
        existingUser.setEmailId(benchProfilesInfo.getEmailId());
        existingUser.setRate(benchProfilesInfo.getRate());
        existingUser.setOriginalDob(benchProfilesInfo.getOriginalDob());
        existingUser.setMarketingDob(benchProfilesInfo.getMarketingDob());
        existingUser.setWhatsappNumber(benchProfilesInfo.getWhatsappNumber());
        existingUser.setMarketingStartDate(benchProfilesInfo.getMarketingStartDate());
        existingUser.setMarketingEndDate(benchProfilesInfo.getMarketingEndDate());
        existingUser.setComments(benchProfilesInfo.getComments());

        benchProfilesRepository.save(existingUser);
    }

    @Override
    public Optional<List<BenchProfilesInfo>> getUserDetails() {
        return Optional.of(benchProfilesRepository.findAll());
    }
}
