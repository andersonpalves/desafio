# Desenvolvimento e Deploy

Instruções detalhadas para desenvolvimento local, testes e deploy utilizando Docker e Docker Compose.

## Passo 1: Preparação do Ambiente

Clone o repositório do projeto para sua máquina local usando o Git:

```bash
git clone https://github.com/mllcarvalho/DesafioItau.git
cd DesafioItau
```

## Passo 2: Construção dos Containers com Docker Compose

Na raiz do projeto, onde o arquivo docker-compose.yml está localizado, execute o comando abaixo para construir e iniciar todos o container do Wiremock definido no Docker Compose:

```bash
docker-compose up --build -d
```

## GET Mock Client

  http://localhost:9090/clientes/bcdd1048-a501-4608-bc82-66d7b4db3600
  
  http://localhost:9090/clientes/2ceb26e9-7b5c-417e-bf75-ffaa66e3a76f

  + Response 200 (application/json)

    + Body

            {
                "id": "bcdd1048-a501-4608-bc82-66d7b4db3600",
                "nome": "João Silva",
                "telefone": "912348765",
                "tipoPessoa": "Fisica"
            }
  



## GET Mock Contas

  http://localhost:9090/contas/d0d32142-74b7-4aca-9c68-838aeacef96b
  
  http://localhost:9090/contas/41313d7b-bd75-4c75-9dea-1f4be434007f

  + Response 200 (application/json)

    + Body

            {
                "id": "d0d32142-74b7-4aca-9c68-838aeacef96b,
                "saldo": 5000.00
                "ativo": true
                "limiteDiario": 500.00
            }


      

## PUT Mock Contas - Atualiza Saldo

  http://localhost:9090/contas/saldos

  + Request (application/json)

    + Body

            {
              "valor": 1000.00,
              "nomeDestino": "Artur Carneiro",
              "conta": {
                  "idOrigem": "d0d32142-74b7-4aca-9c68-838aeacef96b",
                  "idDestino": "41313d7b-bd75-4c75-9dea-1f4be434007f"
              }
            }

  + Response 204 - No content (application/json)




## POST Mock Bacen

  http://localhost:9090/notificacoes

  + Request (application/json)

    + Body

            {
              "valor": 1000.00,
              "conta": {
                  "idOrigem": "d0d32142-74b7-4aca-9c68-838aeacef96b",
                  "idDestino": "41313d7b-bd75-4c75-9dea-1f4be434007f"
              }
            }

  + Response 204 - No Content (application/json)
      



## POST API Transferência

http://localhost:8080/transferencia

  + Request (application/json)

    + Body

            {
              "idCliente": "2ceb26e9-7b5c-417e-bf75-ffaa66e3a76f",
              "valor": 1000.00,
              "conta": {
                  "idOrigem": "d0d32142-74b7-4aca-9c68-838aeacef96b",
                  "idDestino": "41313d7b-bd75-4c75-9dea-1f4be434007f"
              }
            }

  + Response 200 (application/json)

    + Body

            {
                "id_transferencia": "410bb5b0-429f-46b1-8621-b7da101b1e28"
            }

## Como executar?
  + Requisitos obrigatórios: Docker e Java 17
  + Executar o metodo POST na rota /transferencia com o seguinte payload
    + Body

              {
                "idCliente": "bcdd1048-a501-4608-bc82-66d7b4db3600",
                "valor": 500.00,
                "conta": {
                    "idOrigem": "d0d32142-74b7-4aca-9c68-838aeacef96b",
                    "idDestino": "41313d7b-bd75-4c75-9dea-1f4be434007f"
                }
              }
    + Response

              {
                "idTransferencia": "8aa3fba0-dd9c-4216-b9fb-ff913d4105a6",
              }
  + Regras de negócio:
    + Valida se o cliente que vai receber a transferência existe passando o idCliente na API de Cadastro
    + Buscar dados da conta origem passando idConta na API de Contas; 
    + Validar se a conta corrente está ativa; 
    + Validar se o cliente tem saldo disponível na conta corrente para realizar a transferência; 
    + A API de contas retornará o limite diário do cliente, caso o valor seja zero ou menor do que o valor da transferência a ser realizada, a transferência não poderá ser realizada;
    + Após a transferência é necessário notificar o BACEN de forma síncrona que a transação foi concluída com sucesso. A API do BACEN tem controle de rate limit e pode retornar 429 em caso de chamadas que excedam o limite;
    + Impedir que falhas momentâneas das dependências da aplicação impactem a experiência do cliente