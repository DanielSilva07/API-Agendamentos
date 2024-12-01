
# API-Agendamentos

Projeto pessoal para meu primo que tem uma loja para reparos de smartphones , peguei esse problema do mundo real para aprimorar meus conhecimentos no desenvolvimento de software. 
Desenvolvi uma API para fazer agendamentos para serviços de reparo no celular. Usando as boas práticas de desenvolvimento, fazendo integração e entrega contínua com o GitHub Actions para fazer o deploy da aplicação na AWS .

# Tecnologias utilizadas: 
Java , Spring Boot , MongoDB , Github Actions ,  AWS   .

# USO
 O usuário vai inserir o nome , dia e a descrição do serviço para fazer um agendamento.

# API Endpoints

{

        "nome": "Daniel",
        "descricao": "Trocar conector",
        "data": "03/12/2024"
}

Body Return:

{

    "id": "674ca0bfde2b95026bccc5d3",
    "nome": "Daniel",
    "descricao": "Trocar conector",
    "data": "03/12/2024",
    "email": null,
    "localDateTime": "2024-12-01T17:45:35.173440129"
}


# Pipeline CI-CD




