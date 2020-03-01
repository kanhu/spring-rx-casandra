package com.example.springrx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	Contact contact = new Contact("Test Corp", "http://www.test.com",
			"kanhu.aum@gmail.com");

	List<VendorExtension> vendorExtensions = new ArrayList<>();

	ApiInfo apiInfo = new ApiInfo("Product Service", "This is api documentation for product service.", "1.0.0",
			"https://localhost.com", contact, "VFC license", "https://localhost.com", vendorExtensions);

	@Bean
	public Docket apiDocket() {

		return new Docket(DocumentationType.SWAGGER_2).protocols(new HashSet<>(Arrays.asList("HTTP", "HTTPs")))
				.apiInfo(apiInfo).select().apis(RequestHandlerSelectors.basePackage("com.example.springrx"))
				.paths(PathSelectors.any()).build();
	}
}
