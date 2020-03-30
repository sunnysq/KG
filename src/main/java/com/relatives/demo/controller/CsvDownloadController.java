package com.relatives.demo.controller;

import com.relatives.demo.service.CsvDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/csvDownload")
public class CsvDownloadController {

    @Autowired
    private CsvDownloadService csvDownloadService;
    /**
     * @param tableName     要导出的表名
     * @param response
     */
    @RequestMapping("/download")
    public String ToCsv(@RequestParam(value = "tableName", required = false) String tableName , HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
       System.out.println(tableName);
        String result = csvDownloadService.download(tableName);
        map.put("down",result);
      //  if(result.equals("success")){return  "relationChange";}
        System.out.println(result);
        return "relationChange";
        //map.put("down", result);

    }
}
