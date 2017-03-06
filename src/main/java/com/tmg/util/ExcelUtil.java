package com.tmg.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/2/28.
 * excel相关的工具方法
 */
public class ExcelUtil {
    /**
     * 匹配excel中 不同类型的数据，进而使用相对应的提取方法，获得具体内容数据<br>
     * 如手机号码在excel中为数字类型，姓名为字符串类型 ，true false为布尔类型<br>
     *
     * @param cell 单元格对象
     * @return 单元格的内容字符串
     */
    public static String getCellVal(Cell cell) {
        String cellValue = "";
        if (cell == null || cell.equals("") || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            return cellValue;
        }
        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                //解决手机号码转换失败
                cellValue = df.format(cell.getNumericCellValue()).toString();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }

    /**
     * 判断文件格式
     *
     * @param file
     * @return
     */
    public static Workbook getWorkbookByFile(File file) {
        InputStream stream = null;
        Workbook wb = null;
        try {
            String fileName = file.getName();
            //提取文件路径的文件扩展名部分，进行文件类型判断
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            stream = new FileInputStream(file);
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(stream);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(stream);
            } else {
                throw new Exception("该excel文件后缀格式有问题，不是一个excel文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
                System.out.println("wb close");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stream.close();
                System.out.println("stream close");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    public static void getMByStr() {

    }

    @Test
    public void test() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\教师课表_郑渝英.xlsx");
        getWorkbookByFile(file);
    }
}
