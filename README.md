# **StoreHub - Gerenciamento de Produtos**

O **EasyMart** é um sistema básico de E-Commerce em desenvolvimento, que tem como objetivo simular o funcionamento de uma loja virtual. O sistema permite o cadastro de produtos, clientes e a realização de compras, com validações e manipulação de dados. Atualmente, o projeto está em construção e, nesta fase inicial, os testes relacionados ao cadastro e listagem de produtos estão funcionando corretamente.

## **Funcionalidades**
- **Cadastro de Produtos**: Adicione novos produtos ao sistema com informações como nome, preço, quantidade em estoque e categoria.
- **Listagem de Produtos**: Visualize todos os produtos cadastrados no sistema.
- **Busca de Produto por ID**: Consulte os detalhes de um produto específico.
- **Atualização de Produtos**: Atualize as informações de produtos existentes.
- **Exclusão de Produtos**: Remova produtos do sistema.

### Produtos

**Cadastro de Produtos**:
- Cada produto possui:
    - Nome (único e obrigatório).
    - Descrição.
    - Preço (deve ser maior que 0).
    - Estoque (deve ser maior ou igual a 0).
    - Categoria (obrigatória, como ELETRONICOS, ROUPAS, ALIMENTOS, etc.).
- Não é permitido cadastrar produtos com o mesmo nome.

- **Listagem de Produtos**:
    - Endpoint: `GET /produtos`
    - Retorna todos os produtos cadastrados no sistema.

- **Listagem de Produtos Disponíveis**:
    - Endpoint: `GET /produtos/disponiveis`
    - Retorna apenas os produtos com estoque maior que 0.

- **Atualização de Produtos**:
    - Endpoint: `PUT /produtos/{id}`
    - Permite atualizar as informações de um produto existente.

- **Exclusão de Produtos**:
    - Endpoint: `DELETE /produtos/{id}`
    - Remove um produto do sistema.

### Clientes (Em Desenvolvimento)
- Cadastro de clientes com validações:
    - Nome (obrigatório).
    - CPF (único e válido).
    - Email (único e válido).
- Listagem de clientes e busca por ID.

### Compras (Em Desenvolvimento)
- Registro de compras:
    - Identificação do cliente.
    - Atualização do estoque dos produtos comprados.
    - Validação para impedir a compra de produtos com estoque 0.

---

## **Tecnologias Utilizadas**
- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para criação de aplicações web.
- **JPA/Hibernate**: Para persistência de dados.
- **H2 Database**: Banco de dados em memória para testes e desenvolvimento.

## **Pré-requisitos**
- Java 17 ou superior instalado.
- Maven instalado para gerenciamento de dependências.


##  **Endpoints Disponíveis**

  **Produtos**

    1. Listar Produtos

 Método: GET

 URL: http://localhost:8085/produtos

 Descrição: Retorna a lista de todos os produtos cadastrados.

 Exemplo de Resposta:

[
{
"id": 3,
"name": "Arroz",
"descricao": "Pacote de arroz de 5kg",
"preco": 29.99,
"estoque": 100,
"categoria": "ALIMENTOS"
},
{
"id": 4,
"name": "Camiseta",
"descricao": "Camiseta de algodão tamanho M",
"preco": 49.9,
"estoque": 200,
"categoria": "ROUPAS"
}
]

    2. Buscar Produto por ID

Método: GET

URL: http://localhost:8085/produtos/{{id}}

Descrição: Retorna os detalhes de um produto específico pelo ID.

Exemplo de Resposta:

{
"id": 3,
"name": "Arroz",
"descricao": "Pacote de arroz de 5kg",
"preco": 29.99,
"estoque": 100,
"categoria": "ALIMENTOS"
}

    3. Cadastrar Produto

Método: POST

URL: http://localhost:8085/produtos

Body (JSON):

{
"name": "Celular",
"descricao": "Smartphone com 128GB",
"preco": 1500.99,
"estoque": 10,
"categoria": "ELETRONICOS"
}

    Exemplo de Resposta:

{
"id": 5,
"name": "Celular",
"descricao": "Smartphone com 128GB",
"preco": 1500.99,
"estoque": 10,
"categoria": "ELETRONICOS"
}

    4. Atualizar Produto

Método: PUT

URL: http://localhost:8085/produtos/{{id}}

Body (JSON):

{
"name": "Celular Atualizado",
"descricao": "Smartphone com 256GB",
"preco": 2000.99,
"estoque": 8,
"categoria": "ELETRONICOS"
}

    Exemplo de Resposta:

{
"id": 5,
"name": "Celular Atualizado",
"descricao": "Smartphone com 256GB",
"preco": 2000.99,
"estoque": 8,
"categoria": "ELETRONICOS"
}

    5. Excluir Produto

Método: DELETE

URL: http://localhost:8085/products/{{id}}

Descrição: Remove um produto do sistema.

Exemplo de Resposta:

Status: 204 No Content

## **Como Baixar e Utilizar o Projeto**
1. **Clone o repositório**:
   ```bash
   git clone <git@github.com:LuizMoura-03/EasyMart.git>

2. **Navegue até a pasta do projeto:**

   cd StoreHub

3. **Execute o projeto:**

   mvn spring-boot:run

4. **Acesse a aplicação**

   http://localhost:8085/produtos

5. **Acesse o console do banco de dados H2:

   URL: http://localhost:8085/h2-console

   Configurações:

   JDBC URL: jdbc:h2:mem:storehubdb

   User Name: sa

   Password: (deixe em branco)


## **Observações**

Este projeto está em desenvolvimento e novas funcionalidades serão adicionadas em breve.
O diagrama de classes será atualizado conforme o projeto evoluir