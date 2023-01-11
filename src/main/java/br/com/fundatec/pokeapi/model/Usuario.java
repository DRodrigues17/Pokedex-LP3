package br.com.fundatec.pokeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Long id;

    private String login;

}
