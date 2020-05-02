package com.movierent;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//Contact information for swagger
    public static final Contact DEFAULT_CONTACT = new Contact("Said Franco", "https://github.com/said90/movieRent.git",
            "bryan.franco37@gmail.com");
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Movie Rent Api Documentation", "Movie Rent Api Documentation",
            "1.0", "FREE", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO);
    }
}
