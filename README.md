# pedidos
Repositório de um teste para uma empresa contendo uma API que processa pedidos de compra de um cliente
--------------

Vaga Programador Java

Criar uma solução java em formato de API que atenda aos seguintes requisitos para a recepção de pedidos dos clientes:

Criar um serviço que receba pedidos no formato xml e json com 6 campos:  
número controle - número aleatório informado pelo cliente.  
data cadastro (opcional)  
nome - nome do produto  
valor - valor monetário unitário produto  
quantidade (opcional) - quantidade de produtos.  
codigo cliente - identificação numérica do cliente.

Critérios aceitação e manipulação do arquivo:

O arquivo pode conter 1 ou mais pedidos, limitado a 10.  
Não poderá aceitar um número de controle já cadastrado.  
Caso a data de cadastro não seja enviada o sistema deve assumir a data atual.  
Caso a quantidade não seja enviada considerar 1.  (regras definidas no [service](https://github.com/ederp/pedidos/blob/main/spring-jpa-mysql/src/main/java/com/eder/springjpamysql/service/PedidoService.java))  
Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, para quantidades a partir de 10 aplicar 10% de desconto no valor total.  (desconto aplicado na entidade [Pedido](https://github.com/ederp/pedidos/blob/main/spring-jpa-mysql/src/main/java/com/eder/springjpamysql/model/Pedido.java))
O sistema deve calcular e gravar o valor total do pedido.  
Assumir que já existe 10 clientes cadastrados, com códigos de 1 a 10.


Criar um serviço onde possa consultar os pedidos enviados pelos clientes. (a requisição é feita pelo [Controller](https://github.com/ederp/pedidos/tree/main/spring-jpa-mysql/src/main/java/com/eder/springjpamysql/controller))
Critérios aceitação:
O retorno deve trazer todos os dados do pedido.

filtros da consulta:
número pedido, data cadastro, todos


Frameworks:
Fica a critério do candidato utilizar ou não, sem restrições de escolha.

desejável:
Injeção de dependência  
Padrões de projeto  
Testes unitários (presentes nesta [pasta](https://github.com/ederp/pedidos/tree/main/spring-jpa-mysql/src/test/java/com/eder/springjpamysql))

Obrigatório
banco de dados mysql  
utilizar framework ORM  
utilizar a linguagem java 1.8

Obs.: Foi gerado uma configuração para a documentação da API no Swagger, acessível através do link [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html#/) assim que a aplicação é executada no servidor.

