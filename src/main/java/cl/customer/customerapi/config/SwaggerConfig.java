package cl.customer.customerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
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
public class SwaggerConfig {

    @SuppressWarnings("unchecked")
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api").select()
                .apis((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("cl.customer.customerapi"))
                .paths((Predicate<String>) PathSelectors.any())
                .build().apiInfo(apiEndPointsInfo());
    }
    
    private ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder().title("API Rest Bancaria")
                .description("API Rest para el mantenimiento de Clientes y sus datos")
                .contact(new Contact("Mauricio Águila", "Desarrollador de Sistemas", "maurcio.aguila.g@outlook.com"))
                .licenseUrl("www.mipagina.cl")
                .version("1.0.0")
                .build();
    }
}
