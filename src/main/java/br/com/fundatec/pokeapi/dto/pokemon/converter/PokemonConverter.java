package br.com.fundatec.pokeapi.dto.pokemon.converter;

import br.com.fundatec.pokeapi.dto.pokemon.PokemonRequest;
import br.com.fundatec.pokeapi.dto.pokemon.PokemonResponse;
import br.com.fundatec.pokeapi.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public interface PokemonConverter<T extends Pokemon, O extends PokemonResponse, I extends PokemonRequest> {

    O convert(T pokemon);

    T convert(I pokemon);
}
