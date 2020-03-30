package com.relatives.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
    @RequestMapping("/hello")
    public String hello(){
        return "index";
    }
//    @RequestMapping("/alterNode")
//    public String alterNode(){
//        return "alterNode";
//    }
    @RequestMapping("/import")
    public String test1(){
        return "import";
    }
    @RequestMapping("/relationChange")
    public String test2(){
        return "relationChange";
    }
    @RequestMapping("/relationQuery")
    public String test3(){
        return "relationQuery";
    }
    @RequestMapping("/relationFind")
    public String test4(){
        return "relationFind";
    }

}
