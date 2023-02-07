package br.com.fundatec.pokeapi.dto;

import java.util.Collection;


public record PokemonDTO(
        Integer id,
        String name,
        int height,
        int weight,
        Collection<MovesDTO>moves
        ) {
}
