# Desafio LabPeti9

## Visão Geral

O **LabPeti9** é mais do que apenas uma aplicação, é uma experiência interativa que gerencia tutores e seus queridos pets. Desenvolvido como parte de um desafio de recrutamento, este projeto é uma oportunidade para mostrar suas habilidades técnicas de uma maneira criativa e envolvente.

## Estrutura do Projeto 🏗️

O projeto foi estruturado com o framework Spring Boot, que proporciona uma arquitetura robusta e modular. Aqui estão alguns pontos importantes da estrutura:

- **Pacotes Organizados:**
  - `Controller`: Responsável por lidar com as requisições HTTP e direcioná-las para os serviços adequados.
  - `Service`: Contém a lógica de negócios e interage com os repositórios.
  - `Repository`: Responsável pela comunicação com o banco de dados.
  - `Dto`: Classes que servem como objetos de transferência de dados entre as camadas.
  - `Domain`: Entidades de domínio que representam os modelos de dados.

- **Banco de Dados H2:**
  - Utilizei o banco de dados H2 para facilitar o desenvolvimento e os testes. Os dados são armazenados em memória durante a execução.

- **Validações Personalizadas:**
  - Implementei validações personalizadas para garantir a integridade dos dados, como a verificação de nomes de tutores únicos.

## Destaques 🚀

- **Registrar um Tutor:**
  - Adicione um novo amigo ao LabPeti9 enviando um pedido POST para `/api/tutors`. Inclua detalhes sobre o tutor e seus pets em um formato JSON aconchegante.

- **Ver Todos os Tutores:**
  - Use um pedido GET para `/api/tutors/all` e veja uma lista completa de tutores e seus amiguinhos peludos.

- **Buscar Tutor por ID:**
  - Faça um pedido GET para `/api/tutors/{tutorId}` e descubra tudo sobre um tutor específico.

- **Buscar Tutores por Nome:**
  - Encontre tutores com nomes específicos enviando um pedido GET para `/api/tutors?name={nomeTutor}`.

- **Atualizar Nome do Pet:**
  - Mude o nome de um pet com uma solicitação PUT para `/api/pets/{petId}?newName={novoNome}` e veja a mágica acontecer.

- **Excluir Pet:**
  - Se um pet precisa sair de cena, envie um pedido DELETE para `/api/pets/{petId}` e dê adeus.

## Nível de dificuldade: Baixa

## Teste com Estilo 🧪

Use o [Postman](https://www.postman.com/) para explorar o **LabPeti9**. O arquivo [Postman collection](https://api.postman.com/collections/24163924-c978b29a-b66a-43a0-b73a-1c11e9c3e42a?access_key=PMAT-01HGGP55CJ93TR7YF7JM89D6FY) contém pedidos de exemplo para vários pontos de extremidade. Personalize os payloads e parâmetros conforme necessário.


