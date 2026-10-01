# Projeto Banco de Dados 2

## Criação do Banco de Dados (DDL) e Inserção de dados iniciais (DML)
É necessário criar um database nomeado "Academia" no PostgreSQL. Para realizar a criação e inserção de dados no banco PostgreSQL é necessário executar o comando integral contido em \Projeto-BD-2-main\backup.sql

## Alterando usuário e senha do banco de dados  
Para alterar as credenciais relacionadas ao usuário do banco PostreSQL é necessário alterar no arquivo \Projeto-BD-2-main\src\Conexao.java :
`String user = "postgres";`
`String senha = "udesc";`

## Instruções de Compilação
Execute o seguinte comando no terminal no caminho \Projeto-BD-2-main para compilar a aplicação:

`javac -cp ".;lib/postgresql-42.7.13.jar" -sourcepath src -d bin src/Principal.java src/Conexao.java src/Alunos/*.java src/Instrutores/*.java src/Planos/*.java src/Equipamentos/*.java src/Exercicios/*.java src/Matricula/*.java src/AvaliacaoFisica/*.java src/Fichas/*.java src/Relatorios/*.java`

## Instruções de Execução
Execute o seguinte comando no terminal dentro do caminho \Projeto-BD-2-main para executar a aplicação:

`java -cp "bin;lib/postgresql-42.7.13.jar" Principal`
