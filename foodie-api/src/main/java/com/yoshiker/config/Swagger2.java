package com.yoshiker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    // 文档地址
    // http://localhost:8088/swagger-ui.html
    // http://localhost:8088/doc.html

    // Swagger2 核心配置
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yoshiker.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TT在线购物平台 API接口文档")
                .contact(new Contact("yoshiker",
                        "http://www.yoshiker.cn",
                        "124565124@qq.com"))
                .description("专为TT在线购物平台 提供的API接口文档")
                .version("1.0.0")
                .termsOfServiceUrl("http://www.yoshiker.cn")
                .build();
    }
}
