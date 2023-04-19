package br.com.fundatec.pokeapi.service;

import br.com.fundatec.pokeapi.client.PokemonClient;
import br.com.fundatec.pokeapi.dto.PokemonDTO;
import br.com.fundatec.pokeapi.dto.converter.PokemonConverter;
import br.com.fundatec.pokeapi.exception.PokemonAlreadyDeletedException;
import br.com.fundatec.pokeapi.exception.PokemonNotFoundException;
import br.com.fundatec.pokeapi.model.DeleteLog;
import br.com.fundatec.pokeapi.model.Pokemon;
import br.com.fundatec.pokeapi.repository.DeleteLogRepository;
import br.com.fundatec.pokeapi.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PokemonService {

    private final PokemonRepository repository;
    private final PokemonClient client;
    private final DeleteLogRepository deleteLogRepository;

    public Optional<PokemonDTO> findById(Integer id) {
        Optional<PokemonDTO> pokemonOptional = repository.findByExternalIdAndDeletedFalse(id).map(PokemonConverter::convertToDTO);
        if (pokemonOptional.isEmpty()) {
            pokemonOptional = client.getPokemonById(id);
            repository.save(pokemonOptional.map(PokemonConverter::convertToEntity).get());
        }
        return pokemonOptional;
    }

    public Optional<PokemonDTO> findByName(String name) {
        name = StringUtils.capitalize(name);
        Optional<PokemonDTO> pokemonOptional = repository.findByNameAndDeletedFalse(name).map(PokemonConverter::convertToDTO);
        if (pokemonOptional.isEmpty()) {
            pokemonOptional = client.getPokemonByName(name.toLowerCase());
            repository.save(pokemonOptional.map(PokemonConverter::convertToEntity).get());

        }
        return pokemonOptional;

    }

    public List<PokemonDTO> findByWeight(Integer hectogramas) {
        List<PokemonDTO> pokemonList = repository.findByWeightAndDeletedFalse(hectogramas)
                .stream()
                .map(PokemonConverter::convertToDTO)
                .toList();
        if (pokemonList.isEmpty()) {
            throw new IllegalStateException();
        }
        return pokemonList;
    }

    public List<PokemonDTO> findByHeight(Integer decimetros) {
        List<PokemonDTO> pokemonList = repository.findByHeightAndDeletedFalse(decimetros)
                .stream()
                .map(PokemonConverter::convertToDTO)
                .toList();
        if (pokemonList.isEmpty()) {
            throw new IllegalStateException();
        }
        return pokemonList;
    }

    public boolean deleteByName(String name) {
        Pokemon pokemon = repository.findByName(StringUtils.capitalize(name)).orElseThrow(() -> new PokemonNotFoundException(name));

        if (pokemon.isDeleted()) {
            throw new PokemonAlreadyDeletedException(name);
        }
        pokemon.setDeleted(true);
        pokemon.setDeletedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        generateLog(pokemon.getExternalId(), pokemon.getName());
        repository.save(pokemon);
        return true;
    }

    public void generateLog(int pokemonId, String pokemonName) {
        deleteLogRepository.save(
                DeleteLog.builder()
                        .pokemonName(pokemonName)
                        .pokemonId(pokemonId)
                        .dateOfRemove(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                        .build());
    }

}
