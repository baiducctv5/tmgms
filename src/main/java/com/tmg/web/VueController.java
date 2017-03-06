package com.tmg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/15.
 */
@Controller
@RequestMapping("/vue")
public class VueController {
    @RequestMapping("/getMsg")
    @ResponseBody
    public Map<String, Object> getMsg(String id) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idmsg", "id====" + id);
        System.out.println("gett");
        m.put("data", "getMsg de data");
        return m;
    }

    @RequestMapping("/postMsg")
    @ResponseBody
    public Map<String, Object> postMsg(String id, String name) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idmsg", "id====" + id);
        m.put("namemsg", "name====" + name);
        System.out.println(id + "----" + name);
        m.put("data", "postMsg de data");
        return m;
    }

}
