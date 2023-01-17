package br.com.fundatec.pokeapi.dto.pokemon.converter;


import br.com.fundatec.pokeapi.dto.pokemon.PokemonRequest;
import br.com.fundatec.pokeapi.dto.pokemon.PokemonResponse;
import br.com.fundatec.pokeapi.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public class PokemonConverterImpl implements  PokemonConverter<Pokemon, PokemonResponse, PokemonRequest> {
    @Override
    public PokemonResponse convert(Pokemon pokemon) {
        return PokemonResponse.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .height(pokemon.getHeight())
                .weight(pokemon.getWeight())
                .moves(pokemon.getMoves())
                .build();
    }

    @Override
    public Pokemon convert(PokemonRequest pokemon) {
        return Pokemon.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .height(pokemon.getHeight())
                .weight(pokemon.getWeight())
                .moves(pokemon.getMoves())
                .build();
    }
}
