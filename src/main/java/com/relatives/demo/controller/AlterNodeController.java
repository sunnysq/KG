package com.relatives.demo.controller;

import com.relatives.demo.config.Neo4jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AlterNodeController {
    @Autowired
    private Neo4jUtil neo4jUtil;

    @RequestMapping("/alterNode")
    public Object alternode(@RequestParam("name") String name, @RequestParam("name1") String name1, @RequestParam("sex") String sex, @RequestParam("phone") String phone, @RequestParam("workplace") String workplace, @RequestParam("address") String address,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(name + name1 + phone);
        Object alter=null;
        String cql="match(n:relatives{name:"+"'"+name+"'"+"}) set n.name="+"'"+name1+"'"+",n.sex="+"'"+sex+"'"+",n.telephone="+"'"+phone+"'"+",n.workplace="+"'"+workplace+"'"+",n.address="+"'"+address+"'"+"return COUNT(n)";
        alter=neo4jUtil.alternode(cql,alter);
        System.out.println(alter);
       return alter.toString();
       // retMap.put("node",)
    }

    @RequestMapping("/alterNode2")
    public Object alternode(@RequestParam("name") String name, @RequestParam("name2") String name2, @RequestParam("sex2") String sex2, @RequestParam("phone2") String phone2, @RequestParam("workplace2") String workplace2, @RequestParam("address2") String address2,
                            @RequestParam("present_position") String present_position,@RequestParam("nation") String nation,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object alter=null;
        String cql="match(n:party{name:"+"'"+name+"'"+"}) set n.name="+"'"+name2+"'"+",n.sex="+"'"+sex2+"'"+",n.telephone="+"'"+phone2+"'"+",n.workplace="+"'"+workplace2+"'"+",n.address="+"'"+address2+"'"+",n.present_position="+"'"+present_position+"'"+",n.nation="+"'"+nation+"'"+"return COUNT(n)";
        alter=neo4jUtil.alternode(cql,alter);
        System.out.println(alter);
        return alter.toString();
        // retMap.put("node",)
    }

}
