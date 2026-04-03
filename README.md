🚀 ***API Automation Project - Restful Booker***

Projeto de automação de testes de API desenvolvido em Java, utilizando a biblioteca Rest-Assured para validar o fluxo completo de reservas (bookings) da API Restful-Booker.

📌***Diferenciais do Projeto***

Testes de Contrato: Validação da estrutura JSON (chaves e tipos) para garantir a integridade da interface.

Testes Funcionais: Validação das regras de negócio e persistência de dados.

Encadeamento de Testes (Chaining): Extração dinâmica de token e bookingid para uso em requisições sequenciais.

Massa de Dados (DDT): Leitura de payloads através de arquivos externos .json, mantendo o código limpo e desacoplado.

Ordem de Execução: Utilização do JUnit 5 @Order para garantir o fluxo lógico (Auth -> Post -> Get).

🛠️ ***Tecnologias Utilizadas***

### 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Rest-Assured](https://img.shields.io/badge/Rest--Assured-green?style=for-the-badge)
![JUnit 5](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JSON](https://img.shields.io/badge/json-5E5E5E?style=for-the-badge&logo=json&logoColor=white)

📂 ***Estrutura de Pastas***

    Plaintext
    src
    └── test
      ├── java
      │    └── TestBooking.java (Scripts de Teste)
      └── resources
           └── json
                ├── token.json (Payload de Auth)
                └── bookingPost.json (Payload de Criação)
                
