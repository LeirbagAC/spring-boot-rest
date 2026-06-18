# Spring Boot REST API - Job Post & Security Management

Este é um projeto de uma API RESTful completa desenvolvida com **Java 21** e **Spring Boot**, focada no gerenciamento de vagas de emprego (Job Posts) e controle de acesso seguro de usuários. O projeto foi construído para consolidar e demonstrar conhecimentos em persistência de dados, boas práticas no design de APIs REST e a implementação de uma camada inicial de segurança.

---

## 🚀 Funcionalidades Principais

### 💼 Gerenciamento de Vagas (CRUD Completo)
* **Criação de Vagas:** Permite cadastrar novas oportunidades com perfil, descrição, anos de experiência e stack tecnológica necessária. Retorna o status HTTP correto (`201 Created`).
* **Listagem e Consulta:** Busca de todas as vagas registradas ou de uma vaga específica através de seu ID.
* **Atualização e Remoção:** Edição completa dos dados de uma vaga existente e exclusão segura por ID.
* **Busca Avançada:** Endpoint otimizado para pesquisar vagas filtrando por palavras-chave tanto no perfil quanto na descrição.
* **Carga Inicial de Dados:** Endpoint utilitário (`/load`) para popular o banco de dados rapidamente com registros fictícios para fins de testes.

### 🔒 Segurança e Autenticação (Spring Security)
* **Registro de Usuários:** Endpoint público para criação de novos usuários com criptografia forte de senhas.
* **Criptografia com BCrypt:** Uso do algoritmo BCrypt (com força/sal de 12) para garantir que nenhuma senha seja salva em texto plano no banco de dados.
* **Autenticação Stateless:** Configuração de uma política de sessão totalmente *stateless* (sem estado), ideal para arquiteturas de APIs modernas baseadas em tokens ou autenticação básica direta por requisição.
* **Filtros de Acesso:** Proteção global de endpoints onde qualquer requisição de gerenciamento de vagas exige autenticação prévia (`HttpBasic`).
* **UserDetailsService Personalizado:** Integração nativa com o banco de dados para carregar as credenciais dos usuários em tempo de execução via JPA.

---

## 🛠️ Tecnologias e Ferramentas Utilizadas

* **Linguagem:** Java 21
* **Framework Principal:** Spring Boot (v4.0.2)
* **Segurança:** Spring Security (Autenticação Básica & Criptografia BCrypt)
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL
* **Produtividade:** Project Lombok (Redução de código boilerplate como getters, setters e construtores)
* **Gerenciador de Dependências:** Maven (com suporte a Maven Wrapper)

---

## 🧠 Decisões Arquiteturais e Boas Práticas

Durante o desenvolvimento deste projeto, foram aplicados conceitos importantes de engenharia de software:

1. **REST Design (Query Params vs Path Variables):** No endpoint de busca (`/JobPost/search`), optou-se pelo uso de `@RequestParam` (parâmetros de consulta) em vez de variáveis de caminho. Essa abordagem evita quebras na URL caso as palavras-chave contenham espaços ou caracteres especiais (ex: `C++`, `Java Developer`).
2. **Camadas Bem Definidas (Layered Architecture):** O projeto segue rigorosamente a divisão de responsabilidades:
   * **Model/Entity:** Mapeamento objeto-relacional com o banco de dados.
   * **Repository:** Interfaces que estendem `JpaRepository`, utilizando *Derived Queries* do Spring Data para consultas dinâmicas sem a necessidade de escrever SQL manual.
   * **Service:** Concentração das regras de negócio e intermediação entre os controllers e o banco de dados.
   * **Controller:** Exposição dos endpoints REST e manipulação de códigos de status HTTP (`ResponseEntity`).
3. **CORS Configurado:** Inclusão de suporte a Cross-Origin Resource Sharing (`@CrossOrigin`) habilitado especificamente para o ambiente de desenvolvimento Frontend local (`http://localhost:3000`).

---

## 📋 Endpoints da API

### Autenticação / Usuários
| Método | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/register` | Cadastra um novo usuário (Gera senha hash) | Público |

### Vagas (Job Posts) - *Requer Autenticação*
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/jobPosts` | Retorna todas as vagas |
| `GET` | `/JobPost/{postId}` | Retorna uma vaga específica pelo ID |
| `GET` | `/JobPost/search?keyword=...` | Filtra vagas por palavra-chave |
| `POST` | `/JobPost` | Cria uma nova vaga (Retorna `201 Created`) |
| `PUT` | `/jobPost` | Atualiza os dados de uma vaga existente |
| `DELETE` | `/JobPost/{postId}` | Remove uma vaga pelo ID |
| `GET` | `/load` | Carrega uma lista de vagas mockadas para testes |

---

## 📦 Como Executar o Projeto

### Pré-requisitos
* Java 21 instalado.
* MySQL Server rodando localmente.

### 1. Configuração do Banco de Dados
No arquivo `src/main/resources/application.properties`, ajuste as credenciais do seu MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/SpringJPA
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
