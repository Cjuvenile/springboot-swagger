package com.example.controller;


import com.example.model.Hello;
import com.example.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@Api(tags = "用户相关的请求")
//RestController是Controller和ResponseBody组合的注解
@RestController
@RequestMapping("/user")
public class UserController {
//    不返回的实体是不会展示在model栏里的 return
//通过@RequestBody也是将实体返回到前端
    @ApiOperation("获取用户信息")

    @GetMapping
    //表达参数
    public String getUser(Hello hello){
    return "张三";
}
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Name",value = "用户名",paramType = "header",defaultValue = "zhangsan",example = "lisi"),
            @ApiImplicitParam(name="age",value = "年龄")
    })
    @PostMapping("111")
    public String addUser(String Name,String age){
        return Name;
    }
    @PostMapping("222")
    public String addUser2(String Name,String age){
        return Name;
    }

    @DeleteMapping
    public User deleteUser(User user){
//        User user = new User("张三","22");
        return  user;
    }
    @PutMapping
    //json参数
    public String putUser(@RequestBody User user){
        return  "user";
    }

}
