package com.example.Ejercicio.back.services;

import com.example.Ejercicio.back.dto.LibroDTO;
import com.example.Ejercicio.back.model.Autor;
import com.example.Ejercicio.back.model.Libro;
import com.example.Ejercicio.back.repository.LibroRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.util.List;
@Transactional
@Service
public class LibroServices {
    private LibroRepo libroRepo;
    private final String error = "No se ha encontrado al Libro";

    @Autowired
    public LibroServices(LibroRepo libroRepo) {
        this.libroRepo = libroRepo;
    }

    public Libro addLibro(Libro libro){
        return libroRepo.save(libro);
    }

    public List<Libro> findAllLibros(){
        return libroRepo.findAll();
    }

    public Libro findLibroById(Long id){
        return libroRepo.findLibroById(id).orElseThrow(() -> new IllegalArgumentException(error));
    }

    public void deleteLibro(Long id){
        libroRepo.deleteLibroById(id);
    }

    public Libro updateLibro(Libro libro){
        return libroRepo.save(libro);
    }

    public List<Libro> findLibroByAutorId(String dni){
        return libroRepo.findLibroByAutorDni(dni);
    }

    public List<Libro> findLibroByCategoriaId(Long id){
        return libroRepo.findLibroByCategoriaId(id);
    }

    public ByteArrayInputStream libroExcel(List<Libro> list) throws Exception{
        String[] columns = {"ID", "Titulo", "Edicion", "Autor", "Categoria"};

        Workbook workbook = new XSSFWorkbook();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Libros");
        sheet.setColumnWidth(0, 1000);
        sheet.setColumnWidth(1, 5500);
        sheet.setColumnWidth(2, 2500);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4500);

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
        for(Libro libro: list) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(libro.getId());
            row.createCell(1).setCellValue(libro.getTitulo());
            row.createCell(2).setCellValue(libro.getEdicion());
            if(libro.getAutor() != null) {
                row.createCell(3).setCellValue(libro.getAutor().getNombre() + " " + libro.getAutor().getApellido1());
            } else {
                row.createCell(3).setCellValue("Desconocido");
            }
            row.createCell(4).setCellValue(libro.getCategoria().getDescripcion());
            rowIndex++;
        }

        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}

