package br.com.fundatec.pokeapi.repository;

import br.com.fundatec.pokeapi.model.DeleteLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeleteLogRepository extends MongoRepository<DeleteLog, Integer> {
}
