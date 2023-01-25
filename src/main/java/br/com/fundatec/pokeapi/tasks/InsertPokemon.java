package br.com.fundatec.pokeapi.tasks;

import br.com.fundatec.pokeapi.client.PokemonClient;
import br.com.fundatec.pokeapi.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InsertPokemon {

    @Autowired
    private PokemonRepository repository;
    @Autowired
    private PokemonClient client;

    private static final Logger log = LoggerFactory.getLogger(InsertPokemon.class);

    @Scheduled(fixedRate =  500000, zone = "America/Sao_Pailo")
    public void salvarPokemons(){
//        for (int i = 0; i < 1000; i++) {
//            repository.save(client.getPokemonById(i));
//        }
        log.info("salvou o pokemon no momento -> {}", LocalDateTime.now());
    }

}
