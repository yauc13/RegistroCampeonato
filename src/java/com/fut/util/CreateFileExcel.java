/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.util;

import com.fut.model.Jornada;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.util.CellRangeAddress; //celdas por convinadas excel
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author YEISON URREA
 */
public class CreateFileExcel {
    
    public static String generateFileExcelFixture(List<Jornada> dR, List<String> columnsStatic) {

        String fileExcel = null;

        String ruteFile = "D:\\";
        File fileEx = new File(ruteFile + "_" + new Date().getTime() + ".xlsx");
        if (ruteFile != null) {
            try {
                String sheetName = "Hoja 1";
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet(sheetName);

                //insertar encabezado tabla
                XSSFRow row = sheet.createRow(0);

                for (int i = 0; i < columnsStatic.size(); i++) {
                    XSSFCell cell = row.createCell(i);
                    cell.setCellValue(columnsStatic.get(i));
                }

                //Insertar datos de las personas en cada campo                
                for (int i = 1; i < dR.size() + 1; i++) {

                    row = sheet.createRow(i);

                    for (int j = 0; j < columnsStatic.size(); j++) {

                        XSSFCell cell = row.createCell(j);
                        switch (j) {
                            case 0:
                                cell.setCellValue(dR.get(i - 1).getIdJornada());
                                break;
                            case 1:
                                cell.setCellValue(dR.get(i - 1).getFechaJornada());
                                break;
                            case 2:
                                cell.setCellValue(dR.get(i - 1).getNombreJornada());
                                break;
                          

                        }

                    }

                }

                FileOutputStream fileOut = new FileOutputStream(fileEx.getPath());
                wb.write(fileOut);
                fileOut.flush();
                fileExcel = fileEx.getPath();
                //InputStream stream = new FileInputStream(fileEx.getPath());
                //fileExcel = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "datos reporte dinamico.xlsx");

            } catch (IOException e) {
                System.err.println(e);
                
            }

        } else {
            //Util.setMessage(severity, ruteFile, ruteFile);
        }

        return fileExcel;

    }
}
