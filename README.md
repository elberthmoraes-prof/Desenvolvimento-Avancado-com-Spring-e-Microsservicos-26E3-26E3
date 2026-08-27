# Desenvolvimento Avançado com Spring e Microsserviços

Projeto desenvolvido como aplicação de referência para a disciplina **Desenvolvimento de Aplicações Java com Spring Boot**.

Este repositório acompanha a implementação realizada durante as aulas e foi desenvolvido a partir das **features previstas no Projeto de Disciplina**, seguindo uma evolução progressiva: modelagem orientada a objetos, manipulação de dados em memória, organização em serviços, criação de API REST com Spring Boot e persistência com Spring Data JPA.

A proposta não é apenas demonstrar anotações ou recursos isolados do Spring, mas mostrar **por que cada nova responsabilidade passa a ser necessária à medida que uma aplicação evolui**.

## Repositório

```text
https://github.com/elberthmoraes-prof/Desenvolvimento-Avancado-com-Spring-e-Microsservicos-26E3-26E3
```

---

## Visão geral

O projeto utiliza um domínio acadêmico simplificado, composto por elementos como:

* **Turma**
* **Comunicado**
* **Escola**
* **Pessoa**
* **Professor**
* **Responsável**

Ao longo da evolução da aplicação, essas classes foram utilizadas para trabalhar conceitos de orientação a objetos, estruturas de dados, serviços, APIs REST, persistência, relacionamentos, validação e tratamento de erros.

A arquitetura principal da versão atual segue o fluxo:

```text
Cliente HTTP
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Spring Data JPA
     ↓
Banco de Dados
```

---

# Objetivos do projeto

O projeto demonstra, entre outros conceitos:

* modelagem orientada a objetos;
* relacionamentos entre objetos;
* herança e abstração;
* interfaces e generics;
* Collections;
* armazenamento temporário utilizando `Map`;
* expressões lambda;
* Streams;
* tratamento de exceções;
* camada de serviço;
* desenvolvimento de APIs REST;
* métodos HTTP;
* códigos de resposta HTTP;
* Inversão de Controle;
* Injeção de Dependência;
* Spring Data JPA;
* mapeamento de entidades;
* relacionamento `@OneToMany`;
* relacionamento `@ManyToOne`;
* consultas derivadas utilizando `findBy`;
* Bean Validation;
* tratamento global de exceções;
* serialização de relacionamentos;
* documentação utilizando OpenAPI e Swagger.

---

# Tecnologias utilizadas

| Tecnologia       | Utilização                                         |
| ---------------- | -------------------------------------------------- |
| Java             | Linguagem principal                                |
| Spring Boot      | Estrutura principal da aplicação                   |
| Spring Web / MVC | Desenvolvimento da API REST                        |
| Spring Data JPA  | Camada de persistência                             |
| Hibernate        | Implementação ORM                                  |
| H2 Database      | Banco de dados utilizado durante o desenvolvimento |
| Bean Validation  | Validação dos dados recebidos                      |
| Jackson          | Serialização e desserialização JSON                |
| OpenAPI          | Especificação da documentação da API               |
| Swagger UI       | Visualização e execução dos endpoints              |
| Maven            | Gerenciamento de dependências e build              |
| Git              | Controle de versão                                 |

---

# Evolução da aplicação

Uma característica importante deste projeto é que as etapas representam **momentos da evolução de uma mesma aplicação**.

A implementação foi sendo modificada conforme novos conceitos e necessidades eram introduzidos durante a disciplina.

## Etapa 1 — Orientação a Objetos

A primeira etapa teve como foco a construção do modelo de domínio utilizando recursos da orientação a objetos.

Foram trabalhados conceitos como:

* classes e objetos;
* atributos;
* métodos;
* construtores;
* encapsulamento;
* relacionamento entre objetos;
* relacionamento um-para-muitos;
* herança;
* abstração;
* interfaces;
* polimorfismo;
* implementação de `toString()`.

Exemplo conceitual:

```text
Pessoa
 ├── Professor
 └── Responsavel
```

Outro relacionamento presente no domínio:

```text
Escola
   ↓
 Turma
   ↓
Comunicado
```

---

## Etapa 2 — Estruturas de Dados e Serviços

A segunda etapa introduziu estruturas de dados e uma primeira separação das responsabilidades da aplicação.

O armazenamento foi inicialmente simulado utilizando:

```java
Map<Long, T>
```

A arquitetura nesse momento poderia ser representada por:

```text
Aplicação
    ↓
 Service
    ↓
   Map
```

Foram trabalhados:

* `Map`;
* `List`;
* Generics;
* Lambdas;
* Streams;
* filtragem;
* ordenação;
* busca;
* transformação;
* exceções;
* operações CRUD em memória;
* camada de serviço.

Entre as operações utilizadas com Streams estão:

```java
filter()
sorted()
map()
```

Essa etapa permitiu trabalhar regras e operações da aplicação antes da introdução de banco de dados.

---

## Etapa 3 — API REST com Spring Boot

Na terceira etapa, as funcionalidades passaram a ser disponibilizadas através de HTTP.

A arquitetura evoluiu para:

```text
Cliente HTTP
     ↓
Controller
     ↓
 Service
     ↓
   Map
```

Foram utilizados recursos como:

```java
@RestController
@RequestMapping
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PathVariable
@RequestParam
@RequestBody
```

Também foram trabalhados:

* Injeção de Dependência;
* Inversão de Controle;
* `ResponseEntity`;
* códigos HTTP;
* tratamento global de exceções;
* testes da API;
* documentação dos endpoints.

Entre os principais códigos HTTP utilizados estão:

```text
200 OK
201 Created
204 No Content
400 Bad Request
404 Not Found
409 Conflict
```

---

## Etapa 4 — Persistência com Spring Data JPA

Na etapa final, o armazenamento temporário em memória foi substituído pela persistência utilizando Spring Data JPA.

A arquitetura passou a ser:

```text
Cliente HTTP
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Spring Data JPA
     ↓
Banco de Dados
```

Entre os principais recursos utilizados estão:

```java
@Entity
@Id
@GeneratedValue
@OneToMany
@ManyToOne
@JoinColumn
```

Os repositories utilizam:

```java
JpaRepository
```

permitindo operações como:

```java
save()
findById()
findAll()
delete()
```

Também são utilizadas consultas derivadas do Spring Data, por exemplo:

```java
findByPublicadoTrue()
findByTituloContainingIgnoreCase(...)
findByAnoLetivo(...)
```

---

# Domínio principal

## Turma

Representa uma turma cadastrada na aplicação.

Principais atributos:

```text
id
nome
anoLetivo
ativa
```

Uma turma pode possuir diversos comunicados.

```text
Turma 1 ───── N Comunicado
```

No JPA:

```java
@OneToMany(mappedBy = "turma")
```

---

## Comunicado

Representa um comunicado do domínio acadêmico.

Principais atributos:

```text
id
titulo
conteudo
publicado
dataPublicacao
```

Um comunicado pode estar associado a uma turma.

No JPA:

```java
@ManyToOne
@JoinColumn(name = "turma_id")
```

---

# Relacionamento entre Turma e Comunicado

O relacionamento principal persistido pela aplicação é:

```text
TURMAS
   ↑
   │
   │ FK turma_id
   │
COMUNICADOS
```

No modelo de objetos:

```text
Turma
  1
  │
  N
Comunicado
```

A aplicação utiliza estratégias do Jackson para evitar referências circulares durante a serialização:

```java
@JsonManagedReference
@JsonBackReference
```

Também existe uma operação específica para criar um comunicado associado a uma turma:

```http
POST /turmas/{turmaId}/comunicados
```

---

# Principais endpoints

## Turmas

### Listar todas as turmas

```http
GET /turmas
```

### Buscar turma pelo identificador

```http
GET /turmas/{id}
```

### Cadastrar turma

```http
POST /turmas
```

Exemplo:

```json
{
  "nome": "Turma Java 2026",
  "anoLetivo": 2026,
  "ativa": true
}
```

### Alterar turma

```http
PUT /turmas/{id}
```

### Excluir turma

```http
DELETE /turmas/{id}
```

### Filtrar turmas pelo ano letivo

```http
GET /turmas?anoLetivo=2026
```

### Adicionar comunicado a uma turma

```http
POST /turmas/{turmaId}/comunicados
```

Exemplo:

```json
{
  "titulo": "Reunião de responsáveis",
  "conteudo": "A reunião será realizada na próxima sexta-feira.",
  "publicado": true
}
```

---

## Comunicados

### Listar todos os comunicados

```http
GET /comunicados
```

### Buscar comunicado pelo identificador

```http
GET /comunicados/{id}
```

### Cadastrar comunicado

```http
POST /comunicados
```

Exemplo:

```json
{
  "titulo": "Entrega do projeto",
  "conteudo": "O projeto deverá ser entregue através do repositório Git.",
  "publicado": true
}
```

### Alterar comunicado

```http
PUT /comunicados/{id}
```

### Excluir comunicado

```http
DELETE /comunicados/{id}
```

### Consultar comunicados publicados

```http
GET /comunicados/publicados
```

### Buscar comunicados pelo título

```http
GET /comunicados/busca?titulo=reuniao
```

---

# Validação

A aplicação utiliza Bean Validation para impedir a entrada de dados estruturalmente inválidos.

Entre as anotações utilizadas estão:

```java
@NotBlank
@Size
```

Nos Controllers, a validação é acionada através de:

```java
@Valid
```

Exemplo de requisição inválida:

```json
{
  "titulo": "",
  "conteudo": "",
  "publicado": true
}
```

Nesse caso, a aplicação responde com:

```text
400 Bad Request
```

As falhas de validação são tratadas de forma centralizada pelo `GlobalExceptionHandler`.

---

# Tratamento de erros

A aplicação utiliza:

```java
@RestControllerAdvice
```

para centralizar o tratamento de situações excepcionais.

Exemplos:

| Situação            |              HTTP |
| ------------------- | ----------------: |
| Dados inválidos     | `400 Bad Request` |
| Recurso inexistente |   `404 Not Found` |
| Conflito            |    `409 Conflict` |

Exemplo:

```http
GET /comunicados/999
```

Caso o comunicado não exista:

```text
404 Not Found
```

---

# Documentação da API

A API possui documentação utilizando **OpenAPI** e **Swagger UI**.

Com a aplicação em execução:

```text
http://localhost:8080/swagger-ui/index.html
```

A especificação OpenAPI também pode ser consultada em:

```text
http://localhost:8080/v3/api-docs
```

Através do Swagger UI é possível:

* visualizar os recursos disponíveis;
* consultar parâmetros;
* analisar as estruturas das requisições;
* verificar respostas;
* executar requisições diretamente pela interface.

---

# Banco de dados

O projeto utiliza **H2 Database**.

Configuração de desenvolvimento:

```properties
spring.datasource.url=jdbc:h2:mem:elberthdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

O console pode ser disponibilizado através de:

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Acesso:

```text
http://localhost:8080/h2-console
```

Utilize:

```text
JDBC URL: jdbc:h2:mem:elberthdb
User Name: sa
Password:
```

---

# Execução

## Pré-requisitos

É necessário possuir:

* Java instalado;
* Git instalado.

O projeto utiliza Maven Wrapper, portanto não é obrigatório possuir uma instalação global do Maven.

---

## Clonar o projeto

```bash
git clone https://github.com/elberthmoraes-prof/Desenvolvimento-Avancado-com-Spring-e-Microsservicos-26E3-26E3.git
```

Acesse a pasta:

```bash
cd Desenvolvimento-Avancado-com-Spring-e-Microsservicos-26E3-26E3
```

---

## Executar no Linux ou macOS

```bash
./mvnw spring-boot:run
```

## Executar no Windows

```bash
mvnw.cmd spring-boot:run
```

Por padrão, a aplicação estará disponível em:

```text
http://localhost:8080
```

---

# Carga de dados de demonstração

O projeto possui um `ProjetoRunner` que pode carregar dados de demonstração automaticamente.

No arquivo:

```text
application.properties
```

utilize:

```properties
app.runner.enabled=true
```

para habilitar a carga.

Para iniciar a aplicação sem carga automática:

```properties
app.runner.enabled=false
```

Quando desabilitado, o Runner não realiza alterações no banco.

---

# Testando a API

A API pode ser testada utilizando:

* Swagger UI;
* Postman;
* Insomnia;
* cURL;
* outro cliente HTTP equivalente.

Recomenda-se validar pelo menos:

```text
POST
GET
GET por ID
PUT
DELETE
```

além das consultas específicas disponibilizadas pela aplicação.

---

# Estrutura conceitual

```text
src/main/java
│
└── br.edu.infnet.elberth_api
    │
    ├── controller
    │   ├── ComunicadoController
    │   └── TurmaController
    │
    ├── service
    │   ├── ComunicadoService
    │   └── TurmaService
    │
    ├── repository
    │   ├── ComunicadoRepository
    │   └── TurmaRepository
    │
    ├── domain
    │   ├── Comunicado
    │   ├── Turma
    │   ├── Escola
    │   ├── Pessoa
    │   ├── Professor
    │   └── Responsavel
    │
    └── exception
        └── tratamento global de erros
```

---

# Separação de responsabilidades

## Controller

Responsável pela comunicação HTTP.

```text
HTTP Request
     ↓
Controller
```

## Service

Responsável pela coordenação das operações e regras da aplicação.

```text
Controller
    ↓
Service
```

## Repository

Responsável pelo acesso aos dados.

```text
Service
   ↓
Repository
   ↓
Banco
```

Essa separação permite que detalhes relacionados à persistência permaneçam isolados da camada responsável pela comunicação HTTP.

---

# Projeto acadêmico e evolução didática

Este repositório possui finalidade educacional.

Algumas estruturas foram criadas especificamente para permitir que os conceitos fossem apresentados de forma incremental durante as aulas.

Um exemplo é a evolução de:

```text
Service
   ↓
Map
```

para:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Banco
```

A existência de estruturas relacionadas às etapas anteriores no código também ajuda a visualizar a trajetória percorrida durante a disciplina.

A proposta é compreender não somente **como utilizar cada tecnologia**, mas também **por que ela passa a ser necessária conforme a aplicação evolui**.

---

# Uso de Inteligência Artificial

Quando aplicável ao desenvolvimento acadêmico, ferramentas baseadas em Inteligência Artificial devem ser utilizadas de acordo com as orientações estabelecidas para a disciplina.

O uso pode apoiar atividades como:

* esclarecimento de dúvidas;
* configuração de frameworks;
* depuração;
* documentação;
* melhoria da qualidade do código.

A modelagem da solução, implementação das camadas, APIs, persistência e demais funcionalidades devem ser desenvolvidas e compreendidas pelo autor do projeto.

Fontes e ferramentas utilizadas devem ser devidamente citadas quando exigido pelas regras acadêmicas.

---

# Checklist técnico

Antes de considerar o projeto concluído, recomenda-se verificar:

* [ ] Projeto executando corretamente
* [ ] CRUD de `Turma` funcionando
* [ ] CRUD de `Comunicado` funcionando
* [ ] Controllers utilizando Services
* [ ] Services utilizando Repositories nas funcionalidades persistentes
* [ ] Spring Data JPA configurado
* [ ] Banco H2 funcionando
* [ ] Relacionamento `Turma 1:N Comunicado` funcionando
* [ ] Associação de comunicado a uma turma funcionando pela API
* [ ] Consultas personalizadas utilizando `findBy`
* [ ] Bean Validation funcionando
* [ ] Respostas HTTP adequadas
* [ ] Tratamento global de erros funcionando
* [ ] Swagger UI disponível
* [ ] Configuração do banco documentada
* [ ] Instruções de execução documentadas
* [ ] Principais requisições testadas

---

# Considerações finais

Mais importante do que conhecer isoladamente anotações como:

```java
@RestController
@Service
@Entity
@OneToMany
@ManyToOne
```

é compreender **por que essas responsabilidades aparecem durante a evolução de uma aplicação**.

O projeto parte de objetos Java e estruturas em memória e evolui progressivamente até uma aplicação Spring Boot organizada em camadas, contendo API REST, validação, tratamento de erros, documentação e persistência em banco de dados.

A proposta é consolidar não apenas o uso do framework Spring, mas principalmente os princípios de **organização, responsabilidade, separação de camadas e desenvolvimento incremental de aplicações Java**.
