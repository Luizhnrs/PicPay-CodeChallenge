# Desafio PicPay Backend

Este projeto é baseado no desafio de backend do PicPay e tem como objetivo aprimorar minhas habilidades de desenvolvimento de projetos backend. O sistema desenvolvido é uma plataforma simplificada de pagamentos, permitindo a realização de depósitos e transferências entre usuários.

## Índice

- [Introdução](notion://www.notion.so/Picpay-challenge-7092537fc477446890e824af3ced2fc8#introdu%C3%A7%C3%A3o)
- [Funcionalidades](notion://www.notion.so/Picpay-challenge-7092537fc477446890e824af3ced2fc8#funcionalidades)
- [Tecnologias Utilizadas](notion://www.notion.so/Picpay-challenge-7092537fc477446890e824af3ced2fc8#tecnologias-utilizadas)
- [Instalação e Execução](notion://www.notion.so/Picpay-challenge-7092537fc477446890e824af3ced2fc8#instala%C3%A7%C3%A3o-e-execu%C3%A7%C3%A3o)
- [Estrutura do Projeto](notion://www.notion.so/Picpay-challenge-7092537fc477446890e824af3ced2fc8#estrutura-do-projeto)
- [Endpoints](notion://www.notion.so/Picpay-challenge-7092537fc477446890e824af3ced2fc8#endpoints)

## Introdução

O desafio do PicPay consiste em desenvolver uma aplicação backend que permita a realização de transferências de dinheiro entre usuários comuns e lojistas. A aplicação deve seguir os princípios RESTful e implementar validações de saldo, consultas a serviços externos e notificações de pagamento.

## Funcionalidades

- Cadastro de Usuários e Lojistas:
    - Registro de usuários comuns e lojistas com validação de CPF/CNPJ e e-mail únicos.
- Depósitos:
    - Adição de saldo à carteira do usuário.
- Transferências:
    - Transferência de saldo entre usuários e lojistas com validação de saldo e autorização externa.
- Notificações:
    - Envio de notificações via e-mail e SMS para confirmação de transações.

## Tecnologias Utilizadas

- Linguagem: Java 21
- Framework: Spring Boot
- Gerenciamento de Dependências: Maven 4.0.0
- ORM: JPA (Java Persistence API)
- Banco de Dados: MySQL
- Integração com Serviços Externos: OpenFeign
- Utilitários: Lombok, Docker, Mocky, Postman

## Instalação e Execução

### Pré-requisitos

- Java 21
- Maven 4.0.0+
- MySQL

### Passos para Execução

1. Clone o repositório:
    
    ```bash
    git clone <https://github.com/luizhnrs/PicPay-CodeChallenge.git>
    cd PicPay-CodeChallenge
    
    ```
    
2. Configure o banco de dados PostgreSQL no arquivo `application.properties`:
    
    ```
    spring.application.name=picpay
    
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/picpaydb
    spring.datasource.username= your username
    spring.datasource.password= your password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.show-sql=true
    ```
    
3. Execute o comando Maven para iniciar a aplicação:
    
    ```bash
    mvn spring-boot:run
    
    ```
 ## Endpoints

### Cadastro de Usuários
- **POST** `/wallets`
  ```json
  {
    "fullName": "Nome Completo",
    "cpfCnpj": "123.456.789-00",
    "email": "email@exemplo.com",
    "password": "senha"
    "walletType": "USER or MERCHANT"
  }
###Efetuar Tranferencia
  **POST** /transfer
```json
{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}
```
