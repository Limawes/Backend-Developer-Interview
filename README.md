# Backend-Developer-Interview

#### Este projeto tem a finalidade de fornecer uma ferramenta de gestão de revenda de veículos.

      As tecnologias utilizadas são:
        * Java 11
        * Spring Boot 2.6.4
        * Maven
        * Docker
        * PostgreSQL
        * JWT
        * Swagger
        * Spring-Security

* Para iniciar a aplicação rode os seguintes comandos no terminal na raiz do projeto:

    >- **docker-compose up** (Cria os contêineres e os inicia)

    >- **mvn compile** (Faz o 'build' da aplicação e gera o bytecode)
    
    >- **mvn spring-boot:run** (Inicia a aplicação)
    

### Regras não incluidas no projeto
    "O sistema deve ter a inteligência de distribuir as oportunidades sem responsável para 
      os assistentes da loja em forma de fila. Onde o próximo a receber seja o que possui a
      menor quantidade de oportunidades em andamento e maior tempo sem receber
      uma oportunidade."

- essa regra foi parcialmente inclusa, o ponto que não está no projeto é onde o responsável com mais tempo sem 
receber uma oportunidade, recebe a oportunidade de maneira automática.-
  * motivo: Necessitaria de uma lógica onde há um cálculo de período, e eu levaria um tempo maior pra
  pra criar essa regra, então é basicamente por falta de tempo.
  

    "Apenas administradores podem cadastrar novos usuários, salvos proprietários e 
      gerentes que podem cadastrar usuários em sua loja."

- regra parcialmente inclusa, o que não está incluso é a verificação de proprietários e gerentes poderem cadastras
usuarios somente em suas lojas.
  * motivo: Eu realmente não consegui validar isso, demandaria mais tempo pra eu me dedicar a essa regra.


### Observações!!!

  - Existe um script que vai rodar assim que a aplicação for levantada
  - Nele contém a criacao de um usuário padrão:
    >username: admin
    > 
    >password: 123456
  - Contém também a inserção das permissões, de alguns clientes e de alguns veículos.

  #### Login e autenticação:
    Realizando a requisição de login, vai ser retornado um token, onde será utilizado
    para as demais requisições.
    Pode ser feito via postman(recomendação)/insomnia ou pelo Swagger 
    Caso queira utilizar, vou deixar disponível um arquivo do postman(envio no e-mail com o link do repositório).

### Swagger
* http://localhost:8080/swagger-ui.html

### Referências:
* https://javatechonline.com/spring-boot-mvc-crud-example/
* https://www.dio.me/articles/implementacao-basica-do-swagger-em-api-spring-boot
* https://stackoverflow.com/questions/40241843/failed-to-start-bean-documentationpluginsbootstrapper-in-spring-data-rest
* https://medium.com/@AlexanderObregon/securing-spring-boot-applications-with-spring-security-1f6da7fde0f0
* https://www.baeldung.com/spring-boot-security-autoconfiguration
* https://medium.com/@minadev/authentication-and-authorization-with-spring-security-bf22e985f2cb
