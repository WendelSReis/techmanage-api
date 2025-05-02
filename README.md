# TechManage API – Spring Boot 3.4.5 🚀

API RESTful para gerenciamento de usuários utilizada no desafio **TechManage**.

---

## ✨ Visão Geral
* CRUD completo de usuários (`User`)
* Validações Bean Validation
* Banco de dados em **H2** (profile `default`) e suporte a MySQL/PostgreSQL (`prod`)
* Testes unitários (Mockito/JUnit 5) e teste de integração (MockMvc)
* Documentação rápida neste README

## 🛠️ Tecnologias & Dependências

| Ferramenta | Versão |
|------------|----|
| Java       | 21 |
| Spring Boot| 3.4.5 |
| Spring Web | —  |
| Spring Data JPA | —  |
| Bean Validation| —  |
| Lombok     | 1.18.x |
| H2 Database| 2.x |
| Maven Wrapper| 3.9.9 |

## ⚙️ Requisitos

* JDK 21+
* Maven 3.9+ ou usar `./mvnw`
* Git

## ▶️ Como Rodar Localmente

```bash
# clone o repositório
git clone https://github.com/<seu‑user>/techmanage-api.git
cd techmanage-api

# execute
./mvnw spring-boot:run
```

O serviço inicia em `http://localhost:8080`.

Acesse o console H2 em `http://localhost:8080/h2-console` (JDBC URL padrão: `jdbc:h2:mem:techmanage`).

## 🗄️ Configuração do H2

O **H2** já vem configurado no profile padrão através do arquivo [`src/main/resources/application.yml`](src/main/resources/application.yml):

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:techmanage;DB_CLOSE_DELAY=-1
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    defer-datasource-initialization: true
    properties:
      hibernate:
        format_sql: true
  h2:
    console:
      enabled: true
      path: /h2-console
```

## 📑 Exemplos de Requisições

```bash
# Criar usuário
curl -X POST http://localhost:8080/api/users  -H "Content-Type: application/json"  -d '{"fullName":"Ana Silva","email":"ana@ex.com","phone":"+55 11 99999-9999","birthDate":"1990-01-01","userType":"ADMIN"}'

# Listar todos
curl -X GET http://localhost:8080/api/users

# Buscar por id
curl -X GET http://localhost:8080/api/users/1

# Atualizar
curl -X PUT http://localhost:8080/api/users/1  -H "Content-Type: application/json"  -d '{"fullName":"Ana S. Silva","email":"ana@ex.com","phone":"+55 11 98888-7777","birthDate":"1990-01-01","userType":"EDITOR"}'

# Excluir
curl -X DELETE http://localhost:8080/api/users/1
```

## 🧪 Rodar Testes

```bash
./mvnw test
```

Saída esperada: `BUILD SUCCESS`.

* Basta rodar `./mvnw spring-boot:run` e acessar `http://localhost:8080/h2-console`.
* JDBC URL: `jdbc:h2:mem:techmanage`
* Usuário: `sa` (sem senha)

### Dados iniciais (`data.sql`)

Para popular o banco em memória toda vez que a aplicação sobe, crie o arquivo
`src/main/resources/data.sql` com o conteúdo abaixo:

```sql
INSERT INTO users (full_name, email, phone, birth_date, user_type) VALUES
  ('Ana Silva',   'ana@ex.com',    '+55 11 99999-9999', '1990-01-01', 'ADMIN'),
  ('Bruno Costa', 'bruno@ex.com',  '+55 21 98888-7777', '1988-05-15', 'EDITOR'),
  ('Carlos Souza','carlos@ex.com', '+55 31 97777-6666', '1995-09-30', 'VIEWER');
```

---

## 📂 JSONs de exemplo para testes manuais

```jsonc
// POST /api/users
{
  "fullName": "João Pereira",
  "email": "joao@ex.com",
  "phone": "+55 48 98888-5555",
  "birthDate": "1987-12-10",
  "userType": "EDITOR"
}

// PUT /api/users/{id}
{
  "fullName": "João P. Pereira",
  "email": "joao@ex.com",
  "phone": "+55 48 97777-4444",
  "birthDate": "1987-12-10",
  "userType": "VIEWER"
}
```