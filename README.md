# Sobre o projeto 

# 💈 BarBernet

A Barbernet é uma plataforma de API voltada para barbearias, oferecendo funcionalidades para gerenciar eficientemente o negócio. Com ela, é possível cadastrar cabeleireiros e clientes, além de agendar cortes de cabelo de forma simples. A plataforma permite não apenas o agendamento, mas também a listagem de cabeleireiros e clientes, facilitando o acompanhamento e a organização das dos dados.




## 🚀 Deploy 
A API foi implantada na AWS para facilitar o teste. Você pode acessar a aplicação através deste
[Swagger for application](http://3.21.128.67:8081/swagger-ui/index.html#/).

Aqui estão as credenciais padrão para obter o Token JWT :
```
{
  "login": "test@test.com",
  "senha": "123"
}
```
Depois de fazer a requisição de login, copie o token fornecido e cole-o na janela de diálogo do Swagger. Assim, você terá acesso a todas as requisições da aplicação.

## 🛠️ Compilação
Para gerar os arquivos-fonte, siga o padrão de um projeto Maven: `mvn clean install`.

Aqui estão alguns links úteis para a aplicação local:
- Clientes: http://localhost:8080/clientes
- Cabeleireiros: http://localhost:8080/cabeleireiros
- Agendamentos: http://localhost:8080/agendamentos

## 🧰 Tecnologias utilizadas
Aqui estão algumas das principais tecnologias utilizadas no desenvolvimento do Clinic Med:

| Tecnologia | Versão |
| --- | --- |
| Java | 17 |
| Spring Boot | 3.2.2 |
| Spring Security | 3.1.0 |
| JPA | 3.1.0 |
| Maven | 3.9.6 |
| Lombok | 3.1.0 |
| Auth0 JWT | 4.4.0 |
| MySQL | 8.0 |
| H2 | 2.2.2|


## 🎯 Funcionalidades
O Clinic health care oferece uma variedade de funcionalidades, incluindo:
- Gerenciamento completo de clientes (CRUD);
- Gerenciamento completo de cabeleireiros (CRUD);
- Gerenciamento completo de agendamentos (CRUD).

## 📚 Como rodar o Projeto
Existem duas maneiras principais de começar:
1. [Baixe o ZIP do projeto](https://github.com/Diog007/barbernet-backend/archive/refs/heads/main.zip) e abra-o em uma IDE de sua escolha.
2. Clone o repositório usando o seguinte comando: `https://github.com/Diog007/barbernet-backend.git`.

Depois de obter o código-fonte, você pode instalar as dependências do projeto com `mvn clean install` e iniciar a aplicação com `mvn spring-boot:run`. A aplicação estará disponível na porta 8080. Para alterar a porta, modifique o `server.port` no arquivo `application.properties`.

Developed by [Diogo do Nascimento](https://github.com/Diog007).
