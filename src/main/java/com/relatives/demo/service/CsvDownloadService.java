package com.relatives.demo.service;

import com.relatives.demo.dao.CsvDownloadRepository;
import com.relatives.demo.entity.TbCivilServant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvDownloadService {
    //注入数据访问层
    @Autowired
    private CsvDownloadRepository csvdownloadrepository;

    public String download(String tableName) {
        System.out.println(tableName);
        File file = new File("D:\\neo4j-community-3.5.5-windows\\neo4j-community-3.5.5\\import\\"+tableName+".csv");
        System.out.println(file);
        if(file.exists()){System.out.println("文件已存在");return "文件已存在";}
        else{  List<TbCivilServant> partyservice = new ArrayList<>();
            partyservice = csvdownloadrepository.downLoad(tableName);
            if (!partyservice.isEmpty()) {
                return "文件写入失败";
            } else {
                return "文件写入成功";
            }
        }


    }
}
