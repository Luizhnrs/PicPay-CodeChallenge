Feature: Transferência de saldo

  Scenario: Transferência para si mesmo
    Given que o usuário A possui saldo de "200" reais
    When o usuário A tenta transferir "100" reais para si mesmo
    Then o sistema deve bloquear a transferência
    And exibir uma mensagem de erro
    And o saldo permanece "200" reais

  Scenario: Transferência com valor zero
    Given que o usuário A possui saldo de "200" reais
    And que o usuário B está cadastrado no sistema
    When o usuário A tenta transferir "0" reais para o usuário B
    Then o sistema deve bloquear a transferência
    And exibir mensagem de valor inválido

  Scenario: Transferência com valor negativo
    Given que o usuário A possui saldo de "200" reais
    And que o usuário B está cadastrado no sistema
    When o usuário A tenta transferir "-50" reais
    Then o sistema deve bloquear a operação
    And exibir mensagem de erro

  Scenario: Transferência com valor muito alto
    Given que o usuário A possui saldo de "1000000" reais
    And que o usuário B está cadastrado no sistema
    When o usuário A tenta transferir "999999" reais para o usuário B
    Then a transferência deve ser processada com sucesso

  Scenario: Transferência para usuário inexistente
    Given que o usuário A possui saldo de "200" reais
    When o usuário A tenta transferir "100" reais para um usuário inexistente
    Then o sistema deve bloquear a operação
    And exibir mensagem de usuário não encontrado

  Scenario: Transferência com falha no serviço externo
    Given que o usuário A possui saldo de "200" reais
    And que o serviço de autorização está indisponível
    When o usuário A tenta transferir "100" reais
    Then a transferência deve ser cancelada
    And o saldo permanece inalterado

  Scenario: Duas transferências simultâneas
    Given que o usuário A possui saldo de "100" reais
    And que o usuário B está cadastrado no sistema
    When o usuário A realiza duas transferências de "100" reais ao mesmo tempo
    Then apenas uma transferência deve ser concluída
    And o saldo não deve ficar negativo

  Scenario: Clique duplo no botão de transferir
    Given que o usuário A possui saldo de "200" reais
    And que o usuário B está cadastrado no sistema
    When o usuário A clica duas vezes rapidamente no botão de transferir
    Then apenas uma transferência deve ser processada

  Scenario: Transferência com atraso na resposta
    Given que o usuário A possui saldo de "200" reais
    And que o serviço externo demora a responder
    When o usuário A realiza uma transferência
    Then o sistema deve aguardar a resposta corretamente
    And não deve duplicar a transação