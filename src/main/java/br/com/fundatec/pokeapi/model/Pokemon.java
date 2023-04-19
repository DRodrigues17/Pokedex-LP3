package br.com.fundatec.pokeapi.model;

import br.com.fundatec.pokeapi.dto.MovesDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Document
public class Pokemon {

    @Id
    private UUID id;
    private Integer externalId;
    private String name;
    private int height;
    private int weight;
    private List<MovesDTO> moves;
    private boolean deleted;
    private LocalDateTime deletedAt;
}
