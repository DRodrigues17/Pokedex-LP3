package br.com.fundatec.pokeApi.dto.converter;

import br.com.fundatec.pokeApi.dto.PokemonResponseDTO;
import br.com.fundatec.pokeApi.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public interface PokemonConverter<T extends Pokemon, O extends PokemonResponseDTO> {

    O convert(T pokemon);
}
