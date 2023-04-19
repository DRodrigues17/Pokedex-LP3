package br.com.fundatec.pokeapi.repository;

import br.com.fundatec.pokeapi.model.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PokemonRepository extends MongoRepository<Pokemon, UUID> {

    @Query("{$and :[{externalId: ?0},{deleted: false}] }")
    Optional<Pokemon> findByExternalIdAndDeletedFalse(Integer externalId);

    @Query("{$and :[{name: ?0},{deleted: false}] }")
    Optional<Pokemon> findByNameAndDeletedFalse(String name);

    Optional<Pokemon> findByName(String name);

    @Query("{$and :[{weight: ?0},{deleted: false}] }")
    List<Pokemon> findByWeightAndDeletedFalse(Integer weight);

    @Query("{$and :[{height: ?0},{deleted: false}] }")
    List<Pokemon> findByHeightAndDeletedFalse(Integer height);

}
