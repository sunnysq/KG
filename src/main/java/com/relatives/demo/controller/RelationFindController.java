package com.relatives.demo.controller;

import com.relatives.demo.config.Neo4jUtil;
import com.relatives.demo.service.RelationFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class RelationFindController {
    @Autowired
    private Neo4jUtil neo4jUtil;
    @Autowired
    RelationFindService relationFindService;

    public Map<String, Object> getShortPath(String name1,String name2)  {
        Map<String, Object> retMap = new HashMap<>();
        //cql语句
        String cql = "MATCH n=shortestPath((a)-[*]-(b)) where a.name="+"'"+name1+"'"+"and b.name="+"'"+name2+"'"+"return n";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String, Object>> nodeList = new HashSet<>();
        Set<Map<String, Object>> edgeList = new HashSet<>();
        relationFindService.relationFind(cql, nodeList, edgeList);
        retMap.put("nodeList", nodeList);
        retMap.put("edgeList", edgeList);
        return retMap;
    }
    @RequestMapping("/getPathList")
    public Map<String, Object> pathList(@RequestParam(value = "name1", required = false) String name1, @RequestParam(value = "name2", required = false) String name2,
                         @RequestParam(value = "id_code1", required = false) String id_code1, @RequestParam(value = "id_code2", required = false) String id_code2,
                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> retMap = new HashMap<>();
        int pathSize = 0;
        relationFindService.getpathList(list,pathSize,name1,name2);
        retMap=getShortPath(name1,name2);
        return retMap;
    }
}
