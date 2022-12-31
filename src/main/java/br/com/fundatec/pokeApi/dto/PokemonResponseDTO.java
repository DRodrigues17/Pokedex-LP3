package br.com.fundatec.pokeApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;


import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class PokemonResponseDTO {

    private String name;
    private int height;
    private int weight;
    private Collection<MovesDTO> moves;

    public void setName(String name) {
        this.name = name.substring(0,1).toLowerCase() +
                name.substring(1).toLowerCase();
    }

}
