# Bootcamp desafio spring

### Dependências

Maven 3.8.1 </br>
Java 11 </br>
H2 Database </br>

### Requisistos mínimos
- Java 11
- Maven

### Funcionalidades 


1. **US0001** - Ser capaz de realizar a ação de “Follow” (seguir) a um determinado vendedor.
  - POST http://localhost:8080/users/{userId}/follow/{sellerId}
  
2. **US0002** - Obter o resultado do número de usuários que seguem um determinado
vendedor.
  - GET http://localhost:8080/users/{selleId}/followers/count/
  
3. **US0003** - Obter uma lista de todos os usuários que seguem um determinado vendedor
(quem me segue?)
  - GET http://localhost:8080/users/{sellerId}/followers/list
  
4. **US0004** - Obter uma lista de todos os vendedores que um determinado usuário segue
(quem estou seguindo?)
  - GET http://localhost:8080/users/{userId}/followed/list
  
5. **US0005** - Cadastrar uma nova publicação
  - POST http://localhost:8080/products/newpost

6. **US0006** - Obter uma lista das publicações feitas pelos vendedores que um usuário segue
nas últimas duas semanas (para isso, ter em conta ordenação por data, a maioria das
publicações recentes primeiro).
  - GET http://localhost:8080/products/followed/{userId}/list

7. **US0007** - Ser capaz de realizar a ação de “Deixar de seguir” (parar de seguir) um
determinado vendedor.
  - POST http://localhost:8080/{userId}/unfollow/{sellerId}

8. **US0008** - Ordem alfabética crescente e decrescente (usuário , vendedor)
  - GET http://localhost:8080/users/{userId}/followed/list?order=name_asc
  - GET http://localhost:8080/users/{userId}/followed/list?order=name_desc
  - GET http://localhost:8080/users/{sellerId}/followers/list?order=name_asc
  - GET http://localhost:8080/users/{sellerId}/followers/list?order=name_desc

9. **US0009** - Classificar por data crescente e decrescente
  - GET http://localhost:8080/products/followed/{userId}/list
  
<br />

### **Estrutura de dados** 
> Exemplos de retorno de dados

- **US0002**
> Quantidade de usuários que segue um Vendedor.(EXEMPLO)

```json
{
    "id": 3,
    "name": "José",
    "countFollow": 4
}
```

- **US0003**
> Lista de Usuários que seguem um Vendedor.(EXEMPLO)

```json
{
    "id": 2,
    "name": "Pedro",
    "list": {
        "5": "Gabriel",
        "4": "Lucas",
        "3": "Thamirez"
    }
}
```

- **US0004**
> Lista de quais Vendedores um Usuário segue.(EXEMPLO)

```json
{
    "id": 3,
    "name": "Thamirez",
    "list": {
        "3": "José",
        "2": "Pedro"
    }
}
```

- **US0006**
> Lista de postagem das ultimas duas semanas dos Vendedores que um Usuário segue.(EXEMPLO)

```json
{
    "userId": 3,
    "userName": "Thamirez",
    "list": [
        {
            "idPost": 2,
            "sellerId": 3,
            "date": "2021-06-03",
            "productName": "Caideira Gamer",
            "type": "Gamer",
            "brand": "Razer",
            "color": "Red",
            "notes": "Sin Bateria",
            "category": 120,
            "price": 2800.0
        }
    ]
}
```
