package br.com.fundatec.pokeApi.dto.converter;

import br.com.fundatec.pokeApi.dto.PokemonResponseDTO;
import br.com.fundatec.pokeApi.model.Pokemon;

public class PokemonConverterImpl implements  PokemonConverter<Pokemon, PokemonResponseDTO> {
    @Override
    public PokemonResponseDTO convert(Pokemon pokemon) {
        return PokemonResponseDTO.builder()
                .name(pokemon.getName())
                .height(pokemon.getHeight())
                .weight(pokemon.getWeight())
                .moves(pokemon.getMoves())
                .build();
    }
}
