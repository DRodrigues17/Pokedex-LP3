package br.com.fundatec.pokeapi.repository;

import br.com.fundatec.pokeapi.model.DeleteLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DeleteLogRepository extends MongoRepository<DeleteLog, UUID> {
}
