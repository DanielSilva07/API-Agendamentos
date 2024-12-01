
# API-Agendamentos

Projeto pessoal para meu primo que tem uma loja para reparos de smartphones , peguei esse problema do mundo real para aprimorar meus conhecimentos no desenvolvimento de software. 
Desenvolvi uma API para fazer agendamentos para serviços de reparo no celular. Usando as boas práticas de desenvolvimento, fazendo integração e entrega contínua com o GitHub Actions para fazer o deploy da aplicação na AWS .

# Tecnologias utilizadas: 
Java , Spring Boot , MongoDB , Github Actions ,  AWS   .

# USO
 O usuário vai inserir o nome , dia e a descrição do serviço para fazer um agendamento.

# API Endpoints

POST /http://44.220.157.143:8090/save  

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


GET /http://44.220.157.143:8090/getAll 

Body Return:

]   
    {
    
        "id": "674ca0bfde2b95026bccc5d3",
        "nome": "Daniel",
        "descricao": "Trocar conector",
        "data": "03/12/2024",
        "email": null,
        "localDateTime": "2024-12-01T17:45:35.173"
    },
    
    {
        "id": "674ca247de2b95026bccc5d4",
        "nome": "MELQUISEDEQUE",
        "descricao": "Trocar tela",
        "data": "05/12/2024",
        "email": null,
        "localDateTime": "2024-12-01T17:52:07.386"
    },
    
    {
        "id": "674ca25dde2b95026bccc5d5",
        "nome": "JEROBOÃO",
        "descricao": "Trocar tela",
        "data": "05/12/2024",
        "email": null,
        "localDateTime": "2024-12-01T17:52:29.283"
    }
]


# Pipeline CI-CD

    steps:
    - name: Checkout code
      uses: actions/checkout@v3

    - name: Setup Java
      uses: actions/setup-java@v3
      with:
        distribution: 'temurin'
        java-version: '17'
    - name: Build project
      run: mvn clean install -DsKipTests
    - name: Login Docker Hub
      run: docker login -u ${{secrets.DOCKER_USERNAME}} -p ${{secrets.DOCKER_PASSWORD}}
    - name: Build docker image
      run: docker build -t daniel00dev/spring-boot-api-agendamentos .
    - name: push image docker
      run: docker push daniel00dev/spring-boot-api-agendamentos

      
![Screenshot from 2024-12-01 15-21-49](https://github.com/user-attachments/assets/b769c194-2886-4efb-94b1-241555df4fc2)



