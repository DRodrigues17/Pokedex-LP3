package br.com.fundatec.pokeapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pokedex REST API")
                        .description("Interface para PokeAPI(https://pokeapi.co/) feita com SpringBoot")
                        .version("1.0.0"))
                .components(new Components());
    }
}
