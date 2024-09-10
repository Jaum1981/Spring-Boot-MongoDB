# Projeto SpringBoot com MongoDB

# Sobre o projeto:
É um projeto simples do curso de Java do Nélio Alves, onde estudo sobre as tecnologias SpringBoot e o banco de dados orientado à agregados MongDB.

## Objetivos

- Compreender as principais diferenças entre paradigma orientado a documentos e relacional
- Implementar operações de CRUD
- Refletir sobre decisões de design para um banco de dados orientado a documentos
- Implementar associações entre objetos
  - Objetos aninhados
  - Referências
- Realizar consultas com Spring Data e MongoRepository

### Diagrama de classe:

![Alt text](/documentacaoProjeto/DiagramaDeClasse.png?raw=true "Diagrama de Classes")

![Alt text](/documentacaoProjeto/CamadaLogica.png?raw=true "Camada Logica")

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- MongoDB
- Maven

# Como executar o projeto

## Back end

**Pré-requisitos:**
- Java 21
- Maven 

```bash
# Clonar repositório
git clone https://github.com/Jaum1981/Spring-Boot-MongoDB

# Navegar até o diretório do projeto
cd Spring-Boot-MongoDB

# Construir o projeto com Maven
mvn clean install

#Acessar o seu próprio banco de dados
#Linux:
export MONGO_URI="mongodb+srv://<usuário>:<senha>@<seu_cluster>/<nome_do_container>?retryWrites=true&w=majority"

#Windows:
set MONGO_URI=mongodb+srv://<usuário>:<senha>@<seu_cluster>/<nome_do_container>?retryWrites=true&w=majority


# Executar o projeto
mvn spring-boot:run
```

# Acessar a aplicação
Através do Postman acesse http://localhost:8080 para interagir com a aplicação.

# Requisições URL's:

- GET:
```bash
#Listar usuários
http://localhost:8080/users

#Listar usuário por {id}
http://localhost:8080/users/{id}

#Listar todos os post do usuário por {id}
http://localhost:8080/users/{id}/posts

#Procurar post através de uma string do campo título
http://localhost:8080/posts/titlesearch?text={string}

#Procurar post através de uma string de qualquer campo
http://localhost:8080/posts/fullsearch?text={string}&maxDate={yyyy-MM-dd}&minDate={yyyy-MM-dd}

```
- POST:
```bash
#Adicionar novo user
http://localhost:8080/users

Body = {
    "name": "{name}",
    "email": "{email}"
}
```

- PUT
```bash
#Atualizar dados de um usuário já existente
http://localhost:8080/users/{id}

Body = {
    "name": "{name}",
    "email": "{email}"
}
```

- DELETE
```bash
#Deletar um usuário 
http://localhost:8080/users/{id}

```
