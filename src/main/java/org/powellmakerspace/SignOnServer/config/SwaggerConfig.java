package org.powellmakerspace.SignOnServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket signOnServerApi(){

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Powell Makerspace Login Application API")
                .description("Application designed to track and analyze visit and member information.")
                .version("1.0")
                .contact(new Contact("Anthony Riesen", null, "r.anthony1961@hotmail.com"))
                .license("License to be Determined")
                .licenseUrl("License to be Determined")
                .build();


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.powellmakerspace"))
                .build()
                .apiInfo(apiInfo);
    }
}
