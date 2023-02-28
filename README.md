# POKEDEX
Projeto criado na cadeira de LP3, é uma API de consulta de pokemons por id, nome, peso e altura.
Na wiki estão registras todas as dependencias e técnologias do projeto. 

## Oque é preciso para executar o programa
- Java (17 de preferência, pois é a versão utilzada no projeto)
- Gradle 
- Docker
- Mongo (este é opcional, pois se você tem o docker instalado, pode só seguir o passo a passo que ele já será "instalado"
  no caso de você ter mongo instalado, você não precisa de DOcker, mas eu fortemente recomendo que você utulize ou procure
  aprender esta técnologia)
- Um copilador ou IDE para executar o programa (eu recomendo o IntelliJ, pois com ele você já consegue instalar o gradle e o java sem problemas)

## Como executar o projeto

Primeiro, voce precisa clonar o repositório (estou considerando que você tem o conhecimento minimo de git, mas também se preferir pode baixar o zip).
Depois disso, você deve abrir um terminal na pasta do porjeto e executar o seguinte comando

> docker-compose up

Com este comando, o container de mongo (banco de dados utilizado para este projeto) será criado e a aplicação
poderá fazer seu trabalho sem problemas. 
Após isso, basta executar a aplicação comsua IDE de preferência e acesssar alguma das URLs abaixo.

### para acessar a api via swagger, acesse a seguinte url:
>localhost:8081/pokedex/api/v1/swagger-ui.html

### Urls via navegador, se você quiser usar o postman, na pasta src/main/resources tem um arquivojson para importar as requisições
> localhost:8081/pokedex/api/v1/pokemons/id/{id}

> localhost:8081/pokedex/api/v1/pokemons/name/{name}

> localhost:8081/pokedex/api/v1/pokemons/list/weight/{hectograms}

> localhost:8081/pokedex/api/v1/pokemons/list/height/{decimeters}

> localhost:8081/pokedex/api/v1/pokemons/delete/{name}
