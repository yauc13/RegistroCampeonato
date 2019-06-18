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
import java.text.SimpleDateFormat;
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
    
    public static String generateFileExcelFixture(List<Jornada> data, List<String> columnsStatic,String nameFile) {

        String fileExcel = null;

        String ruteFile = "D:\\";
        File fileEx = new File(ruteFile +nameFile+ ".xlsx");
        if (ruteFile != null) {
            try {
                String sheetName = "Hoja 1";
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet(sheetName);

                //insertar encabezado tabla
                XSSFRow row = sheet.createRow(0);
/*
                for (int i = 0; i < columnsStatic.size(); i++) {
                    XSSFCell cell = row.createCell(i);
                    cell.setCellValue(columnsStatic.get(i));
                } */
                int initCel = 1;
                int rowIndex=1;
                XSSFCell cell;
                //Insertar datos de las jornadas                
                for (int i = initCel; i < data.size() + initCel; i++) {

                    row = sheet.createRow(rowIndex);
                    //cell = row.createCell(0);
                    //cell.setCellValue(data.get(i - initCel).getFechaJornada());
                    /*recorre las columnas de jornada*/
                    for (int j = 0; j < 3; j++) {

                        cell = row.createCell(j);
                        switch (j) {
                            case 0:
                                cell.setCellValue(data.get(i - initCel).getIdJornada());
                                break;
                            case 1:
                                cell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(data.get(i - initCel).getFechaJornada()));
                                break;
                            case 2:
                                cell.setCellValue(data.get(i - initCel).getNombreJornada());
                                break;
                          

                        }

                    }
                    rowIndex++;
                    /*recorre los partidos*/
                    for (int p = 0; p < data.get(i - initCel).getListMatch().size(); p++) {
                        row = sheet.createRow(rowIndex);
                        
                        for (int cp = 0; cp < 4; cp++) {
                            
                            cell = row.createCell(cp);
                            switch (cp) {
                                case 0:
                                    cell.setCellValue(new SimpleDateFormat(" hh:mm a").format(new Date(
                                            data.get(i - initCel).getListMatch().get(p).getFechaPartido().getTime())));
                                    break;
                                case 1:
                                    cell.setCellValue(data.get(i - initCel).getListMatch().get(p).getEquipoA().getNombreEquipo());
                                    break;
                                case 2:
                                    cell.setCellValue(data.get(i - initCel).getListMatch().get(p).getGolA()+" - "+
                                            data.get(i - initCel).getListMatch().get(p).getGolB());
                                    break;
                                case 3:
                                    cell.setCellValue(data.get(i - initCel).getListMatch().get(p).getEquipoB().getNombreEquipo());
                                    break;

                            }

                        }
                        rowIndex++;
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
