package com.relatives.demo.controller;

import com.relatives.demo.config.Neo4jUtil;
import com.relatives.demo.dao.RelationQueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class RelationQueryController {
//    @Autowired
//    RelationQueryRepository relationQueryRepository;
        @Autowired
         RelationQueryDao relationQueryDao;
//    @GetMapping("/CREAGE")
//    public List<TbCivilServant> create(){
//        System.out.println(relationQueryRepository.creat());
//        return relationQueryRepository.creat();
//    }
//    @RequestMapping("/get")
//    public TbCivilServant Getrelation(@RequestParam(value="name") String name){
//   //     System.out.println(relationQueryRepository.findByName(name));
//        return relationQueryRepository.findByName(name);
//    }
//    @RequestMapping("/gets")
//    public List<TwoTuple> Getrelation(){
//    //    System.out.println(relationQueryRepository.finds());
//        return relationQueryRepository.finds();
//    }
//     @RequestMapping("/re")
//    public List<RelativeRlation> queryRelation(@RequestParam(value="name")String name) {
//     //   System.out.println(relationQueryRepository.find(name));
//        return relationQueryRepository.find(name);
//    }

    @Autowired
    private Neo4jUtil neo4jUtil;

    @RequestMapping("/getPath")
    public Map<String, Object> getPath(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "generation", required = false)String generation,
                                       HttpServletRequest request, HttpServletResponse response)throws Exception{
        Map<String, Object> retMap = new HashMap<>();
        int generations=Integer.parseInt(generation);
        //cql语句
        String cql="match data=(na:party{name:"+"'"+name+"'"+"})-[r*.."+generations+"]->(nb:relatives) return data";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql,nodeList,edgeList);
        retMap.put("nodeList",nodeList);
        retMap.put("edgeList",edgeList);
        return retMap;
    }
    @RequestMapping("getShortPath")
    public Map<String, Object> getShortPath(){
        Map<String, Object> retMap = new HashMap<>();
        //cql语句
        String cql = "MATCH n=shortestPath((a:朋友圈{姓名:\"小讯\"})-[*]-(b:朋友圈{姓名:\"小锐\"})) return n";
        //待返回的值，与cql return后的值顺序对应
        Set<Map<String ,Object>> nodeList = new HashSet<>();
        Set<Map<String ,Object>> edgeList = new HashSet<>();
        neo4jUtil.getPathList(cql,nodeList,edgeList);
        retMap.put("nodeList",nodeList);
        retMap.put("edgeList",edgeList);
        return retMap;
    }
    @RequestMapping("/relation")
    public String relationQuery(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "generation", required = false)String generation,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        int generations=Integer.parseInt(generation);
        System.out.println(generations);
        String cql="match data=(na:party{name:"+"'"+name+"'"+"})-[r*.."+generations+"]->(nb:relatives) return data";
        String result=relationQueryDao.RelationQuery(cql);
        System.out.println(result);
        return result;
    }

}
