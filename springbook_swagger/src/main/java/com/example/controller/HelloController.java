package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@Controller
@RequestMapping("hello")
public class HelloController {
    //@RequestHeader("token") String token  用于接收全局变量
    @PutMapping
    public String updateHello(@RequestHeader("token") String token, String str){  //header
        //  public String updateHello(String token, String str){    对应query值的参数
        if(token.equals("111")){
            return "111";
        }
        return str;
    }
    //restful 风格url
    @DeleteMapping("1112")
    public String deleteHello(Integer integer,Long s){
        return "hello";
    }
    @DeleteMapping("222")
    public String deleteHello2(Integer integer,Long s){
        return "hello";
    }
}
