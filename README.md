# 🗓️ Agendamento Consultas

Esta aplicação foi desenvolvida para gerenciar o agendamento de consultas médicas de forma eficiente e automatizada. Ela permite que médicos e pacientes organizem suas consultas de acordo com suas necessidades e disponibilidades.

## ⚙️ Funcionalidades

- Gerenciamento de Dados dos Médicos
- Gerenciamento de Dados dos Pacientes
- Marcação de Consulta
- Relatórios de Consultas Agendadas

## 🛠️ Tecnologias Utilizadas

- ![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=java&logoColor=white) **Java 21**
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?style=flat&logo=spring-boot&logoColor=white) **Spring Boot 3.2.5**
- ![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0-6BA539?style=flat&logo=openapi-initiative&logoColor=white) **OpenApi 3.0**
- ![JUnit](https://img.shields.io/badge/JUnit-Jupiter-25A162?style=flat&logo=junit5&logoColor=white) **JUnit Jupiter**
- ![JaCoCo](https://img.shields.io/badge/JaCoCo-0.8.12-B07C30?style=flat&logo=java&logoColor=white) **JaCoCo 0.8.12**
- ![Maven](https://img.shields.io/badge/Maven-3.8.4-C71A36?style=flat&logo=apache-maven&logoColor=white) **Maven**
- ![Lombok](https://img.shields.io/badge/Lombok-1.18.28-AD1C16?style=flat&logo=lombok&logoColor=white) **Lombok 1.18.28**


## 📋 Instruções

Para subir o projeto, siga os seguintes passos:

1. **Configure o Banco de Dados**: Certifique-se de que o banco de dados esteja disponível online e obtenha a URL de conexão, usuário e senha.
2. **Configure as variáveis de ambiente**: Preencha o arquivo `agendamentoconsultas.env` com as informações do banco de dados conforme o exemplo fornecido. Um exemplo do arquivo se encontra abaixo.
3. **Compile o Projeto**: Utilize o Java 21 e o Maven para compilar o projeto.
4. **Execute o Projeto**: O projeto possui apenas um perfil default para execução. A diferenciação de ambientes se dá mediante variáveis de ambiente.

### 📄 Exemplo de Arquivo de Variáveis de Ambiente

agendamentoconsultas.env:

```env
SERVER_PORT=<porta da aplicação>
DATABASE_URL=<url de conexão com o banco>
DATABASE_USER=<usuario do banco>
DATABASE_PASSWORD=<senha do banco>
HIBERNATE_DDL_AUTO=<ddl do hibernate>
```

### 🧪 Testes com o Postman

Você pode importar a coleção do Postman para testar a API. [Link para a collection do Postman]

## 📜 Licença

Este projeto está licenciado sob a licença GNU General Public License v3.0 (GPLv3). Você é livre para compartilhar e adaptar o material desde que dê o devido crédito ao autor. Para mais detalhes, consulte o arquivo [LICENSE](LICENSE).

## ✒️ Autoria

Matheus Paulino Ribeiro - <mathpaulinor@gmail.com>

Se você tiver dúvidas ou sugestões, não hesite em entrar em contato!
