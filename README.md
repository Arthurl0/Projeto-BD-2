# Projeto Banco de Dados 2

## Instruções de Compilação
Execute o seguinte comando no terminal no caminho \Projeto-BD-2-main para compilar a aplicação:

`javac -cp ".:lib/postgresql-42.7.13.jar" -sourcepath src -d bin src/Principal.java src/Conexao.java src/Alunos/*.java src/Instrutores/*.java src/Planos/*.java src/Equipamentos/*.java src/Exercicios/*.java src/Matricula/*.java src/AvaliacaoFisica/*.java src/Fichas/*.java src/Relatorios/*.java`

## Instruções de Execução
Execute o seguinte comando no terminal dentro do caminho \Projeto-BD-2-main para executar a aplicação:

`java -cp "bin;lib/postgresql-42.7.13.jar" Principal`
