package com.example.Ejercicio.back.services;

import com.example.Ejercicio.back.model.Categoria;
import com.example.Ejercicio.back.model.Libro;
import com.example.Ejercicio.back.repository.CategoriaRepo;
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
    public class CategoriaServices{
        private CategoriaRepo categoriaRepo;
        private final String error = "No se ha encontrado al Categoria";

        @Autowired
        public CategoriaServices(CategoriaRepo categoriaRepo){
            this.categoriaRepo = categoriaRepo;
        }

        public Categoria addCategoria(Categoria categoria){
            return categoriaRepo.save(categoria);
        }

        public List<Categoria> findAllCategorias(){
            return categoriaRepo.findAll();
        }

        public Categoria findCategoriaById(Long id){
            return categoriaRepo.findCategoriaById(id).orElseThrow(() -> new IllegalArgumentException(error));
        }

        public void deleteCategoria(Long id){
            categoriaRepo.deleteCategoriaById(id);
        }

        public Categoria updateCategoria(Categoria categoria){
            return categoriaRepo.save(categoria);
        }

    public ByteArrayInputStream categoriaExcel(List<Categoria> list) throws Exception {
        String[] columns = {"ID", "Descripción"};

        Workbook workbook = new XSSFWorkbook();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Libros");
        sheet.setColumnWidth(0, 1000);
        sheet.setColumnWidth(1, 5500);

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
        for(Categoria categoria: list) {
            Row row = sheet.createRow(rowIndex);
            if(categoria.getId() != 1) {
                row.createCell(0).setCellValue(categoria.getId());
                row.createCell(1).setCellValue(categoria.getDescripcion());
                rowIndex++;
            }
        }

        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}

