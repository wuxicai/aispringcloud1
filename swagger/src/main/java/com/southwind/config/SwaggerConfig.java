package com.southwind.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2//访问页面http://localhost:8080/swagger-ui.html
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){
        /*
        * RequestHandlerSelectors.basePackage("com.southwind.controller") 扫描包
        * RequestHandlerSelectors.any() 全部
        * RequestHandlerSelectors.withClassAnnotation(Controller.class) 类注解
        * paths(PathSelectors.ant("/hello/**")) 请求路径限制
        * .enable(flag)环境区别设置，生产环境有
        * 测试用************
        * groupName("吴喜财")分组
        * 访问页面http://localhost:8080/swagger-ui.html
        * */
        Profiles profiles=Profiles.of("dev","test");

        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("吴喜财").enable(flag)
                .select().
                apis(RequestHandlerSelectors.any()).
                build();
    }
    @Bean
    public Docket docket1(Environment environment){
        /*
         * RequestHandlerSelectors.basePackage("com.southwind.controller") 扫描包
         * RequestHandlerSelectors.any() 全部
         * RequestHandlerSelectors.withClassAnnotation(Controller.class) 类注解
         * paths(PathSelectors.ant("/hello/**")) 请求路径限制
         * .enable(flag)环境区别设置，生产环境有
         *
         * groupName("吴喜财")分组
         * */
        return new Docket(DocumentationType.SWAGGER_2).groupName("周洪峰");
    }
    @Bean
    public Docket docket2(Environment environment){
        /*
         * RequestHandlerSelectors.basePackage("com.southwind.controller") 扫描包
         * RequestHandlerSelectors.any() 全部
         * RequestHandlerSelectors.withClassAnnotation(Controller.class) 类注解
         * paths(PathSelectors.ant("/hello/**")) 请求路径限制
         * .enable(flag)环境区别设置，生产环境有
         *
         * groupName("吴喜财")分组
         * */
        Profiles profiles=Profiles.of("dev","test");

        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2).groupName("刘磊").enable(flag)
                .select().
                        apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class)).
                        paths(PathSelectors.ant("/hello/**")).build();
    }
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("吴喜财", "https://www.baidu.com", "767718929@qq.com");
        return new ApiInfo(
                "吴喜财的文档",
                "发的了思考法的精髓啦费德勒送咖啡就肯定撒",
                "2.0",
                "https://www.baidu.com",
                 contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
