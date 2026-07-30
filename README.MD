# MatchSkills - User Service

Microsserviço responsável pela **gestão de usuários** da plataforma MatchSkills: cadastro, autenticação e edição de dois tipos de conta — **Candidatos** e **Empresas** — com autenticação stateless via **JWT** (access + refresh token) e suporte a **logout/blacklist de tokens** via Redis.

## Stack Técnica

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 4.1.0 |
| Build | Gradle |
| Segurança | Spring Security + JWT (`java-jwt` / Auth0) |
| Persistência | Spring Data JPA + PostgreSQL (produção) / H2 (console/dev) |
| Migrações | Flyway |
| Cache / Blacklist de tokens | Redis (Spring Data Redis) |
| Validação | Bean Validation (`spring-boot-starter-validation`) |
| Boilerplate | Lombok |
| Variáveis de ambiente | `spring-dotenv` (leitura de `.env`) |
| Observabilidade | Spring Boot Actuator |

## Arquitetura

O projeto segue uma organização em camadas dentro do pacote `com.matchskills.user.service`:

```
controllers/     -> Endpoints REST (Auth, Candidate, Company, Server/health)
services/        -> Regras de negócio (AuthService, CandidateService, CompanyService, RedisBlackListService)
repositorys/     -> Repositórios JPA (CandidateRepository, CompanyRepository)
entitys/         -> Entidades JPA (CandidateEntity, CompanyEntity)
domains/         -> Modelos de domínio (CandidateDomain, CompanyDomain, TokenDecoded)
dtos/            -> Objetos de request/response (auth, candidate, company, tokens)
jwt/             -> Geração e validação de tokens (JwtService)
configurations/  -> Spring Security (SecurityConfigurations, SecurityFilter)
exceptions/      -> Exceptions customizadas + handlers globais por domínio
enums/           -> RoleType (Company, Candidate)
```

### Fluxo de autenticação

1. Registro (`/auth/register/candidate` ou `/auth/register/company`) ou login (`/auth/login/candidate` ou `/auth/login/company`) geram um par de tokens (`accessToken` de 15 minutos e `refreshToken` de 7 dias), assinados com HMAC256.
2. Cada requisição autenticada envia `Authorization: Bearer <accessToken>`. O `SecurityFilter` decodifica o token, valida se não está na blacklist do Redis e carrega o usuário (`Candidate` ou `Company`) correspondente no `SecurityContext`, aplicando a role (`hasRole('Candidate')` / `hasRole('Company')`) usada nos endpoints protegidos.
3. `/auth/refresh` invalida o refresh token atual (adicionando à blacklist) e emite um novo par de tokens.
4. `/auth/logout` adiciona tanto o access quanto o refresh token à blacklist no Redis, com TTL igual ao tempo restante de validade de cada um.

### Segurança

- Sessão **stateless** (sem sessão de servidor).
- Senhas criptografadas com **BCrypt**.
- CSRF e frame options desabilitados (API REST stateless).
- Rotas públicas: `POST /auth/register/**`, `POST /auth/login/**`, `POST /auth/refresh`, `GET /` (health check).
- Demais rotas exigem autenticação; algumas exigem role específica via `@PreAuthorize`.

## Documentação das rotas

A documentação detalhada de cada grupo de endpoints (request/response e códigos de retorno) está na pasta [`docs/`](./docs):

- [`docs/auth/auth.MD`](./docs/auth/auth.MD) — Registro, login, logout e refresh de tokens para Candidatos e Empresas.
- [`docs/candidates/candidates.MD`](./docs/candidates/candidates.MD) — Consulta, edição e remoção de Candidatos.
- [`docs/companies/companies.MD`](./docs/companies/companies.MD) — Consulta, edição e remoção de Empresas.

> Observação: os endpoints de criação (`POST /candidates` e `POST /companies`) constam como **não implementados** nesses documentos — o cadastro é feito exclusivamente pelas rotas de `auth/register/**`.

## Configuração e execução local

### Pré-requisitos

- JDK 21
- PostgreSQL (produção/dev) — H2 disponível via `h2console` para testes rápidos
- Redis em execução

### Variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto com base em [`.envexemple`](./.envexemple):

```
REDIS_URL=
SECRET=
ISSUER=
AUDIENCE=
```

| Variável | Descrição |
|---|---|
| `REDIS_URL` | URL de conexão do Redis, usada para a blacklist de tokens |
| `SECRET` | Chave secreta usada para assinar os tokens JWT (HMAC256) |
| `ISSUER` | Issuer (`iss`) incluído nos tokens JWT |
| `AUDIENCE` | Audience (`aud`) validado nos tokens JWT |

As migrações do banco (Flyway) e a URL/credenciais do PostgreSQL devem ser configuradas conforme o ambiente (o `application.properties` já referencia `spring.config.import` para carregar o `.env`).

### Rodando o projeto

```bash
./gradlew bootRun
```

### Rodando os testes

```bash
./gradlew test
```

## Modelo de dados

- **candidates**: `id`, `name`, `email`, `password`, `number`
- **companies**: `id`, `name`, `cnpj`, `email`, `password`, `address`

Migrações Flyway em [`src/main/resources/db/migration`](./src/main/resources/db/migration):
- `V1__create_candidate_table.sql`
- `V2__create_company_table.sql`
