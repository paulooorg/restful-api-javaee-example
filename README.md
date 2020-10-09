# WIP

# RESTful API com Java EE

Esse é um projeto de exemplo, para demonstrar a utilização de algumas tecnologias Java na construção de uma API RESTful.

O projeto utiliza as seguintes tecnologias:

* [Liquibase](https://www.liquibase.org/) para versionamento de banco de dados
* [Dropwizard Metrics](https://metrics.dropwizard.io/4.1.2/) para monitoramento da API
* [Mapstruct](https://mapstruct.org/) para conversão dos Java Beans para DTO e vice-versa
* [Hibernate](https://hibernate.org/) como Framework para o mapeamento objeto-relacional
* [Swagger](https://swagger.io/) para documentação da API
* Java EE 8 com JAX-RS, CDI e JPA

# O que a API faz?

A API realiza a simulação de empréstimos usando os sistemas de amortizações SAC e Price. Fornece o controle de usuários e perfis e autenticação com JWT.

# Dados básicos para teste

Com a API funcionando é possível fazer login no caminho /api/v1/login usando um usuário de teste:

```json
{
    "username": "gordon.freeman",
    "password": "blackmesa"
}
```

Além desse usuário, existem alguns dados de teste que podem ser consultados

# Endpoints

Todos os Endpoints e seus exemplos também podem ser visualizados no Postman usando o arquivo [Api.postman_collection.json](docs/Api.postman_collection.json)

### Filtering

A API possui vários filtros que podem ser utilizados como Query Param:

* less
* lessequal
* equal
* greaterequal
* greater
* notequal
* between
* in
* contains

Para utilizar algum dos filtros é necessário informar no padrão *campo,operador,valor1,valor2,valor3...*

Exemplo: filter=lastLogin,between,2020-01-04T00:00:00.00,2020-12-08T00:00:00.00

As datas seguem o padrão ISO-8601

### Sorting

É possível realizar o Sorting dos dados usando o Query Param:

* sort

Exemplo: sort=id,ASC

Os valores aceitos são ASC e DESC, sendo DESC o padrão, caso não seja informado

### Pagination

Para paginar os resultados de uma busca pode ser utilizando os Query Params:

* page
* per_page

Exemplo: page=1&per_page=10

# Como executar o projeto?

## Docker

Com Docker instalado é possível executar a API. Para isso basta executar um dos Scripts disponíveis na raiz do projeto

### Windows

Executar build-exec-docker.bat

### Linux

Executar build-exec-docker.sh

## Configurando o projeto no Wildfly

A API também pode ser configurando em um servidor de aplicação Wildfly sem usar o Docker. Na pasta docs existem os exemplos de datasource para serem utilizados
na configuração do Wildfly.

## Compilando projeto

O projeto utiliza Maven, então para gerar o deploy pode ser executado:

```bash
mvn clean install
```

Se a máquina local estiver executando um servidor de aplicação Wildfly pode ser acrescentado a profile local para fazer o Deploy automático

```bash
mvn clean install -Plocal
```

## Outros

### Fake SMTP

A API faz o envio de e-mail para a ativação de usuários registrados e reset da senha. Por padrão está configurando em application.properties um servidor SMPT local, dessa forma é possível usar a aplicação Fake SMTP [fakeSMTP-2.1.jar](docs/fakeSMTP-2.1.jar) para simular um servidor SMTP local.

### Postman

Todos os Endpoints e exemplos de uso estão documentados no arquivo docs/Api.postman_collection.json

### Swagger

Existe uma documentação dos Endpoints da API, essa documentação é gerada de forma automática. O json para o Swagger fica disponível no caminho /api/v1/openapi.json.
Em docs/swagger-doc/dist existe um index.html que apresenta esse arquivo json.