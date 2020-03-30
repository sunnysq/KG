package com.relatives.demo.controller;

import com.relatives.demo.service.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class CsvImportController {
    @Autowired
    CsvImportService csvImportService;
    @RequestMapping("/csvimport")
    public String csvimport(MultipartFile file, HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
        String FileName=file.getOriginalFilename();
        System.out.println(FileName);
        String result=csvImportService.addNode(FileName);
        map.put("csvimport",result);
        return "{\"code\":0,\"msg\":\""+result+"\"}";
    }
//    @RequestMapping("/getList")
//    public List<TbCivilServant> getList(){
//        System.out.println(csvImportService.getList());
//       return  csvImportService.getList();
//    }

}
