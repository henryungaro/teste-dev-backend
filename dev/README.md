# ReadMe

Esta API é utilizada para gerenciamento de cadastro de clientes com problemas de saude.
Utilizando MySQL. As tabelas com as ligações sao criadas automaticamente.

# Metodos:
 - Criação
 - Busca ( Listagem, Por nome e por ID)
 - Alteração
 - Remoção

# POST Client - Create
```
localhost:8081/client/create
```
Cria um cliente passando: nome, sexo e data de nascimento. o sistema ira preencher a data de criação automaticamente. Nota que ele não adiciona os problemas de saúde

Bodyraw (json)

```
json
{
  "name": "Milena Rodrigues",
  "birthDate": "05/02/1993",
  "sex": "Feminino",
  "health": []
}

```
Resposta:

```
json
{
  "id": 15,
  "name": "Fabricio Monteiro",
  "birthDate": "15/08/1980",
  "sex": "Masculino",
  "registerDate": "27/09/2021",
  "updateDate": null,
  "health": []
}

```
# PUT Client -Update
```
localhost:8081/client/update/id
```

Modifica um cliente previamente cadastrado, precisando de seu id na chamada, ele adiciona data da modificação automaticamente

```
client/update/1
```
Bodyraw (json)

```
json
{
  "name": "Fabio Vasconcello",
  "birthDate": "15/10/1985",
  "sex": "Masculino",
  "health": []
}

```
Resposta:
```
{
    "id": 1,
    "name": "Fabio Vasconcello",
    "birthDate": "15/10/1985",
    "sex": "Masculino",
    "registerDate": "15/05/2021",
    "updateDate": "27/09/2021",
    "health": []
}
```
# GET Client - ListAll
```
localhost:8081/client/list
```
Retorna uma lista de todos os clientes cadastrados junto com seus sintomas

Resposta:
```
[
  {
    "id": 1,
    "name": "Gabriella Fagundes",
    "birthDate": "15/07/1980",
    "sex": "Feminino",
    "registerDate": "15/05/2021",
    "updateDate": null,
    "health": [
      {
        "id": 1,
        "description": "Diabetes",
        "degree": 1
      }
    ]
  },
  {
    "id": 2,
    "name": "Marcos Pirelly",
    "birthDate": "20/05/1992",
    "sex": "Masculino",
    "registerDate": "20/06/2021",
    "updateDate": null,
    "health": [
      {
        "id": 2,
        "description": "Enchaqueca",
        "degree": 1
      }
    ]
  },
  {
    "id": 3,
    "name": "Roberto Dinis",
    "birthDate": "23/02/1960",
    "sex": "Masculino",
    "registerDate": "17/03/2021",
    "updateDate": null,
    "health": [
      {
        "id": 3,
        "description": "Parkinson",
        "degree": 2
      },
      {
        "id": 4,
        "description": "Diabete",
        "degree": 2
      },
      {
        "id": 5,
        "description": "Teste",
        "degree": 2
      }
    ]
  }
]

```

# GET Client - Critical List
```
localhost:8081/client/criticalList
```
Mostra os 10 usuarios com maior risco de saúde, Baseado na furmula:
score = (1 / (1 + eˆ-(-2.8 + sd ))) * 100

Exemplo:
```
localhost:8081/client/criticalList
```
Resposta:
```
json
[
  {
    "id": 3,
    "name": "Roberto Dinis",
    "score": 6684.907055817889
  },
  {
    "id": 13,
    "name": "Ricardo Batista",
    "score": 130.0374548893128
  },
  {
    "id": 1,
    "name": "Fabio Vasconcello",
    "score": 9.40554254967863
  },
  {
    "id": 2,
    "name": "Marcos Pirelly",
    "score": 9.40554254967863
  },
  {
    "id": 14,
    "name": "Milena Rodrigues",
    "score": 9.40554254967863
  },
  {
    "id": 15,
    "name": "Fabricio Monteiro",
    "score": 9.40554254967863
  }
]
```

# GET Client - FindById

```
localhost:8081/client/find/id
```
Retorna um cadastro especifico baseado pelo ID fornecido no inicio

Exemplo
```
/client/find/13
```
Resposta:
```
{
  "id": 13,
  "name": "Ricardo Batista",
  "birthDate": "10/08/1960",
  "sex": "Masculino",
  "registerDate": "27/09/2021",
  "updateDate": null,
  "health": [
    {
      "id": 6,
      "description": "teste update",
      "degree": 1
    },
    {
      "id": 7,
      "description": "teste",
      "degree": 1
    },
    {
      "id": 8,
      "description": "teste",
      "degree": 1
    }
  ]
}
```
# GET Client - FindByName
```
localhost:8081/client/findName/nome
```
Procura por nome, podendo conter apenas um pedaço do nome ou ele completo. sendo acessado por exemplo client/findName/nome-a-ser-pesquisado

Exemplo
```
/client/findName/Marcos
```
Resposta:
```
{
  "id": 2,
  "name": "Marcos Pirelly",
  "birthDate": "20/05/1992",
  "sex": "Masculino",
  "registerDate": "20/06/2021",
  "updateDate": null,
  "health": [
    {
      "id": 2,
      "description": "Enchaqueca",
      "degree": 1
    }
  ]
}
```

# DEL Client - DeleteById
```
localhost:8081/client/remove/id
```
Deleta um cliente utilizando um ID fornecido depois da /

Exemplo
```
localhost:8081/client/remove/6
```

Resposta:
```
1. 
```
Se não houve problemas ele nao retorna nenhuma mensagem.

# POST Health Issues - Create
```
localhost:8081/issues/create
```
Cria os sintomas em um cliente previamente cadastrado, não criando múltiplos sintomas

Bodyraw (json)
```
{
  "description": "teste",
  "degree": 1,
  "client": {
    "id": 15
  }
}
```

Responsta
```
{
  "id": 10,
  "description": "teste",
  "degree": 2
}
```

# PUT Health Issue - Update
```
localhost:8081/issues/update/id
```
Exemplo:
```
localhost:8081/issues/update/13
```
Atualiza um problema de saúde a partir de seu id, é necessário fornecer o id de associação do cliente

Bodyraw (json)
```
{
  "description": "teste update",
  "degree": 1,
  "client": {
    "id": 13
  }
}
```

# Health Issue - Update
```
localhost:8081/issues/update/id
```
Exemplo:
```
/issues/update/6
```
Bodyraw Json
```
{
    "description": "teste update",
    "degree": 1,
    "client":{
    "id": 13
    }
}
```
Resposta:

```
{
  "id": 6,
  "description": "teste update",
  "degree": 1
}
```

# DEL Health Issue - Delete
```
localhost:8081/issues/remove/id
```
Remove um registro de problema de saúde, utilizando o id deste problema previamente cadastrado

Exemplo:
```
/issues/remove/5
```
Resposta
```
1.
```
Caso não ocorra falhas durante esta solicitação, não sera mostrada mensagem
