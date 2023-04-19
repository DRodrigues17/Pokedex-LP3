package br.com.fundatec.pokeapi.controller;

import br.com.fundatec.pokeapi.model.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;

public interface PokemonController {

    @Operation(description = "Retorna um pokemon pelo id passado.")
    ResponseEntity<BaseResponse> findById(@Parameter(schema = @Schema(implementation = int.class),
            description = "Id do pokemon desejado",
            required = true,
            example = "1") int id);

    @Operation(description = "Retorna um pokemon pelo nome passado.")
    ResponseEntity<BaseResponse> findByName(@Parameter(schema = @Schema(implementation = String.class),
            description = "nome do pokemon desejado",
            required = true,
            example = "charmander") String name);

    @Operation(description = "Retorna uma lista de pokemons pelo peso. ATENÇÃO, a API que usei de base salva o" +
            " peso por hectogramas, então se for buscar por quilos adcione um 0 depois do numero, já que 1 quilo = " +
            "10 hectogramas. Mesma coisa em caso de ser um numero quebrado exemplo: 6,9 quilos = 69 hectogramas")
    ResponseEntity<BaseResponse> findByWeight(@Parameter(schema = @Schema(implementation = Integer.class),
            description = "Peso desejado em hectogramas",
            required = true,
            example = "50") int hectograms);

    @Operation(description = "Retorna uma lista de pokemons pela altura. ATENÇÃO, a API que usei de base salva a" +
            " altura por decimetros, então se for buscar por metros adcione um 0 depois do numero, já que 1 metro =" +
            " 10 decimetros. Mesma coisa para alturas quebradas, já que 1,5 metro é 150 decimetros")
    ResponseEntity<BaseResponse> findByHeight(@Parameter(schema = @Schema(implementation = Integer.class),
            description = "Altura desejada em decimetros",
            required = true,
            example = "50") int decimeters);

    @Operation(description = "Deleta o pokemon que existe na api pelo nome")
    ResponseEntity<BaseResponse> deleteByName(@Parameter(schema = @Schema(implementation = String.class),
            description = "nome do pokemon que você deseja deletar",
            required = true,
            example = "charmander") String name);
}
