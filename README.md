# Task Tracker API

Projeto feito com o objetivo de praticar Java, Spring Boot e boas práticas de desenvolvimento de APIs REST.

## Descrição

O Task Tracker é uma **API REST** que permite gerenciar tarefas.  
Você pode criar, listar, atualizar, buscar e deletar tarefas utilizando endpoints REST.  
As tarefas possuem uma descrição e um status, que podem ser atualizados conforme necessário.

## Tecnologias utilizadas

- **Java** – Linguagem principal
- **Spring Boot** – Framework para construção da API
- **Maven** – Gerenciador de dependências
- **REST API** – Interface de comunicação baseada em HTTP
- **Postman** – Testes dos endpoints

## Como rodar o projeto

### 1. Baixe ou clone o repositório

```bash
git clone https://github.com/JessicaLorenzon/task-tracker-spring.git
cd task-tracker-spring
```

### 2. Navegue até o diretório onde está o `.jar`:

```bash
cd target
```

### 3. Execute o aplicativo:

```bash
java -jar task-tracker-java-spring-0.0.1.jar
```

## Endpoints disponíveis

### 1. Listar todas as tarefas

```http
GET /tasks
```

### 2. Buscar tarefa por ID

```http
GET /tasks/{taskId}
```

### 3. Buscar tarefas por status

```http
GET /tasks/status/{status}
```

### 4. Criar nova tarefa

```http
POST /tasks
```

#### Payload (JSON):

```json
{
  "description": "Estudar Spring Boot"
}
```

### 5. Atualizar tarefa

```http
PUT /tasks/{taskId}
```

#### Payload (JSON):

```json
{
  "description": "Estudar Spring Boot e JPA",
  "status": "PROGRESS"
}
```

### 6. Deletar tarefa

```http
DELETE /tasks/{taskId}
```