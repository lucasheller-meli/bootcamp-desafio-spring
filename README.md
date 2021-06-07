# Bootcamp: Desafio Spring
______

## Dependências

Maven 3.8.1

Java 11.0

H2 Database

##Teste

Os usuários e vendores já estão pré-cadastrados e podem ser visualizados no arquivo PopulateDataBase.

Os arquivos para a realização dos testes utilizando o Postman estão contidos no projeto e serão apresentados na seção **Arquivos para teste**.

Para a realização do teste da API, deve-se garantir que o endpoint follow seja realizado para que se possa 
executar os demais endpoints de User e Seller.
Em seguida, realizar o cadastro dos posts para que se possa executar os demais endpoints.


##Arquivos para teste:
Os arquivos necessarios para a realização dos testes da API, utilizando o Postman, 
encontram-se em formato JSON contindos no modulo do projeto.
São eles:
- Product:
  
Contêm os endpoints para realizar o cadastro dos Posts dos vendodores (ex. product1) 
e solicitar os post em ordem cronologica ascedente (productList/1/date_asc) e 
descendente (productList/1/date_asc) dos vendedores que um determinado usuário segue.

- Seller: 
  
Contêm os endpoints para solicitar o número de seguidores que um determinado vendedor tem (followrs/1/count)
e as listas de seguidores que um determinado vendedor tem em ordem ascedente (followers/1/name_asc) e descendente (followers/1/name_desc).

- User: 

Contêm os endpoints para realizar follow, no qual um usuario segue um vendedor (follow 1/2), o unfollow (unfollow1/3) 
e solicita a lista de seguindo (vendedores que o usuario segue) em ordem alfabetica ascedente (followin/1/name_asc) e descentente (followin/1/name_asc). 

- PromoProduct:

Contêm os endpoints para realizar os cadastros do produtos promocional (promoProduct1), o solicitação de quantos Post promocionais um determinado vendedor 
fez (promoProductList/1/count) e a lista desses posts (promoProductList/1).

**Obs. os números contidos nos endpoints são equivalentes aos IDs de usuario e vendedor, dependendo do objetivo de cada um.**


