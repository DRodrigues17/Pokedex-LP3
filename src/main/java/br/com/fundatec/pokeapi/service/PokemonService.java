package br.com.fundatec.pokeapi.service;

import br.com.fundatec.pokeapi.client.PokemonClient;
import br.com.fundatec.pokeapi.dto.pokemon.PokemonRequest;
import br.com.fundatec.pokeapi.dto.pokemon.PokemonResponse;
import br.com.fundatec.pokeapi.dto.pokemon.converter.PokemonConverter;
import br.com.fundatec.pokeapi.exception.ObjectNotFoundException;
import br.com.fundatec.pokeapi.model.Pokemon;
import br.com.fundatec.pokeapi.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PokemonService {

    private final PokemonRepository repository;
    private final PokemonClient client;

    private final PokemonConverter<Pokemon, PokemonResponse, PokemonRequest> converter;

    public Optional<PokemonResponse> findById(Integer id) {

        return Optional.ofNullable(Optional.of(repository.findById(id)
                        .map(converter::convert))
                .orElseGet(() -> client.getPokemonById(id))
                .orElseThrow(() -> new ObjectNotFoundException(id.toString())));

    }

    public Optional<PokemonResponse> findByName(String name) {

        return Optional.ofNullable(Optional.of(repository.findByName(name)
                        .map(converter::convert))
                .orElseGet(() -> client.getPokemonByName(name))
                .orElseThrow(() -> new ObjectNotFoundException(name)));

    }

    public Collection<Pokemon> findByWigth(Integer hectogramas){
        return repository.findByWeigth(hectogramas);
    }

    public Collection<Pokemon> findByHeigth(Integer decimetros){
            return repository.findByHeigth(decimetros);
    }

    public boolean deleteById(int id) {
         repository.deleteById(id);
         return true;
    }

}
