package br.com.fundatec.pokeApi.repository;

import br.com.fundatec.pokeApi.model.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonRepository extends MongoRepository<Pokemon, Integer> {
}
