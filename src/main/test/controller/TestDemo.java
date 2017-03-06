package controller;

import com.tmg.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import pojo.KcVO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 * C:\Users\Administrator\Desktop\教师课表_郑渝英.xlsx 教师课程表的数据分析
 */
public class TestDemo {

    @Test
    public void getTest() {
        Map<String, Map<KcVO, Map<Integer, String>>> maps = new HashMap<String, Map<KcVO, Map<Integer, String>>>();

        File file = new File("C:\\Users\\Administrator\\Desktop\\教师课表_郑渝英.xlsx");
        InputStream is = null;
        Workbook wb = null;
        Sheet sheet = null;
        try {
            is = new FileInputStream(file);
            wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(0);

            for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                CellRangeAddress region = sheet.getMergedRegion(i); //
                int colIndex = region.getFirstColumn();             // 合并区域首列位置
                int rowNum = region.getFirstRow();                     // 合并区域首行位置
                //System.out.println("第"+i+"个格子，坐标[" + rowNum +","+colIndex+ "]数据为：" +  sheet.getRow(rowNum).getCell(colIndex).getStringCellValue());
            }
            //坐标[1,0]数据为：   教师:郑渝英  院系:财经学院  教研室:  性别:女  学期:2016-2017-2
            //                                              2017年02月28日
            // ij       1       2       3       4       5       6       7
            //0102  04.03   04.06   04.09   04.12   04.15   04.18   04.21
            //          1(0.0)  7(0.1)  14(0.2) 21(0.3) 35(0.4) 42(0.5) 49(0.6)
            //0304  05.03   05.06   05.09   05.12   05.15   05.18   05.21
//                      2(1.0)  8(1.1)  15(1.2) 22(1.3) 36(1.4) 43(1.5) 50(1.6)
//                                         i+1 + j*7
//
//
            //0506  06.03   06.06   06.09   06.12   06.15   06.18   06.21

            //0708  07.03   07.06   07.09   07.12   07.15   07.18   07.21

            //0910  08.03   08.06   08.09   08.12   08.15   08.18   08.21

            //1112  09.03   09.06   09.09   09.12   09.15   09.18   09.21
            //
            Map<Integer, String> kbmaps = new HashMap<Integer, String>();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    kbmaps.put(i + 1 + 7 * j, ExcelUtil.getCellVal(sheet.getRow(i + 4).getCell((j + 1) * 3)));
                }
            }
            System.out.println(kbmaps.size());
            System.out.println(kbmaps);
            //教师课程表中所教的所有班级的信息（全盘遍历，为空的不add到list中）
            //11.00 11.03 11.04 11.05   11.06 11.09 11.10 11.11    11.12 11.15 11.16 11.17    11.18 11.21 11.22 11.23
            //12.00 12.03 12.04 12.05   12.06 12.09 12.10 12.11    12.12 12.15 12.16 12.17    12.18 12.21 12.22 12.23
            //13.00 11.03 11.04 11.05   11.06 11.09 11.10 11.11    11.12 11.15 11.16 11.17    11.18 11.21 11.22 11.23
            //14.00 11.03 11.04 11.05   11.06 11.09 11.10 11.11    11.12 11.15 11.16 11.17    11.18 11.21 11.22 11.23

            //获取所教的所有班级的信息
            List<KcVO> kcs = new ArrayList<KcVO>();
            KcVO kc = null;
            for (int i = 11; i < 15; i++) {
                for (int j = 0; j < 4; j++) {
                    if (ExcelUtil.getCellVal(sheet.getRow(i).getCell(j * 6)) != null && !ExcelUtil.getCellVal(sheet.getRow(i).getCell(j * 6)).equals("")) {
                        kc = new KcVO();
                        kc.setKcname(ExcelUtil.getCellVal(sheet.getRow(i).getCell(j * 6)));
                        kc.setTypeScore(ExcelUtil.getCellVal(sheet.getRow(i).getCell(j * 6 + 3)));
                        kc.setsTime(ExcelUtil.getCellVal(sheet.getRow(i).getCell(j * 6 + 4)));
                        kc.setClassName(ExcelUtil.getCellVal(sheet.getRow(i).getCell(j * 6 + 5)));
                        kcs.add(kc);
                    }
                }
            }
            System.out.println(kcs.size());
            System.out.println(kcs);

            wb.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
