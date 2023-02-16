package br.com.fundatec.pokeapi.dto.converter;

import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.model.Pokemon;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PokemonConverter {
    public static PokemonDTO convertToDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.getExternalId(),
                StringUtils.capitalize(pokemon.getName()),
                pokemon.getHeight(),
                pokemon.getWeight(),
                pokemon.getMoves()
        );
    }


    public static Pokemon convertToEntity(PokemonDTO pokemonDTO) {
        return Pokemon.builder()
                .externalId(pokemonDTO.id())
                .name(StringUtils.capitalize(pokemonDTO.name()))
                .height(pokemonDTO.height())
                .weight(pokemonDTO.weight())
                .moves(pokemonDTO.moves())
                .build();
    }
}
