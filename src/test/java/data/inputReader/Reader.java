package data.inputReader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Reader {
    private  String FILE_NAME;
    private ArrayList<String> keywordList = new ArrayList<>();
    private String[] buffer;

    public Reader(String FILE_NAME) throws IOException {
        this.FILE_NAME = FILE_NAME;
    }

    public HashMap<String, ArrayList<String>> getHashMapFromXlsxFile() throws IOException {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        DataFormatter formatter = new DataFormatter();

        FileInputStream file = new FileInputStream(new File(FILE_NAME).getAbsolutePath());
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        List<String> columnNames = new ArrayList<>();
        for (Iterator<Cell> iterator = row.cellIterator(); iterator.hasNext();) {
            Cell cell = iterator.next();
            String value = formatter.formatCellValue(cell);
            columnNames.add(value);
        }
        Iterator<Row> iterator = sheet.iterator();
        if (iterator.hasNext()) {
            iterator.next();
        }
        List<String> rows = new ArrayList<>();
        while (iterator.hasNext()) {
            row = iterator.next();
            for (int i = 0; i < columnNames.size(); i++) { // name нулевой
                String key = columnNames.get(i);
                Cell cell = row.getCell(i);
                String value = formatter.formatCellValue(cell);
                if (isNotEmpty(key) && isNotEmpty(value)){
                    if (result.containsKey(key)){
                        result.get(key).add(value);
                    }
                    else{
                        ArrayList<String> values = new ArrayList<>();
                        values.add(value);
                        result.put(key, values);
                    }
                }
            }
        }
        return result;
    }

    private Boolean isNotEmpty(String value){
        return value.trim().length() > 0;
    }

}
