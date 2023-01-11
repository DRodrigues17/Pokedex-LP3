# Projeto Final - Implementação de uma API REST sobre Pokémon (Pokédex)

- Implementação de uma API REST utilizando Java 17 e Spring Boot 3.0.0
- Utilize versionamento na API (teremos apenas uma V1); 
- A API deve rodar na Porta 8081;
- Utilize Boas Práticas RESTful. Essas questões serão questionadas na apresentação. Link de apoio: https://www.brunobrito.net.br/api-restful-boas-praticas; 
- Utilize de Banco de Dados: PostgreSQL, MongoDB ou outro de sua escolha. Dica: provisione um Banco de Dados em um Container com Docker em ambiente local; 
- Utilize Orientação a Objetos e foque em um bom Design de Código: Clean Code, SOLID, Patterns, DRY, KISS e YAGNI; 
- Implemente Testes de Unidade. Pontuação extra para quem implementar Testes de Integração; 
- Para os Testes de Unidade, utilize JUnit e Mockito. Para os Testes de Integração, utilize H₂ Database (ou o próprio Banco de Dados utilizada na aplicação); 
- Integração com API externa utilizando Spring RestTemplate: https://pokeapi.co (v2); 
- Versione o código-fonte no GitHub ou GitLab. Utilize mensagens de commits que sejam claras e assertivas; 
- Documentação de toda API (todos Endpoints) utilizando Swagger (https://swagger.io); 
- Validação dos Endpoints utilizando Postman (https://www.postman.com). Ou outra opção à sua escolha; 
- Necessário utilização de Maven ou Gradle; 
- Necessário utilização de LocalDate e API Streams.


### ENDPOINTS:

#### GET - ...pokedex/api/v1/health:
- Retorna um Status OK informando que a API está no ar;


#### GET - ...pokedex/api/v1/pokemons/{id}:
- Retorna um Pokémon a partir de seu ID
- Em um primeiro momento, busca o Pokémon no Banco de Dados da sua API (interna):
- Caso encontre, retorna o Pokémon normalmente;
- Caso não encontre: 
- Busca o Pokémon na API externa 
- Adiciona o Pokémon na sua API (interna)
- Retorna o Pokémon


#### GET - ...pokedex/api/v1/pokemons/nome/{nome}:
- Retorna um Pokémon a partir de seu Nome 
- A primeira letra do nome do Pokémon deve ser maiúscula 
- Em um primeiro momento, busca o Pokémon no Banco de Dados da sua API (interna):
- Caso encontre, retorna o Pokémon normalmente; 
- Caso não encontre:
- Busca o Pokémon na API externa 
- Adiciona o Pokémon na sua API (interna)
- Retorna o Pokémon


#### DELETE - ...pokedex/api/v1/pokemons/{nome}:
- Remove um Pokémon, a partir do Nome, no Banco de Dados da sua API (interna)
- Caso o Pokémon não exista, retornar HTTP Status Code 400 (com uma mensagem explicativa)
- Para a remoção do Pokémon, utilizar estratégia Lógica (ver sobre Exclusão Lógica)


#### GET - Implementar um Endpoint que:
- Retorna todos os Pokémons que tem mais de X KGs e que estão no Banco de Dados da sua API (interna)
- Como poderíamos implementar com Spring Boot esse Endpoint? Poderíamos utilizar alguma estratégia de filtro?


#### GET - Implementar um Endpoint que:
- Retorna todos os Pokémons que tem mais de X metros de altura que estão no Banco de Dados da sua API (interna)
- Como poderíamos implementar com Spring Boot esse Endpoint? Poderíamos utilizar alguma estratégia de filtro?


#### OBSERVAÇÃO:
- Deve existir uma tabela chamada Log. Essa tabela armazenará Logs das operações DELETE (remoção de Pokémons) da API.
- Para cada remoção de Pokémon (da API interna), armazenar: data e hora da remoção e o ID e Nome do Pokémon removido.