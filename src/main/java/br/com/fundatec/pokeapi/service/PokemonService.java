package br.com.fundatec.pokeapi.service;

import br.com.fundatec.pokeapi.client.PokemonClient;
import br.com.fundatec.pokeapi.model.Pokemon;
import br.com.fundatec.pokeapi.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PokemonService {

    private final PokemonRepository repository;
    private final PokemonClient client;


    public Pokemon findById(Integer id) {
        return repository.findById(id).orElseGet(client.getPokemonById(id));
    }

    public Pokemon findByName(String name) {
        return repository.findByName(name).orElseGet(client.getPokemonByName(name));
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
