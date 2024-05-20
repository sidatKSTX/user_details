package com.learnspring.userdetailsapi.service;

import com.learnspring.userdetailsapi.model.UserInfo;
import org.apache.poi.ss.usermodel.CellType;
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

            return StreamSupport.stream(sheet.spliterator(), false) //converts the sheet into a Java 8 stream
                    .skip(1) // Skip the header row
                    .map(row -> {
                        var user = new UserInfo();
                        user.setRecruiterName(row.getCell(0).getStringCellValue());
                        user.setConsultantName(row.getCell(1).getStringCellValue());

                        try {
                            user.setAssignedUnAssignedStatus(UserInfo.AssignedUnAssignedStatus.valueOf(row.getCell(2)
                                    .getStringCellValue().trim().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            user.setAssignedUnAssignedStatus(UserInfo.AssignedUnAssignedStatus.UNKNOWN);
                        }

                        try {
                            user.setStatus(UserInfo.Status.valueOf(row.getCell(3).getStringCellValue().trim().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            user.setStatus(UserInfo.Status.UNKNOWN);
                        }

                        try {
                            if (row.getCell(4) == null || row.getCell(4).getCellType() == CellType.BLANK) {
                                user.setTurboCheck(null);
                            } else if (row.getCell(4).getCellType() == CellType.NUMERIC) {
                                user.setTurboCheck((int) row.getCell(4).getNumericCellValue());
                            } else {
                                user.setTurboCheck(Integer.parseInt(row.getCell(4).getStringCellValue().trim()));
                            }
                        } catch (NumberFormatException e) {
                            user.setTurboCheck(null);
                        }
                        user.setPriority(row.getCell(5).getStringCellValue());
                        user.setTechnology(row.getCell(6).getStringCellValue());
                        user.setOrg(row.getCell(7).getStringCellValue());
                        try {
                            if (row.getCell(8).getCellType() == CellType.NUMERIC) {
                                user.setExperience((int) row.getCell(8).getNumericCellValue());
                            } else {
                                user.setExperience(Integer.parseInt(row.getCell(8).getStringCellValue().trim()));
                            }
                        } catch (NumberFormatException | IllegalStateException e) {
                            user.setExperience(null); // or any default value you'd prefer
                        }
                        user.setLocation(row.getCell(9).getStringCellValue());
                        try {
                            if (row.getCell(10).getCellType() == CellType.BOOLEAN) {
                                user.setRelocation(row.getCell(10).getBooleanCellValue());
                            } else {
                                user.setRelocation(Boolean.parseBoolean(row.getCell(10).getStringCellValue().trim()));
                            }
                        } catch (IllegalStateException e) {
                            user.setRelocation(false); // or any default value you'd prefer
                        }
                        user.setGuestHouseOrRemote(row.getCell(11).getStringCellValue());

                        try {
                            user.setNewOrExisting(UserInfo.NewOrExisting.valueOf(row.getCell(12)
                                    .getStringCellValue().trim().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            user.setNewOrExisting(UserInfo.NewOrExisting.UNKNOWN);
                        }

                        user.setSourcedBy(row.getCell(13).getStringCellValue());
                        user.setOriginalVisaStatus(row.getCell(14).getStringCellValue());
                        user.setMarketingVisaStatus(row.getCell(15).getStringCellValue());

                        if (row.getCell(16).getCellType() != null && !row.getCell(16).getStringCellValue().isEmpty()) {
                            user.setContactNumber(row.getCell(16).getStringCellValue());
                        } else {
                            user.setContactNumber(null);
                        }
                        user.setConsultantsEmailId(row.getCell(17).getStringCellValue());
                        try {
                            if (row.getCell(18) == null || row.getCell(18).getCellType() == CellType.BLANK) {
                                user.setRate(null);
                            } else if (row.getCell(18).getCellType() == CellType.NUMERIC) {
                                user.setRate((int) row.getCell(18).getNumericCellValue());
                            } else if (row.getCell(18).getCellType() == CellType.STRING) {
                                String rateString = row.getCell(18).getStringCellValue().trim();
                                if (!rateString.isEmpty()) {
                                    user.setRate(Integer.parseInt(rateString));
                                } else {
                                    user.setRate(null);
                                }
                            }
                        } catch (NumberFormatException e) {
                            user.setRate(null);
                        }


                        try {
                            if (row.getCell(19) == null || row.getCell(19).getCellType() == CellType.BLANK) {
                                user.setOriginalDob(null);
                            } else if (row.getCell(19).getCellType() == CellType.NUMERIC) {
                                user.setOriginalDob(row.getCell(19).getLocalDateTimeCellValue().toLocalDate());
                            } else {
                                String dateString = row.getCell(19).getStringCellValue().trim();
                                if (dateString.isEmpty()) {
                                    user.setOriginalDob(null);
                                } else {
                                    user.setOriginalDob(LocalDate.parse(dateString));
                                }
                            }
                        } catch (Exception e) {
                            user.setOriginalDob(null);
                        }
                        try {
                            if (row.getCell(20) == null || row.getCell(20).getCellType() == CellType.BLANK) {
                                user.setMarketingDob(null);
                            } else if (row.getCell(20).getCellType() == CellType.NUMERIC) {
                                user.setMarketingDob(row.getCell(20).getLocalDateTimeCellValue().toLocalDate());
                            } else {
                                String dateString = row.getCell(20).getStringCellValue().trim();
                                if (dateString.isEmpty()) {
                                    user.setMarketingDob(null);
                                } else {
                                    user.setMarketingDob(LocalDate.parse(dateString));
                                }
                            }
                        } catch (Exception e) {
                            user.setMarketingStartDate(null);
                        }

                        if (row.getCell(21).getCellType() != null && !row.getCell(21).getStringCellValue().isEmpty()) {
                            user.setWhatsappNumber(row.getCell(21).getStringCellValue());
                        } else {
                            user.setWhatsappNumber(null);
                        }

                        try {
                            if (row.getCell(22) == null || row.getCell(22).getCellType() == CellType.BLANK) {
                                user.setMarketingStartDate(null);
                            } else if (row.getCell(22).getCellType() == CellType.NUMERIC) {
                                user.setMarketingStartDate(row.getCell(22).getLocalDateTimeCellValue().toLocalDate());
                            } else {
                                String dateString = row.getCell(22).getStringCellValue().trim();
                                if (dateString.isEmpty()) {
                                    user.setMarketingStartDate(null);
                                } else {
                                    user.setMarketingStartDate(LocalDate.parse(dateString));
                                }
                            }
                        } catch (Exception e) {
                            user.setMarketingStartDate(null);
                        }

                        if (row.getCell(23) == null || row.getCell(23).getCellType() == CellType.BLANK) {
                            user.setMarketingEndDate(null);
                        } else if (row.getCell(23).getCellType() == CellType.NUMERIC) {
                            user.setMarketingEndDate(row.getCell(23).getLocalDateTimeCellValue().toLocalDate());
                        } else {
                            user.setMarketingEndDate(LocalDate.parse(row.getCell(23).getStringCellValue()));
                        }
                        user.setComments(row.getCell(24).getStringCellValue());
                        return user;
                    })
                    .collect(Collectors.toList());
        }
    }
}
