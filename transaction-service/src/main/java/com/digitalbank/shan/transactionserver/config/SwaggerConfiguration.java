package com.digitalbank.shan.transactionserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket transcationAPI() {
		return new Docket(DocumentationType.SWAGGER_2 )
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.digitalbank.shan.transactionserver"))
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {

        ApiInfo apiInfo = new  ApiInfo("Transaction-Service",
                "Transaction Service Swagger-UI",
                "1.0",
                "Terms of Service",
                new Contact("Shanmugam", "",
                        "r.shanmugam4@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/license.html");

        return apiInfo;
    }
}
