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

### ** Consultar Cliente por CPF **
**GET** `/clientes/{cpf}`

- **Descrição**: Endpoint para buscar um cliente pelo CPF.
- **Path Parameter**:

cpf (String): CPF do cliente a ser consultado.

Response:
Status 200 (OK):

### ** Atualizar Cliente **
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
```
### **1. lista de Produtos**
 **GET** `/produtos`
- **Descrição**: Retorna a lista de todos os produtos cadastrados.
- **Response**:
  - **Status 200 (OK)**:
    ```json
    [
      {
        "id": 1,
        "nome": "Produto A",
        "preco": 10.0,
        "quantidade": 5
      },
      {
        "id": 2,
        "nome": "Produto B",
        "preco": 20.0,
        "quantidade": 10
      }
    ]
    ```

### ** Cadatrar produto **
**POST** `/produtos`
- **Descrição**: Cadastra um novo produto.
- **Request Body**:
  ```json
  {
    "nome": "Produto A",
    "preco": 10.0,
    "quantidade": 5
  }
  ```
Response:
Status 201 (Created):
```json
{
"id": 1,
"nome": "Produto A",
"preco": 10.0,
"quantidade": 5
}
```

Status 400 (Bad Request): Nome duplicado, preço inválido ou quantidade inválida.

DELETE /produtos/{id}

Descrição: Deleta um produto pelo ID.

Response:

Status 204 (No Content): Produto deletado com sucesso.

Status 404 (Not Found): Produto não encontrado.

### **3. Compras** 
**POST** `/compras`
- **Descrição**: Registra uma nova compra e atualiza a quantidade de produtos.
- **Request Body**:
```json
{
"cpf": "12345678901",
"produtos": [
{ "nome": "Produto A" },
{ "nome": "Produto B" }
]
}
```
Response:
Status 201 (Created):
```json
{
"id": 1,
"cliente": {
"id": 1,
"nome": "João da Silva",
"cpf": "12345678901",
"email": "joao.silva@example.com"
},
"produtos": [
{
"id": 1,
"nome": "Produto A",
"quantidade": 4
},
{
"id": 2,
"nome": "Produto B",
"quantidade": 9
}
]
}
```

Status 400 (Bad Request): Produto em falta ou cliente não encontrado.
```json
{
"erro": "Produto em falta: Produto A"
}
```

## **Regras de Negócio**

**Cadastro de Produtos**:
- Nome deve ser único.
- Preço deve ser maior que 0.
- Quantidade deve ser maior ou igual a 0.

**Cadastro de Clientes**:
- CPF e email devem ser únicos e válidos.

**Realização de Compras**:
- Produtos com quantidade 0 não podem ser comprados.
- Caso um produto esteja em falta, nenhuma compra será realizada.

Acesse a API em http://localhost:8080.

```plaintext
+-------------------+   1   +-------------------+   N   +-------------------+
|     Cliente       |<------|     Produto       |------>|      Compra       |
+-------------------+       +-------------------+       +-------------------+
| - id: Long        |       | - id: Long        |       | - id: Long        |
| - nome: String    |       | - nome: String    |       | - cliente:        |
| - cpf: String     |       | - preco: Double   |       |   ClienteEntity   |
| - email: String   |       | - quantidade: int |       | - produtos:       |
+-------------------+       +-------------------+       |   List<Produto>   |
                                                        +-------------------+