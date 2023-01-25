package br.com.fundatec.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PokemonDTO(
        Integer id,
        String name,
        int height,
        int weight,
        Collection<MovesDTO>moves
        ) {
}
