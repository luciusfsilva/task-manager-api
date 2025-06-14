# Task Manager API

Uma API RESTFUL desenvolvida em Java com Spring Boot para gerenciamento de tarefas (toDos). Suporta autenticação JWT, controle de usuários e operação CRUD em tarefas.

---

# Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA
- Postgresql
- Maven
- Lombok
- MapStruct
- Swagger (SpringDoc OpenAPI)
- JUnit 5 + Mockito
- TestContainers

# Estrutura do Projeto
com.manager.taskmanager |--config|--controller|--dto|--entity|--exception|--repository|--security|--service

# Autenticação

A API usa autenticação JWT. Para acessar os endpoints protegidos, siga os passos:
1. **Registrar usuário**: `POST /api/auth/register`
2. **Login**: `POST /api/auth/login`
3. O login retorna um token JWT. Use-o no header: Autorization: Bearer

## Rodando os Testes
- Testes unitários: `msn test`
- Testes com Testcontainers (Banco real): `@DataJpaTest`, `@SpringBootTest`

## Como rodar localmente
```bash
git clone
https://github.com/luciusfsilva/task-manager-api.git
cd task-manager-api


