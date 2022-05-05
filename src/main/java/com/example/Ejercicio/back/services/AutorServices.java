package com.example.Ejercicio.back.services;

import com.example.Ejercicio.back.model.Autor;
import com.example.Ejercicio.back.repository.AutorRepo;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@Transactional
public class AutorServices {
    private AutorRepo autorRepo;
    private final String error = "No se ha encontrado al autor";

    @Autowired
    public AutorServices(AutorRepo autorRepo){
        this.autorRepo = autorRepo;
    }

    public Autor addAutor(Autor autor){
        return autorRepo.save(autor);
    }

    public List<Autor> findAllAutors(){
        return autorRepo.findAll();
    }

    public Autor findAutorByDni(String dni){
        return autorRepo.findAutorByDni(dni).orElseThrow(() -> new IllegalArgumentException(error));
    }

    public void deleteAutor(String dni){
        autorRepo.deleteAutorByDni(dni);
    }

    public Autor updateAutor(Autor autor){
        return autorRepo.save(autor);
    }

    public ByteArrayInputStream autorExcel(List<Autor> autorList) throws Exception {
        String[] columns = {"DNI", "Nombre", "Primer Apellido", "Segundo Apellido", "Email", "Telefono"};

        Workbook workbook = new XSSFWorkbook();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Autores");
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 5500);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 3500);

        Row headerRow = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);


        for (int i=0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowIndex = 1;
        for(Autor autor: autorList) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(autor.getDni());
            row.createCell(1).setCellValue(autor.getNombre());
            row.createCell(2).setCellValue(autor.getApellido1());
            row.createCell(3).setCellValue(autor.getApellido2());
            row.createCell(4).setCellValue(autor.getEmail());
            row.createCell(5).setCellValue(autor.getTelefono());
            rowIndex++;
        }

        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

}
