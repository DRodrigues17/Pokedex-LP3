package br.com.fundatec.pokeApi.service;

import br.com.fundatec.pokeApi.exception.ObjectNotFoundException;
import br.com.fundatec.pokeApi.model.Pokemon;
import br.com.fundatec.pokeApi.model.Response;
import br.com.fundatec.pokeApi.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonIntegrationService {

    private RestTemplate restTemplate;
    private PokemonRepository repository;

    @Value("${pokemon-external-api}")
    private String uri;

    public PokemonIntegrationService(RestTemplateBuilder restTemplateBuilder, PokemonRepository repository) {
        this.restTemplate = restTemplateBuilder.build();
        this.repository = repository;
    }

    public List<Pokemon> findAll(){
        try {
            return repository.findAll();
        } catch (ObjectNotFoundException e) {
           throw  new ObjectNotFoundException("Nenhum Pokemon");
        }
    }

    public Pokemon findById(Integer id) {
        String url = generateURLIntegration(id);
        return this.restTemplate.getForObject(url, Pokemon.class);
    }



    private String generateURLIntegration(int id) {
        return this.uri + "/" + id;
    }

    public boolean deleteById(int id) {
         repository.deleteById(id);
         return true;
    }

}
