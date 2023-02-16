package br.com.fundatec.pokeapi.dto;

import java.util.List;

public record PokemonDTO(
        Integer id,
        String name,
        int height,
        int weight,
        List<MovesDTO> moves
) {
}
