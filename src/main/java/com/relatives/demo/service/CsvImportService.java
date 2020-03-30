package com.relatives.demo.service;

import com.relatives.demo.dao.CsvImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvImportService {
    @Autowired
    CsvImportRepository csvImportRepository;

    //  @Override
    //@Transactional(value = "neo4jTransaction")

    public String addNode(String FileName) throws Exception {
        try {
            System.out.println(FileName);
            if(FileName.equals("tb_relation.csv")){csvImportRepository.addRelation(FileName); return "关系建立成功";}
            else if(FileName.equals("tb_civil_servant.csv")) {csvImportRepository.addNode1(FileName);return "节点创建成功";}
            else {csvImportRepository.addNode2(FileName);return "节点创建成功";}

        } catch (Exception e) {
            e.printStackTrace();
            return "创建失败";
            //        log.error("新增失败", e);
           // throw new RuntimeException("新增失败");
        }
    }

//    public List<TbCivilServant> getList(){
//        return csvImportRepository.getUserNode();
//    }
}
