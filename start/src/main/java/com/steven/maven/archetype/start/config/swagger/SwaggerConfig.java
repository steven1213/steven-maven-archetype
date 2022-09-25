package com.steven.maven.archetype.start.config.swagger;

import com.google.common.base.Predicate;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Configuration
@EnableSwagger2
@Profile({"local", "dev"})
public class SwaggerConfig {

//    @Value("${server.servlet.context-path:/}")
//    private String contextPath;

    private ApiInfo initApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("steven-maven-archetype API")
                .version("1.0.0")
                .contact(new Contact("steven", "https://github.com/steven1213", "steven.cao1213@gmail.com"))
//                .license("Apache Licence")
                .build();
        return apiInfo;
    }

    @Bean
    public Docket restfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(initApiInfo())
//                .groupName("RestfulApi")
                //.genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(true)
//                .forCodeGeneration(false)
                .pathMapping("/")
                // base，最终调用接口后会和paths拼接在一起
//                .pathMapping(contextPath)
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.steven.maven.archetype.adapter"))
                //暴露接口地址的包路径（即此包下的类，才生成接口文档）
                //自定义的过滤规则
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     *
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(
                regex("/test*")
        );
    }

}
