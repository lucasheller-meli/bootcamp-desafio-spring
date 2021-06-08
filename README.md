# Bootcamp: Desafio Spring
______

## Dependências

Maven 3.8.1

Java 11.0

H2 Database

## Teste

Os usuários e vendores já estão cadastrados e podem ser visualizados no arquivo PopulateDataBase.

Os arquivos para a realização dos testes utilizando o Postman estão contidos no projeto e serão apresentados na seção **Arquivos para teste**.


##Arquivos para teste:
Os arquivos necessarios para a realização dos testes da API, utilizando o Postman, 
encontram-se em formato JSON contindos no modulo do projeto. 

O arquivo tambem pode ser visualizado pelo link 
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/82f5bd5523ea4f8bbfaf)

O arquivo está nomeado como DesafioSpringboot.postman_collection.json, sendo divido nos seguintes endpoints:

- US0001: responsavel por realizar o follow entre o usuario e o vendedor, para isso deve informar o ID do usuario (idUser) e o ID do vendedor (idSeller).
  A URL segue o seguinte padrão: /users/{idUser}/follow/{idSeller}.
  
  
- US0002: solicita o numero de seguidores que um determinado vendedor tem, para isso se deve informar o ID do vendedor. A URL segue o seguinte padrao: 
  /users/{idSeller}/followers/count. 
  

- US0003: solicita a lista de usuarios que seguem um determinado vendedor, para isso se deve informar o ID do vendedor. A URL segue o seguinte padrão: 
  /users/{idSeller}/followers/list?order=name_desc. Quando o order for igual a "name_asc" sera informado a lista em ordem alfabetica ascedente e 
  descendente quando for informado "name_desc", caso não segue informado a order, a listagem será ordenado numericamente pelo ID dos usuarios.
  

- US0004: US0003: solicita a lista de vendedores que um determinado usuario segue, para isso se deve informar o ID do usuario. A URL segue o seguinte padrão
  : /users/1/followed/list?order=name_asc. Quando o order for igual a "name_asc" sera informado a lista em ordem alfabetica ascedente e
  descendente quando for informado "name_desc", caso não segue informado a order, a listagem será ordenado numericamente pelo ID dos vendedores.


- US0005: realiza o cadastro de uma nova publicação. Para isso, é informado no payload as informações da publicação, bem como o ID do vendedor que deseja realizar 
a publicação. A URL segue o seguinte padrão: /products/newpost.


- US0006: solitica a lista de publicações que dos vendedores que um determinado usuario segue, para isso se deve informar o ID do usuario. A URL segue 
  o seguinte padrão:/products/followed/{idUser}/list?order=date_desc. Quando o order for igual a "date_asc" sera informado a lista em ordem cronologica ascedente e
  descendente quando for informado "date_desc", caso não segue informado a order, a listagem será ordenado numericamente pelo ID das publicaçoes.


- US0007: realizar o unfollow entre o usuario e o vendedor, para isso deve informar o ID do usuario (idUser) e o ID do vendedor (idSeller).
  A URL segue o seguinte padrão: /users/{idUser}/unfollow/{idSeller}.


- US0010: realizar o cadastro de uma publicação promocional. Para isso, é informado no payload as informações da publicação, bem como o ID do vendedor que deseja realizar
  a publicação. A URL segue o seguinte padrão: /products/newpromopost. 


- US0011: solicita a quantidade de publicações promocionais realizadas por um determinado vendedor, para isso se deve informar o ID do vendedor.
A URL segue o seguinte padrão: /products/{idSeller}/countPromo.


- US0012: solicita a lista de publicações promocionais realizadas por um determinado vendedor, para isso se deve informar o ID do vendedor.
  A URL segue o seguinte padrão: /products/{idSeller}/list.

