# Reserva Lá - Sistema de Gerenciamento de Quartos e Reservas de Hotel

O projeto **Reserva Lá** é uma aplicação que permite o gerenciamento de quartos de hotel, reservas, relatórios de ocupação e receitas. Ele foi desenvolvido utilizando Java 21, Spring Boot e um banco de dados em memória H2. Além disso, os testes automatizados são realizados com as bibliotecas Mockito e JUnit.

## Funcionalidades

### Gerenciamento de Quartos

- **CRUD completo para quartos:**
  - Criar, Ler, Atualizar e Deletar quartos.
  - Cada quarto possui as seguintes informações:
    - Número
    - Tipo (por exemplo: suíte, standard, etc.)
    - Preço
    - Disponibilidade (se está ocupado ou livre)

### Reservas

- **Funcionalidades relacionadas a reservas:**
  - Permitir que os clientes façam reservas de quartos através da API.
  - Gerenciar datas de check-in e check-out.
  - Cancelar e modificar reservas existentes.

### Relatórios

- **Relatórios disponíveis:**
  - Ocupação: Mostra a taxa de ocupação dos quartos.
  - Receitas: Exibe o total de receitas geradas pelas reservas.

## Configuração do Ambiente

1. **Clonar o Repositório:**
   ```
   git clone https://github.com/roberttiss/reservala.git
   ```

2. **Executar a Aplicação:**
   - Navegue até o diretório do projeto:
     ```
     cd reservala
     ```
   - Execute a aplicação:
     ```
     ./mvnw spring-boot:run
     ```
   - Acesse a aplicação em `http://localhost:8080`

## **Endpoints da API:**
   - Quartos:
     - `GET /room`: Lista todos os quartos
     - `GET /room/{id}`: Retorna um quarto específico
     - `POST /room`: Cria um novo quarto
     - `PUT /room/{id}`: Atualiza um quarto existente
     - `DELETE /room/{id}`: Remove um quarto

   - Cliente:
     - `GET /client`: Lista todos os clientes
     - `GET /client/{id}`: Retorna um cliente específico
     - `POST /client`: Cria um novo cliente
     - `PUT /client/{id}`: Atualiza um cliente existente
     - `DELETE /client/{id}`: Remove um cliente

   - Reservas:
     - `POST /reservation`: Cria uma nova reserva
     - `GET /reservation/{id}`: Retorna uma reserva específica
     - `GET /reservation/client/{id}`: Retorna todas as reserva de um cliente específico
     - `PUT /reservation/{id}`: Atualiza uma reserva existente
     - `DELETE /reservation/{id}`: Cancela uma reserva

   - Relatórios:
     - `GET /ocupation`: Retorna o relatório de ocupação
     - `GET /renevue`: Retorna o relatório de receitas

## Testes Automatizados Implementados

- Testes Unitários
- Testes de Integração
