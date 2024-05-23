package com.learnspring.userdetailsapi.service;

import com.learnspring.userdetailsapi.model.UserInfo;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExcelReaderService {
    public List<UserInfo> readExcelFile(MultipartFile file) throws Exception {
        try (var workbook = new XSSFWorkbook(file.getInputStream())) {
            var sheet = workbook.getSheetAt(0); // Assuming first sheet

            return StreamSupport.stream(sheet.spliterator(), false) // Convert the sheet into a Java 8 stream
                    .skip(1) // Skip the header row
                    .map(row -> {
                        var user = new UserInfo();
                        user.setRecruiterName(getStringCellValue(row, 0));
                        user.setConsultantName(getStringCellValue(row, 1));
                        user.setAllocatedStatus(getStringCellValue(row,2));
                        user.setStatus(getStringCellValue(row,3));
                        user.setTurboCheck(getIntegerCellValue(row, 4));
                        user.setPriority(getStringCellValue(row, 5));
                        user.setTechnology(getStringCellValue(row, 6));
                        user.setOrganization(getStringCellValue(row, 7));
                        user.setExperience(getIntegerCellValue(row, 8));
                        user.setLocation(getStringCellValue(row, 9));
                        user.setRelocation(getStringCellValue(row, 10));
                        user.setModeOfStaying(getStringCellValue(row, 11));
                        user.setNewOrExisting(getStringCellValue(row,12));
                        user.setSourcedBy(getStringCellValue(row, 13));
                        user.setVisaStatus(getStringCellValue(row, 14));
                        user.setMarketingVisaStatus(getStringCellValue(row, 15));
                        user.setContactNumber(getStringCellValue(row, 16));
                        user.setEmailId(getStringCellValue(row, 17));
                        user.setRate(getIntegerCellValue(row, 18));
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

    private String getStringCellValue(Row row, int cellIndex) {
        var cell = row.getCell(cellIndex);
        return cell != null ? cell.getStringCellValue().trim() : null;
    }

    private Integer getIntegerCellValue(Row row, int cellIndex) {
        var cell = row.getCell(cellIndex);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else {
            try {
                return Integer.parseInt(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    private LocalDate getDateCellValue(Row row, int cellIndex) {
        var cell = row.getCell(cellIndex);
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else {
            try {
                return LocalDate.parse(cell.getStringCellValue().trim());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
