package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.AllowableValues;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.List;

/*配置类*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /*注入到页面上的内容
    *可省略 apiINfo 默认注入内容 Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()
    * */
    @Autowired
//    导入import org.springframework.core.env.Environment;
    Environment environment;


    /*swagger页面提示信息*/
    private ApiInfo apiInfo() {
        //编写者个人信息     Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        Contact contact = new Contact("fd", "aaa.com", "1337428676@qq.com");

        // public ApiInfo(String title, String description, String version, String termsOfServiceUrl, Contact contact, String ", String licenseUrl, Collection<VendorExtension> vendorExtensions) {
        return new ApiInfo("Swagger学习接口文档",// 标题
                "这是学习swagger时生成的文档信息", // 描述
                "v1.0",// 版本
                "http::localhost:8080",// 组织链接
                contact,// 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>());// 扩展
    }


    //通过指定路径扫描接口
    @Bean
    public Docket docket() {
       //判断环境
//        Profiles profiles=Profiles.of("prod");
//        //通过环境来判断是否显示swagger
//        boolean isEnable=environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("A1")
                // 1配置要忽略的参数
//               .ignoredParameterTypes(Long.class)
                //2 配置是否启用Swagger，如果是false，在浏览器将无法访问
//                .enable(isEnable)
//                1 2可共存 也可独立存在 在Docket之下
                .select().apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .build();

    }


/*    分组 groupName 要写的话就需要给所有的Docket都进行分组
   *分组名重复会报错
   *缺少分组会报错
   */


/*扫描接口*/

    //  .any()所有接口  会连Swagger默认的接口也都扫描出来 不推荐使用
    @Bean
    public Docket docket1_1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-any")
                .select().apis(RequestHandlerSelectors.any()).build();
    }
    //.none不显示接口
    @Bean
    public Docket docket1_2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-none")
                .select().apis(RequestHandlerSelectors.none()).build();
    }
    //通过指定路径扫描接口
    @Bean
    public Docket docket1_3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-basePackage")
                .select().apis(RequestHandlerSelectors.basePackage("com.example.controller")).build();
    }
    //根据指定路径下选择扫描所有接口  默认所有 any
    @Bean
    public Docket docket1_3_1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-basePackage-paths-any")
                .select().apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.any()).build();
    }
    //通过path过滤  再往下一次扫描指定的接口
    @Bean
    public Docket docket1_3_2() {

        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-basePackage-paths-ant-use")
                .select().apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                // 配置如何通过 path过滤 即这里只扫描 请求以 /user开头的接口
                .paths(PathSelectors.ant("/user/**"))
                .build();

    }
    @Bean
    public Docket docket1_3_3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-basePackage-paths-ant-hello")
                .select().apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                // 配置如何通过 path过滤 即这里只扫描 请求以 /hello开头的接口
                .paths(PathSelectors.ant("/hello/**"))
                .build();

    }
    //根据path选择不扫描接口
    @Bean
    public Docket docket1_3_4() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-basePackage-paths-none")
                .select().apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.none()).build();
    }

    //.withClassAnnotation()根据类上注解来显示接口
    @Bean
    public Docket docket1_4() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-withClassAnnotation")
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
    }
    //.withMethodAnnotation()根据方法上注解显示接口
    @Bean
    public Docket docket1_5() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("apis-withMethodAnnotation")
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(PutMapping.class)).build();
    }
    /*忽略参数*/
    @Bean
    public Docket docket3_1() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("ignoredParameterTypes-hello1")
                // 1配置要忽略的参数
                .ignoredParameterTypes(Long.class)
                .select().apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                // 配置如何通过 path过滤 即这里只扫描 请求以 /hello开头的接口
                .paths(PathSelectors.ant("/hello/**"))
                .build();

    }
    @Bean
    public Docket docket3_2() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("ignoredParameterTypes-hello2")
                // 1配置要忽略的参数
                .ignoredParameterTypes(Long.class,Integer.class)
                .select().apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                // 配置如何通过 path过滤 即这里只扫描 请求以 /hello开头的接口
                .paths(PathSelectors.ant("/hello/**"))
                .build();

    }

    //配置全局变量
    @Bean
    public Docket docketuser2() {
        //关联 helloController
        Parameter token=new ParameterBuilder().name("token")
                .description("用户登录令牌")
                .parameterType("header")
//              .parameterType("query")
 //             .required(true)//每一个接口都需要验证令牌
                .modelRef(new ModelRef("String")).build();
        List<Parameter> parameters=new ArrayList<>();
        parameters.add(token);

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters).groupName("B_parameters");

    }

}
