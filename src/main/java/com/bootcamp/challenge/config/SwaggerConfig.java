package com.bootcamp.challenge.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bootcamp.challenge.controllers"))
                .paths(PathSelectors.any())
                .build()
                .tags(  new Tag("Usuarios", "Gerencia os usuários da aplicacacao."),
                        new Tag("Produtos", "Gerencia os produtos da aplicacao"),
                        new Tag("Publicacao", "Gerencia as publicacoes da aplicacao"),
                        new Tag("Seguir", "Gerencia os seguidores da aplicacao")
                )
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("Challenge Spring")
                                .description("")
                                .build());
    }

}