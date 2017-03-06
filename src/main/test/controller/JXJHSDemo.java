package controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import pojo.KcVO;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/28.
 * C:\Users\Administrator\Desktop\16-17学年第2学期教学任务书（2017年2月19日）.xls 教师课程表的数据分析
 * 横向有28列数据
 * 年级	专业名称	课程类别	开课学院	课程性质	课程代码	课程名称	课程简称	班级名称	人数	上课教师
 * 总学时	讲课学时	周学时	授课周数	起止周	几节连排	考核方式	教室要求	教学班名称	学分	实验学时
 * 上机学时	课程实践学时	习题课学时	课内上机学时	课外上机学时	课外学时	教学计划号	选课课号	板块等级
 * 平行班标识	教师职工号	实验容量	最大实验班班数	实验课是否要教室	是否要机房	教材名称	作者	出版社	书刊号
 * 版别	征订代号	单价	专业方向	实验场地	单双周	校区要求	授课方式名称	权重系数	备注	组号名称	组号代码
 * 模块名称	模块代码	临时教学班	专业所属学院
 */
public class JXJHSDemo {

    @Test
    public void getTest() {
        Map<String, Map<KcVO, Map<Integer, String>>> maps = new HashMap<String, Map<KcVO, Map<Integer, String>>>();

        File file = new File("C:\\Users\\Administrator\\Desktop\\16-17学年第2学期教学任务书（2017年2月19日）.xls");
        InputStream is = null;
        Workbook wb = null;
        Sheet sheet = null;
        try {
            is = new FileInputStream(file);
            wb = new HSSFWorkbook(is);
            sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i < lastRowNum; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < 28; j++) {
                    System.out.print(sheet.getRow(i).getCell(j) + " ");
                }
                System.out.println();
            }
            wb.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
