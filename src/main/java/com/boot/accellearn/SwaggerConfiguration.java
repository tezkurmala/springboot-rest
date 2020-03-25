package com.boot.accellearn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
    public static final Contact DEFAULT_CONTACT = new Contact("Tez", "tezu.com", "tezzzzz@gmail.com");
    public static final ApiInfo DEFAULT_API_INFO = 
    		new ApiInfo("Social Media App", 
    				    "Provides URIs to interact with a Socia Media application."
    				    + "Users can be maintained. Posts also can be maintained."
    				    + "Users can be associated with Posts.", 
    				    "1.0", 
    				    "urn:tos",
    				    DEFAULT_CONTACT, 
    				    "Tez 5.0", 
    				    "http://www.tezu.org/licenses/LICENSE-5.0");
	private static final Set<String> SUPPORTED_FORMATS = 
			new HashSet<String>(Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket docs() {
		//return new Docket(DocumentationType.SWAGGER_2);
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(SUPPORTED_FORMATS)
				.consumes(SUPPORTED_FORMATS);
	}
	//Access using http://localhost:8080/swagger-ui.html
	//Access using http://localhost:8080/v2/api--docs
}
