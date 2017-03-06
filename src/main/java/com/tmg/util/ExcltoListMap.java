package com.tmg.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/2/9.
 * 从16-17-1化工总课表（横向）.xsl中导入数据
 */
public class ExcltoListMap {
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
     * 将指定的excel文件导入，生成集合
     *
     * @param file springMVC 上传来的excle文件
     * @return excel提取后封装的集合数据 或者 空的集合
     * @throws Exception
     */

    //计划把String的类型转为File类型的，目前只是改了方法参数，后续实现还没弄
    public static List<Map<String, String>> read(MultipartFile file) {
        List<Map<String, String>> excelData = new ArrayList<Map<String, String>>();
        InputStream stream = null;
        Workbook wb = null;
        try {
            String fileName = file.getOriginalFilename();
            //提取文件路径的文件扩展名部分，进行文件类型判断
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            stream = file.getInputStream();
            if (fileType.equals("xls")) {
                try {
                    wb = new HSSFWorkbook(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (fileType.equals("xlsx")) {
                try {
                    wb = new XSSFWorkbook(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    throw new Exception("该excel文件后缀格式有问题，不是一个excel文件");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            //获取第一个sheet单页（可视工作表）
            Sheet sheet1 = wb.getSheetAt(0);
            //迭代得到的sheet对象，获得该对象 每行内容的 迭代集 rows
            Iterator<Row> rows = sheet1.rowIterator();
            Row row = null;
            List<String> title = new ArrayList<String>();
            // 判断可视工作表中是否有行数据(迭代可以忽略空行，只迭代有数据的行)
            if (!rows.hasNext()) {
                try {
                    throw new Exception("该excel文件有问题“excel是有sheet但是sheet中没有内容，包括模版自带的标题头”，请下载模版并按照格式填写");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //将坐标下移，获取“重庆工业职业技术学院2016-2017学年第1学期总课表”
            Row firRow = rows.next();
            String msg = firRow.getCell(0).getStringCellValue();
            System.out.println(msg.substring(0, 10) + "——" + msg.substring(10, msg.length() - 3) + "——" + msg.substring(msg.length() - 3, msg.length()));

            //将坐标下移一位，并获得表头这一行(第二行)
            Row secRow = rows.next();
            //迭代表头行，并获得该行的单元格cell迭代集
            Iterator<Cell> cells = secRow.cellIterator();
            //遍历第一行的所有单元格cell的内容，并加入到表头集合中
            while (cells.hasNext()) {
                Cell cell = cells.next();
                title.add(getCellVal(cell));
            }
            System.out.println("表头是：" + title);
            Map<String, String> maps = null;
            //遍历除去表头的其他行并结合表头存入到List中去
            while (rows.hasNext()) {
                maps = new HashMap<String, String>();
                row = rows.next();
                //因迭代会忽略空单元格，所以替换为for循环。
                for (int i = 0; i < title.size(); i++) {
                    maps.put(title.get(i), getCellVal(row.getCell(i)));
                }
                excelData.add(maps);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excelData;
    }
}
