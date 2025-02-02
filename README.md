# Sistema E-Commerce

Este projeto é uma API para gerenciamento de sitema E-Commerce clientes  que permite o
cadastro de produtos, clientes e a realização de compras

## **Tecnologias Utilizadas**
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- Banco de Dados (ex.: H2, MySQL, PostgreSQL)

---

## **Endpoints**

### **1. Cadastrar Cliente**
**POST** `/clientes`

- **Descrição**: Endpoint para cadastrar um novo cliente.
- **Request Body**:
  ```json
  {
    "nome": "João da Silva",
    "cpf": "12345678901",
    "email": "joao.silva@example.com"
  }

Response:
Status 200 (OK):

    {
    "id": 1,
    "nome": "João da Silva",
    "cpf": "12345678901",
    "email": "joao.silva@example.com"
    }

Response: Status 400 (Bad Request): Dados inválidos.

### **2. Consultar Cliente por CPF**
**GET** `/clientes/{cpf}`

- **Descrição**: Endpoint para buscar um cliente pelo CPF.
- **Path Parameter**:

cpf (String): CPF do cliente a ser consultado.

Response:
Status 200 (OK):

### **3. Atualizar Cliente**
**PUT** `/clientes/{cpf}`

**Descrição**: Endpoint para atualizar os dados de um cliente existente.

**Path Parameter**:

- cpf (String): CPF do cliente a ser atualizado.
  - Request Body:
  ```json
  {    
    "nome": "João da Silva atualizado",
    "cpf": "12345678901",
    "email": "joao.atualizado@example.com"
  }

Response:
Status 200 (OK):
  ```json
   {
     "id": 1,
     "nome": "João da Silva Atualizado",
     "cpf": "12345678901",
     "email": "joao.atualizado@example.com"
   }



