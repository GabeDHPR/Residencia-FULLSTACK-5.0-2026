# Verificador de Fraude (Pega Fraude)

Sistema em Java para terminal que recebe mensagens suspeitas e calcula o risco de golpe através de um sistema de pontuação algorítmica.

## 🚀 Como a Análise Funciona

O sistema avalia a mensagem e adiciona **+1 ponto** para cada critério encontrado:
* **Urgência:** Validação de palavras de alerta usando `HashSet`.
* **Links Suspeitos:** Verificação de protocolos HTTP e links encurtados.
* **Dados Pessoais:** Validação de pedidos de informações usando `HashSet`.
* **Erros de Gramática:** Análise de texto utilizando a biblioteca `JLanguageTools`.

## ⚠️ Classificação de Perigo

Com base na soma total, o sistema emite o alerta final:
* **0 pontos:** Mensagem confiável
* **Até 2 pontos:** Mensagem suspeita
* **Até 4 pontos:** Risco considerável de golpe


<img width="822" height="827" alt="golpes drawio (1)" src="https://github.com/user-attachments/assets/bdbdb005-7006-4c44-85c4-55e3a54dd772" />
