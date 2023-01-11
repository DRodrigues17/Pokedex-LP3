package br.com.fundatec.pokeapi.client;
import br.com.fundatec.pokeapi.model.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2/pokemon")
public interface PokemonClient {

    @GetMapping(value = "/{id}")
    Optional<Pokemon> getPokemonById(@PathVariable("id") Integer id);

    @GetMapping(value = "/{name}")
    Optional<Pokemon> getPokemonByName(@PathVariable("name") String name);

    @GetMapping()
    List<Pokemon> getAllPokemons();


}
