# Spring Boot REST API - Job Post & Security Management

Uma API RESTful desenvolvida com **Java 21** e **Spring Boot**, focada no gerenciamento de vagas de emprego (Job Posts) e controle de acesso seguro de usuários. Este projeto foi construído para demonstrar conhecimentos práticos em persistência de dados, boas práticas de design de APIs, documentação automatizada e implementação de segurança.

---

## 🚀 Funcionalidades Principais

### 💼 Gerenciamento de Vagas (CRUD Completo)
* **Criação de Vagas:** Permite cadastrar novas oportunidades com perfil, descrição, anos de experiência e stack tecnológica necessária, retornando o status HTTP correto (`201 Created`).
* **Listagem e Consulta:** Busca de todas as vagas registradas ou de uma vaga específica através de seu ID.
* **Atualização e Remoção:** Edição completa dos dados de uma vaga existente e exclusão segura por ID.
* **Busca Avançada:** Endpoint otimizado para pesquisar vagas filtrando por palavras-chave tanto no perfil quanto na descrição.
* **Carga Inicial de Dados:** Endpoint utilitário (`/load`) para popular o banco de dados rapidamente com registros fictícios para testes.

### 🔒 Segurança e Autenticação (Spring Security)
* **Registro de Usuários:** Endpoint para criação de novos usuários com criptografia forte de senhas.
* **Criptografia com BCrypt:** Uso do algoritmo BCrypt (força 12) para garantir que as senhas sejam salvas com hash seguro no banco de dados.
* **Autenticação Stateless:** Configuração de sessão *stateless* (sem estado), ideal para arquiteturas de APIs modernas.
* **Filtros de Acesso:** Proteção de endpoints de gerenciamento de vagas exigindo autenticação prévia (`HttpBasic`).
* **UserDetailsService Personalizado:** Integração com o banco de dados para carregar credenciais de usuários em tempo de execução via JPA.

### 📄 Documentação Interativa (Swagger/OpenAPI)
* Interface gráfica nativa para testar e visualizar todos os endpoints da API sem a necessidade de ferramentas externas (como Postman ou Insomnia).

---

## 🛠️ Tecnologias e Ferramentas

* **Linguagem:** Java 21
* **Framework Principal:** Spring Boot
* **Segurança:** Spring Security (Autenticação Básica & BCrypt)
* **Documentação:** Springdoc OpenAPI (Swagger UI)
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL
* **Produtividade:** Project Lombok (Redução de código boilerplate)
* **Gerenciador de Dependências:** Maven

---

## 🧠 Decisões Arquiteturais e Boas Práticas

1. **REST Design (Query Params vs Path Variables):** No endpoint de busca (`/JobPost/search`), optou-se pelo uso de `@RequestParam` em vez de variáveis de caminho. Essa abordagem evita quebras na URL caso as palavras-chave contenham espaços ou caracteres especiais (ex: `C++`, `Java Developer`).
2. **Camadas Bem Definidas (Layered Architecture):** O projeto segue a divisão clássica de responsabilidades (Model/Entity, Repository, Service, Controller), facilitando a manutenção e a escalabilidade.
3. **Derived Queries:** Utilização inteligente do Spring Data JPA (`findByPostProfileContainingIgnoreCaseOrPostDescContainingIgnoreCase`) para consultas dinâmicas sem a necessidade de escrever SQL manual.
4. **CORS Configurado:** Suporte a Cross-Origin Resource Sharing (`@CrossOrigin`) habilitado para integrações com aplicações Frontend (ex: `http://localhost:3000`).

---

## 📋 Endpoints da API

### Documentação
| Método | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `GET` | `/swagger-ui/index.html` | Interface interativa do Swagger para testar a API | Público |
| `GET` | `/v3/api-docs` | Especificação OpenAPI em formato JSON | Público |

### Autenticação / Usuários
| Método | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/register` | Cadastra um novo usuário (Gera senha hash) | Público |
| `GET` | `/login` | Valida as credenciais do usuário | Público |

### Vagas (Job Posts) - *Requer Autenticação*
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/JobPosts` | Retorna todas as vagas |
| `GET` | `/JobPost/{postId}` | Retorna uma vaga específica pelo ID |
| `GET` | `/JobPost/search?keyword=...` | Filtra vagas por palavra-chave |
| `POST` | `/JobPost` | Cria uma nova vaga |
| `PUT` | `/JobPost` | Atualiza os dados de uma vaga existente |
| `DELETE` | `/JobPost/{postId}` | Remove uma vaga pelo ID |
| `GET` | `/load` | Carrega uma lista de vagas mockadas no banco de dados |

---

## 📦 Como Executar o Projeto

### Pré-requisitos
* Java 21 instalado.
* MySQL Server rodando localmente.

### Executando a Aplicação
Após iniciar, acesse http://localhost:8080/swagger-ui/index.html no seu navegador para testar a API.

### 1. Configuração do Banco de Dados
No arquivo `src/main/resources/application.properties`, ajuste as credenciais do seu MySQL e crie o schema `SpringJPA`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/SpringJPA
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
