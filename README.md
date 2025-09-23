# 💰 Bank API

API REST em **Spring Boot 3 (Java 17)** para gestão de contas bancárias.  
Inclui **CRUD**, **validações**, **paginação/ordenação** e operações: **depósito, saque e pix**.

---

## 🚀 Tecnologias
- Java 17, Spring Boot 3  
- Spring Web, Spring Data JPA, Bean Validation  
- H2 (memória), Lombok, Maven  

---

## ⚙️ Como executar
```bash
./mvnw spring-boot:run
```
- API: http://localhost:8080
- Console H2: http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:mem:bankdb
  - User: sa
  - Password: (vazio)

# 📚 Endpoints principais
## Criar conta
```bash
POST /contas
```

## Listar contas (pagina/ordena)
```bash
GET /contas?page=0&size=10&sort=nomeTitular,asc
```

## Buscar por ID
```bash
GET /contas/{id}
```

## Atualizar conta
```bash
PUT /contas/{id}
```

## Encerrar conta
```bash
POST /contas/{id}/encerrar
```

## Depósito
```bash
POST /contas/deposito
```

## Saque
```bash
POST /contas/saque
```

## Pix
```bash
POST /contas/pix
```

# 👨‍💻 Autores
- Maria Eduarda Fernandes Rocha (RM:560657)
- Victor de Carvalho Alves (RM:560395)
