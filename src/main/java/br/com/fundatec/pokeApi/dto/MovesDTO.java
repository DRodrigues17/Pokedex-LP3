package br.com.fundatec.pokeApi.dto;

import br.com.fundatec.pokeApi.dto.MoveDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovesDTO {

    private MoveDTO move;

}
