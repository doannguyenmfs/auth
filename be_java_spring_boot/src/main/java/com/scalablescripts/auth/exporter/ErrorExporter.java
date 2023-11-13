package com.scalablescripts.auth.exporter;

import ch.qos.logback.core.OutputStreamAppender;
import com.scalablescripts.auth.model.Error;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ErrorExporter {
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    List<Error> errorList;

    public ErrorExporter(List<Error> errorList) {
        this.errorList = errorList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("error");

    }

    private void writeHeaderRows() {
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);
        cellStyle.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());

        cellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
        // and solid fill pattern produces solid grey cell fill
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        Row row = sheet.createRow(1);

        Cell cell =  row.createCell(1);
        cell.setCellValue("id");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue("err Code");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue("error Message");
        cell.setCellStyle(cellStyle);
    }

    private void writeDataRows() {
        for (int i=0; i<this.errorList.size(); i++) {
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);

            Row row = sheet.createRow(i+2);
            Cell cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(this.errorList.get(i).getId());

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(this.errorList.get(i).getErrCode());

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(this.errorList.get(i).getErrMessage());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRows();
        writeDataRows();

        ServletOutputStream servletOutputStream = response.getOutputStream();

        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }
}
