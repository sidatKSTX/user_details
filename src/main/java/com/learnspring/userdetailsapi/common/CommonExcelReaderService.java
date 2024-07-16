package com.learnspring.userdetailsapi.common;

import com.learnspring.userdetailsapi.benchprofiles.model.BenchProfilesInfo;
import com.learnspring.userdetailsapi.dailysubmissions.model.DailySubmissionsInfo;
import com.learnspring.userdetailsapi.interviews.model.InterviewInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.learnspring.userdetailsapi.common.ExcelUtil.*;

@Component
public class CommonExcelReaderService {
    @Value("${bench-profiles-excel-sheet-index}")
    private int benchProfilesSheetIndex;

    @Value("${daily-submissions-excel-sheet-index}")
    private int dailySubmissionsSheetIndex;
    
    @Value("${interviews-excel-sheet-index}")
    private int interviewsSheetIndex;

    public List<BenchProfilesInfo> readBenchProfilesExcelFile(MultipartFile file) throws Exception {
        try (var workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(benchProfilesSheetIndex);

            return StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1) // Skip the header row
                    .filter(row -> !isEmptyRow(row))
                    .map(row -> {
                        var user = new BenchProfilesInfo();
                        user.setRecruiterName(getStringCellValue(row, 0));
                        user.setConsultantName(getStringCellValue(row, 1));
                        user.setAllocatedStatus(getStringCellValue(row, 2));
                        user.setStatus(getStringCellValue(row, 3));
                        user.setTurboCheck(getIntegerCellValue(row, 4));
                        user.setPriority(getStringCellValue(row, 5));
                        user.setTechnology(getStringCellValue(row, 6));
                        user.setOrganization(getStringCellValue(row, 7));
                        user.setExperience(getStringCellValue(row, 8));
                        user.setLocation(getStringCellValue(row, 9));
                        user.setRelocation(getStringCellValue(row, 10));
                        user.setModeOfStaying(getStringCellValue(row, 11));
                        user.setNewOrExisting(getStringCellValue(row, 12));
                        user.setSourcedBy(getStringCellValue(row, 13));
                        user.setVisaStatus(getStringCellValue(row, 14));
                        user.setMarketingVisaStatus(getStringCellValue(row, 15));
                        user.setContactNumber(getStringCellValue(row, 16));
                        user.setEmailId(getStringCellValue(row, 17));
                        user.setOriginalDob(getDateCellValue(row, 19));
                        user.setMarketingDob(getDateCellValue(row, 20));
                        user.setWhatsappNumber(getStringCellValue(row, 21));
                        user.setMarketingStartDate(getDateCellValue(row, 22));
                        user.setMarketingEndDate(getDateCellValue(row, 23));
                        user.setComments(getStringCellValue(row, 24));
                        return user;
                    })
                    .collect(Collectors.toList());
        }
    }

    public List<DailySubmissionsInfo> readDailySubmissionsExcelFile(MultipartFile file) throws Exception {
        try (var workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(dailySubmissionsSheetIndex);

            return StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1) // Skip the header row
                    .filter(row -> !isEmptyRow(row))
                    .map(row -> {
                        var user = new DailySubmissionsInfo();
                        user.setDateOfEntry(getDateCellValue(row, 0));
                        user.setRecruiterName(getStringCellValue(row, 1));
                        user.setConsultantName(getStringCellValue(row, 2));
                        user.setTechnology(getStringCellValue(row, 3));
                        user.setPriority(getStringCellValue(row, 4));
                        user.setSkill(getStringCellValue(row, 5));
                        user.setAllocatedStatus(getStringCellValue(row, 6));
                        user.setClientType(getStringCellValue(row, 7));
                        user.setClientName(getStringCellValue(row, 8));
                        user.setRequirementSource(getStringCellValue(row, 9));
                        user.setFeedback(getStringCellValue(row, 10));
                        user.setComments(getStringCellValue(row, 24));
                        return user;
                    })
                    .collect(Collectors.toList());
        }
    }

    public List<InterviewInfo> readInterviewsExcelFile(MultipartFile file) throws Exception {
        try (var workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(interviewsSheetIndex);
            return StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1) // Skip the header row
                    .filter(Objects::nonNull)
                    .map(row -> {
                        var interview = new InterviewInfo();
                        interview.setRecruiterName(getStringCellValue(row, 0));
                        interview.setRound(getStringCellValue(row, 1));
                        interview.setInterviewDate(getDateCellValue(row, 2));
                        interview.setInterviewTime(row.getCell(3).toString()); // Changed to use .toString()
                        interview.setConsultantName(getStringCellValue(row, 5));
                        interview.setOwnSupport(row.getCell(6).toString()); // Changed to use .toString()
                        interview.setTechnology(getStringCellValue(row, 7));
                        interview.setClientType(getStringCellValue(row, 9));
                        interview.setClientName(getStringCellValue(row, 10));
                        interview.setLocation(getStringCellValue(row, 11));
                        interview.setRate(getStringCellValue(row, 12));
                        interview.setVendor(getStringCellValue(row, 13));
                        interview.setFeedback(getStringCellValue(row, 14));
                        interview.setComments(getStringCellValue(row, 15));
                        return interview;
                    })
                    .collect(Collectors.toList());
        }
    }
}
