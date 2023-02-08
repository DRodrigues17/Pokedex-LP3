package br.com.fundatec.pokeapi.service;

import br.com.fundatec.pokeapi.client.PokemonClient;
import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.dto.converter.PokemonConverter;
import br.com.fundatec.pokeapi.exception.ObjectNotFoundException;
import br.com.fundatec.pokeapi.model.DeleteLog;
import br.com.fundatec.pokeapi.model.Pokemon;
import br.com.fundatec.pokeapi.repository.DeleteLogRepository;
import br.com.fundatec.pokeapi.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private final DeleteLogRepository deleteLogRepository;

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

    public List<PokemonDTO> findByWeight(Integer hectogramas){
        List<PokemonDTO> pokemonList = repository.findByWeightAndDeletedFalse(hectogramas)
                .stream()
                .map(pokemonConverter::convertToDTO)
                .toList();
        if(pokemonList.isEmpty()){
            throw new IllegalStateException();
        }
        return pokemonList;
    }

    public List<PokemonDTO> findByHeight(Integer decimetros){
        List<PokemonDTO> pokemonList =repository.findByHeightAndDeletedFalse(decimetros)
                .stream()
                .map(pokemonConverter::convertToDTO)
                .toList();
        if(pokemonList.isEmpty()){
            throw new IllegalStateException();
        }
        return pokemonList;
    }

    public boolean deleteByName(String name) {
        Pokemon pokemon = repository.findByNameAndDeletedFalse(StringUtils.capitalize(name)).orElseThrow(()-> new ObjectNotFoundException(name));
        pokemon.setDeleted(true);
        pokemon.setDeletedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        generateLog(pokemon.getExternalId(), pokemon.getName());
        repository.save(pokemon);

        return true;
    }

    public void generateLog (int pokemonId, String pokemonName){
        deleteLogRepository.save(
                DeleteLog.builder()
                        .pokemonName(pokemonName)
                        .pokemonId(pokemonId)
                        .dateOfRemove(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                        .build());
    }

}
