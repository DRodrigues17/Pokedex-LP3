package br.com.fundatec.pokeapi.repository;

import br.com.fundatec.pokeapi.model.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.List;

public interface PokemonRepository extends MongoRepository<Pokemon, Integer> {

    @Query("{$and :[{externalId: ?0},{deleted: false}] }")
    Optional<Pokemon> findByExternalIdAndDeletedFalse(Integer externalId);
    @Query("{$and :[{name: ?0},{deleted: false}] }")
    Optional<Pokemon> findByNameAndDeletedFalse(String name);
    List<Pokemon> findByWeightAndDeletedFalse(Integer weight);
    @Query("{$and :[{height: ?0},{deleted: false}] }")
    List<Pokemon> findByHeightAndDeletedFalse(Integer height);

}
