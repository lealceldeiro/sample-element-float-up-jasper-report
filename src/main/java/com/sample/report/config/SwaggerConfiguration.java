package com.sample.report.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:configuration.properties")
public class SwaggerConfiguration {

    @Value("${application.version}")
    public String applicationVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample.report"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sample report project for testing a \"float-up\" option")
                .version(applicationVersion)
                .description("Generates a PDF report with three divs based on parameter " +
                        "\"showFirst\" (BLUE) \"showSecond\" (RED).")
                .build();
    }

    @Bean
    public ReportBuilderPlugin structuredInvestmentOperationBuilderPlugin() {
        return new ReportBuilderPlugin();
    }
}
