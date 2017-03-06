package com.tmg.web;

import com.tmg.util.ExcltoListMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/10.
 */
@Controller
@RequestMapping("/syllabus")
public class SyllabusController {
    public SyllabusController() {
        System.out.println("SyllabusController构造器创建");
    }

    @RequestMapping("/upload.do")
    @ResponseBody
    public JsonResult<List<Map<String, String>>> upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        List<Map<String, String>> syllabus = ExcltoListMap.read(file);
        System.out.println(syllabus.size());
        for (Map<String, String> syllabu : syllabus) {
            System.out.println(syllabu);
        }
        return new JsonResult<List<Map<String, String>>>(syllabus);
    }
}
