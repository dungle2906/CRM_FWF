package com.example.BasicCRM_FWF.Service;

import com.example.BasicCRM_FWF.DTO.SalesOrder;
import com.example.BasicCRM_FWF.Repository.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepo;
    private final FacilityRepo facilityRepo;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final PaymentMethodRepo paymentMethodRepo;
    private final ServicePackageRepo servicePackageRepo;
    private final CostOnOrderRepo costOnOrderRepo;

    public void importFromExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 2; i <= sheet.getLastRowNum(); i++) { // dòng 2 trở đi
                Row row = sheet.getRow(i);
                if (row == null) continue;

                SalesOrder order = SalesOrder.builder()
                        .orderCode(getCellValue(row.getCell(1))) // MÃ ĐƠN HÀNG
                        .facilityName(getCellValue(row.getCell(2))) // CƠ SỞ
                        .orderDateTime(getCellValue(row.getCell(3))) // NGÀY
                        .customerName(getCellValue(row.getCell(5)))
                        .phoneNumber(getCellValue(row.getCell(6)))
                        .originalPrice(toBigDecimal(row.getCell(7)))
                        .discount(toBigDecimal(row.getCell(8)))
                        .finalAmount(toBigDecimal(row.getCell(9)))
                        .note(getCellValue(row.getCell(25)))
                        .details(getCellValue(row.getCell(26)))
                        .build();

                fileRepository.save(order);
            }

        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException("Error reading Excel file", e);
        }
    }

    private String getCellValue(Cell cell) {
        return (cell == null) ? null : cell.toString().trim();
    }

    private BigDecimal toBigDecimal(Cell cell) {
        if (cell == null) return BigDecimal.ZERO;
        try {
            return new BigDecimal(cell.toString().trim());
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }
}
