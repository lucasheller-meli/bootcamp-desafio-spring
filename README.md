# Bootcamp: Desafio Spring
Abra a pasta challenge na sua IDE favorita para ver o código da aplicação e executa-la.

## Dependências
- Java 11
- Gradle 7
- Lombok

## Testes
Ainda não temos testes automatizados, mas disponibilizamos uma coleção do postman para facilitar testes manuais:
- https://www.getpostman.com/collections/a941de00145cdaee1c27

Também é possível acessar a documentação do Swagger no seguinte link quando a aplicação estiver em execução:
- http://localhost:8080/swagger-ui/

Como usamos o banco de dados h2, existe um script que roda quando a aplicação é iniciada, para criar alguns dados para teste.

## Resumo dos endpoints
- POST http://localhost:8080/users/8/follow/1
- GET http://localhost:8080/users/1/followers/count
- GET http://localhost:8080/users/1/followers/list
- GET http://localhost:8080/users/1/followed/list
- POST http://localhost:8080/users/7/unfollow/1
- GET http://localhost:8080/users/sellers
- POST http://localhost:8080/users/sellers
- POST http://localhost:8080/users
- GET http://localhost:8080/users
- GET http://localhost:8080/products/posts
- POST http://localhost:8080/products/new/post
- GET http://localhost:8080/products/followed/2/list
- GET http://localhost:8080/products/promotional/posts
- POST http://localhost:8080/products/new/promotional/post
- GET http://localhost:8080/products/1/promotional/count
- GET http://localhost:8080/products/1/promotional/list
- GET http://localhost:8080/products
