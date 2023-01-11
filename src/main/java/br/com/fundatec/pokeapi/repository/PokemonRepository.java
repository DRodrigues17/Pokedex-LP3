package br.com.fundatec.pokeapi.repository;

import br.com.fundatec.pokeapi.model.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;

public interface PokemonRepository extends MongoRepository<Pokemon, Integer> {

    Optional<Pokemon> findByName(String nome);
    List<Pokemon> findByWeigth(Integer weigth);
    List<Pokemon> findByHeigth(Integer heigth);

}
