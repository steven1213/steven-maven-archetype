package com.steven.maven.archetype.start.config.swagger;

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

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Configuration
@EnableSwagger2
@Profile({"local", "dev"})
public class SwaggerConfig {

    private ApiInfo initApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("API").version("1.0.0").build();
        return apiInfo;
    }

    @Bean
    public Docket restfulApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(initApiInfo()).pathMapping("/").select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.steven.maven.archetype")).paths(PathSelectors.any()).build();
    }


}
