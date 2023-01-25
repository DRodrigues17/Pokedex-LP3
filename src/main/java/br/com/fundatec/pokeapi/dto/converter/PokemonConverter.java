package br.com.fundatec.pokeapi.dto.converter;

import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.model.Pokemon;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PokemonConverter {
    public PokemonDTO convertToDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.getExternalId(),
                StringUtils.capitalize(pokemon.getName()),
                pokemon.getHeight(),
                pokemon.getWeight(),
                pokemon.getMoves()
        );
    }


    public Pokemon convertToEntity(PokemonDTO pokemonDTO) {
        return Pokemon.builder()
                .externalId(pokemonDTO.id())
                .name(StringUtils.capitalize(pokemonDTO.name()))
                .height(pokemonDTO.height())
                .weight(pokemonDTO.weight())
                .moves(pokemonDTO.moves())
                .build();
    }
}
