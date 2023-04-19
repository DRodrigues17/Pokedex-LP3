package br.com.fundatec.pokeapi.client.impl;

import br.com.fundatec.pokeapi.client.PokemonClient;
import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.exception.PokemonNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import java.util.Optional;

@RequiredArgsConstructor
@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2/pokemon")
public class PokemonClientImpl implements PokemonClient {

    @Override
    public Optional<PokemonDTO> getPokemonById(Integer id) {
        Optional<PokemonDTO> pokemon = ;
         return pokemon.orElseThrow(() -> new PokemonNotFoundException(id.toString()));
    }

    @Override
    public Optional<PokemonDTO> getPokemonByName(String name) {
        Optional<PokemonDTO> pokemon = ;
        return pokemon.orElseThrow(() -> new PokemonNotFoundException(name));
    }
}
