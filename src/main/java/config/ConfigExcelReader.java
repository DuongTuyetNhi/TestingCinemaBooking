package config;

import base.DriverManagement;
import models.Movie;
import models.User;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigExcelReader {
    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File doesn't exist, so created!");
            }

            fis = new FileInputStream(ExcelPath);
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(SheetName);
            if (sh == null) {
                sh = wb.createSheet(SheetName);
            }

            this.excelFilePath = ExcelPath;

            sh.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public String getCellData(int rownum, int colnum) throws Exception{
//        try{
//            cell = sh.getRow(rownum).getCell(colnum);
//            String CellData = null;
//            switch (cell.getCellType()){
//                case STRING:
//                    CellData = cell.getStringCellValue();
//                    break;
//                case NUMERIC:
//                    if (DateUtil.isCellDateFormatted(cell))
//                    {
//                        CellData = String.valueOf(cell.getDateCellValue());
//                    }
//                    else
//                    {
//                        CellData = String.valueOf((long)cell.getNumericCellValue());
//                    }
//                    break;
//                case BOOLEAN:
//                    CellData = Boolean.toString(cell.getBooleanCellValue());
//                    break;
//                case BLANK:
//                    CellData = "";
//                    break;
//            }
//            return CellData;
//        }catch (Exception e){
//            return"";
//        }
//    }

    //Gọi ra hàm này nè
//    public String getCellData(String columnName, int rownum) throws Exception {
//        return getCellData(rownum, columns.get(columnName));
//    }

//    public String getCellData(String columnName, int rowNum) throws Exception {
//        Integer colIndex = columns.get(columnName);
//        if (colIndex == null) {
//            throw new IllegalArgumentException("Không tìm thấy cột với tên: " + columnName);
//        }
//        Cell cell = sh.getRow(rowNum).getCell(colIndex);
//        if (cell == null) {
//            return "";
//        }
//
//        String cellData = "";
//
//        switch (cell.getCellType()) {
//            case STRING:
//                cellData = cell.getStringCellValue();
//                break;
//            case NUMERIC:
//                if (DateUtil.isCellDateFormatted(cell)) {
//                    java.util.Date date = cell.getDateCellValue();
//                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");
//                    cellData = sdf.format(date);
//                } else {
//                    cellData = String.valueOf((long) cell.getNumericCellValue());
//                }
//                break;
//            case BOOLEAN:
//                cellData = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case BLANK:
//                cellData = "";
//                break;
//            default:
//                cellData = "";
//        }
//        return cellData;
//    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    public String getCellData(String columnName, int rowNum) throws Exception {
        Integer colIndex = columns.get(columnName);
        if (colIndex == null) {
            throw new IllegalArgumentException("Không tìm thấy cột với tên: " + columnName);
        }

        Row row = sh.getRow(rowNum);
        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(colIndex);
        return getCellValue(cell);
    }

    public List<String> getColumnData(String columnName) throws Exception {
        List<String> dataList = new ArrayList<>();
        Integer colIndex = columns.get(columnName);
        if (colIndex == null) {
            throw new IllegalArgumentException("Không tìm thấy cột với tên: " + columnName);
        }

        int lastRowNum = sh.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sh.getRow(i);
            if (row != null) {
                Cell cell = row.getCell(colIndex);
                dataList.add(getCellValue(cell));
            } else {
                dataList.add("");
            }
        }

        return dataList;
    }

    public void setCellData(String text, int rownum, int colnum) throws Exception {
        try{
            row  = sh.getRow(rownum);
            if(row ==null)
            {
                row = sh.createRow(rownum);
            }
            cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }
            cell.setCellValue(text);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }catch(Exception e){
            throw (e);
        }
    }

    public List<Integer> getRowsByType(String desiredType) throws Exception {
        List<Integer> rowIndexes = new ArrayList<>();
        int lastRowNum = sh.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {
            String type = getCellData("type", i);
            if (type.equalsIgnoreCase(desiredType)) {
                rowIndexes.add(i);
            }
        }
        return rowIndexes;
    }
    public User getUserFromExcel(int rowNum) throws Exception {
        User user = new User();
        user.setFirstName(getCellData("firstName", rowNum));
        user.setLastName(getCellData("lastName", rowNum));
        user.setDOB(getCellData("birthDay", rowNum));
        user.setPhone(getCellData("phone", rowNum));
        user.setEmail(getCellData("email", rowNum));
        user.setAddress(getCellData("address", rowNum));
        user.setPassword(getCellData("password", rowNum));
        user.setConfirmPassword(getCellData("confirmPassword", rowNum));
        return user;
    }

    public Map<String, String> getLoginAccountFromExcel(int rowNum) throws Exception {
        Map<String, String> account = new HashMap<>();
        account.put("email", getCellData("email", rowNum));
        account.put("password", getCellData("password", rowNum));
        return account;
    }

    public List<String> getCategoryListFromExcel() throws Exception {
        return getColumnData("Category");
    }

    public Movie getMovieFromExcel(int rowNum) throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx","ThemPhim");

        Movie movie = new Movie();
        movie.setMovieName(getCellData("movieName", rowNum));
        movie.setPhotoPath(getCellData("photo", rowNum));
        movie.setNation(getCellData("nation", rowNum));
        movie.setTimeSlot(getCellData("timeSlot", rowNum));
        movie.setCategory(getCellData("category", rowNum));
        movie.setDirector(getCellData("director", rowNum));
        movie.setProducer(getCellData("producer", rowNum));
        movie.setActor(getCellData("actor", rowNum));
        movie.setTrailer(getCellData("trailer", rowNum));
        movie.setDescribeMovie(getCellData("describeMovie", rowNum));

        return movie;
    }

}
