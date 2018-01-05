package com.touati.org.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()               
	                .apis(RequestHandlerSelectors.any())
	                .paths(regex("/api.*"))
	                .build()
	                .apiInfo(metaData());
	    }
	   
		public ApiInfo metaData() {
	        ApiInfo apiInfo = new ApiInfo(
	                "HUMAN RESOURCES PROJECT",
	                "MASTER 2 Données et systèmes connectés",
	                "1.0",
	                "Terms of service",
	                new Contact("Nassim TOUATI", "https://mootse.telecom-st-etienne.fr/pluginfile.php/24561/mod_resource/content/3/springbootlab.pdf", "nassim.tweti@gmail.com"),
	               "Apache License Version 2.0",
	                "https://www.apache.org/licenses/LICENSE-2.0");
	        return apiInfo;
	    }
	   
}



