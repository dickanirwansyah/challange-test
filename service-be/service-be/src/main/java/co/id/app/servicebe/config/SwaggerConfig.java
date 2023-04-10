package co.id.app.servicebe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public InternalResourceViewResolver defaultViewResolver(){
        return new InternalResourceViewResolver();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo("Microservice Auth Management",
                "Api Sandbox Be Management", "V.1",
                "http://google.com",
                new Contact("muhammad dicka nirwansyah",
                        "http://google.com", "dickanirwansyah@gmail.com"),
                "Apache", "http://google.com", Collections.emptyList());
    }

    private static final Tag TAG_ACCOUNT_CONTROLLER = new Tag("Accounts Controller", "Api sandbox account");
    private static final Tag TAG_JOB_CONTROLLER = new Tag("Job Controller", "Api sandbox Job");

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.id.app.servicebe.controller"))
                .paths(PathSelectors.any())
                .build()
                .tags(TAG_ACCOUNT_CONTROLLER, TAG_JOB_CONTROLLER);
    }
}
