package br.com.fundatec.pokeapi.service;

import br.com.fundatec.pokeapi.client.PokemonClient;
import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.dto.converter.PokemonConverter;
import br.com.fundatec.pokeapi.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PokemonService {

    private final PokemonRepository repository;
    private final PokemonClient client;
    private final PokemonConverter pokemonConverter;

    public Optional<PokemonDTO> findById(Integer id) {
        Optional<PokemonDTO> pokemonOptional = repository.findByExternalId(id).map(pokemonConverter::convertToDTO);
        if (pokemonOptional.isEmpty()) {
            pokemonOptional = client.getPokemonById(id);
            repository.save(pokemonOptional.map(pokemonConverter::convertToEntity).get());
        }
        return pokemonOptional;
    }

    public Optional<PokemonDTO> findByName(String name) {
        name = StringUtils.capitalize(name);
        Optional<PokemonDTO> pokemonOptional = repository.findByName(name).map(pokemonConverter::convertToDTO);
        if (pokemonOptional.isEmpty()) {
            pokemonOptional = client.getPokemonByName(name.toLowerCase());
            repository.save(pokemonOptional.map(pokemonConverter::convertToEntity).get());

        }
        return pokemonOptional;

    }

    public List<PokemonDTO> findByWeigth(Integer hectogramas){
        return repository.findByWeight(hectogramas)
                .stream()
                .map(pokemonConverter::convertToDTO)
                .toList();
    }

    public List<PokemonDTO> findByHeigth(Integer decimetros){
            return repository.findByHeight(decimetros)
                    .stream()
                    .map(pokemonConverter::convertToDTO)
                    .toList();
    }

    public boolean deleteById(int id) {
         repository.deleteById(id);
         return true;
    }

}
