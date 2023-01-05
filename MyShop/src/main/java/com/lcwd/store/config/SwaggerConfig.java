package com.lcwd.store.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableWebMvc
@OpenAPIDefinition
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(getApiInfo());
		
		docket.securityContexts(Arrays.asList(getSecurityContext()));
		docket.securitySchemes(Arrays.asList(getSchemes()));
		ApiSelectorBuilder select = docket.select();
		select.apis(RequestHandlerSelectors.any());
		select.paths(PathSelectors.any());
		Docket build = select.build();
		
		return build;
	}

	private ApiKey getSchemes() {
		return new ApiKey("apiKey","Authorization","header");
	}

	private SecurityContext getSecurityContext() {
		
	return	 SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		
		AuthorizationScope scope =new AuthorizationScope("Global", "Access Everything");
		AuthorizationScope[] scopes= {scope};		
		
		return Arrays.asList(new SecurityReference("apiKey", scopes));
	}

	private ApiInfo getApiInfo() {

		ApiInfo apiInfo = new ApiInfo("MyStore API Documentation",
				"This is a documentation of mystore created by LCWD",
				"1.0",
				"Terms of service",
				new Contact("Denis coding", "sdasd.com", "denis.com	"),
				"Licence of Api",
				"Api licence URL",
				new ArrayList<>());
		
		return apiInfo;
	}
}
