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
        Optional<PokemonDTO> pokemonOptional = repository.findByExternalIdAndDeletedFalse(id).map(pokemonConverter::convertToDTO);
        if (pokemonOptional.isEmpty()) {
            pokemonOptional = client.getPokemonById(id);
            repository.save(pokemonOptional.map(pokemonConverter::convertToEntity).get());
        }
        return pokemonOptional;
    }

    public Optional<PokemonDTO> findByName(String name) {
        name = StringUtils.capitalize(name);
        Optional<PokemonDTO> pokemonOptional = repository.findByNameAndDeletedFalse(name).map(pokemonConverter::convertToDTO);
        if (pokemonOptional.isEmpty()) {
            pokemonOptional = client.getPokemonByName(name.toLowerCase());
            repository.save(pokemonOptional.map(pokemonConverter::convertToEntity).get());

        }
        return pokemonOptional;

    }

    public List<PokemonDTO> findByWeight(Integer quilogramas){
        Integer hectogramas = quilogramas/10;
        List<PokemonDTO> pokemonList = repository.findByWeightAndDeletedFalse(hectogramas)
                .stream()
                .map(pokemonConverter::convertToDTO)
                .toList();
        if(pokemonList.isEmpty()){
            throw new IllegalStateException();
        }
        return pokemonList;
    }

    public List<PokemonDTO> findByHeight(Integer metros){
        Integer decimetros = metros/10;
        List<PokemonDTO> pokemonList =repository.findByHeightAndDeletedFalse(decimetros)
                .stream()
                .map(pokemonConverter::convertToDTO)
                .toList();
        if(pokemonList.isEmpty()){
            throw new IllegalStateException();
        }
        return pokemonList;
    }

    public boolean deleteById(int id) {
         repository.deleteById(id);
         return true;
    }

}
