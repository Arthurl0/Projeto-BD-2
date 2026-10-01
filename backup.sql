CREATE TABLE alunos (
    id_aluno INTEGER GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    data_nascimento DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf CHAR(11) NOT NULL,
    endereco VARCHAR(100),
    CONSTRAINT pk_alunos PRIMARY KEY (id_aluno),
    CONSTRAINT uq_alunos_cpf UNIQUE (cpf)
);

CREATE TABLE instrutores (
    id_instrutor INTEGER GENERATED ALWAYS AS IDENTITY,
    cpf CHAR(11) NOT NULL,
    cref VARCHAR(50) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100) NOT NULL,
    endereco VARCHAR(100),
    especialidade VARCHAR(50),
    CONSTRAINT pk_instrutores PRIMARY KEY (id_instrutor),
    CONSTRAINT uq_instrutores_cpf UNIQUE (cpf),
    CONSTRAINT uq_instrutores_cref UNIQUE (cref)
);

CREATE TABLE planos (
    id_plano INTEGER GENERATED ALWAYS AS IDENTITY,
    preco NUMERIC(10, 2) NOT NULL,
    descricao VARCHAR(150),
    nome_plano VARCHAR(100) NOT NULL,
    duracao INTEGER NOT NULL,
    CONSTRAINT pk_planos PRIMARY KEY (id_plano),
    CONSTRAINT uq_planos_nome UNIQUE (nome_plano),
    CONSTRAINT chk_planos_preco CHECK (preco >= 0),
    CONSTRAINT chk_planos_duracao CHECK (duracao > 0)
);

CREATE TABLE equipamentos (
    id_equipamento INTEGER GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR(100) NOT NULL,
    marca VARCHAR(100),
    status VARCHAR(50) NOT NULL,
    CONSTRAINT pk_equipamentos PRIMARY KEY (id_equipamento)
);

CREATE TABLE exercicios (
    id_exercicio INTEGER GENERATED ALWAYS AS IDENTITY,
    nome_exercicio VARCHAR(50) NOT NULL,
    grupo_muscular VARCHAR(50) NOT NULL,
    id_equipamento INTEGER,
    CONSTRAINT pk_exercicios PRIMARY KEY (id_exercicio),
    CONSTRAINT uq_exercicios_nome UNIQUE (nome_exercicio),
    CONSTRAINT fk_exercicios_equipamento FOREIGN KEY (id_equipamento)
        REFERENCES equipamentos (id_equipamento) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE matricula (
    id_matricula INTEGER GENERATED ALWAYS AS IDENTITY,
    id_aluno INTEGER NOT NULL,
    id_plano INTEGER NOT NULL,
    data_inicio DATE NOT NULL,
    data_vencimento DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT pk_matricula PRIMARY KEY (id_matricula),
    CONSTRAINT fk_matricula_aluno FOREIGN KEY (id_aluno) 
        REFERENCES alunos (id_aluno) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_matricula_plano FOREIGN KEY (id_plano) 
        REFERENCES planos (id_plano) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE avaliacao_fisica (
    id_avaliacao INTEGER GENERATED ALWAYS AS IDENTITY,
    id_aluno INTEGER NOT NULL,
    id_instrutor INTEGER NOT NULL,
    data_avaliacao DATE NOT NULL,
    peso NUMERIC(5, 2) NOT NULL,
    altura NUMERIC(5, 2) NOT NULL,
    percentual_gordura NUMERIC(5, 2),
    observacoes VARCHAR(150),
    CONSTRAINT pk_avaliacao_fisica PRIMARY KEY (id_avaliacao),
    CONSTRAINT fk_avaliacao_aluno FOREIGN KEY (id_aluno) 
        REFERENCES alunos (id_aluno) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_avaliacao_instrutor FOREIGN KEY (id_instrutor) 
        REFERENCES instrutores (id_instrutor) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT chk_avaliacao_peso CHECK (peso > 0),
    CONSTRAINT chk_avaliacao_altura CHECK (altura > 0)
);

CREATE TABLE fichas (
    id_ficha INTEGER GENERATED ALWAYS AS IDENTITY,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    objetivo VARCHAR(150) NOT NULL,
    id_aluno INTEGER NOT NULL,
    id_instrutor INTEGER NOT NULL,
    CONSTRAINT pk_fichas PRIMARY KEY (id_ficha),
    CONSTRAINT fk_fichas_aluno FOREIGN KEY (id_aluno) 
        REFERENCES alunos (id_aluno) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_fichas_instrutor FOREIGN KEY (id_instrutor) 
        REFERENCES instrutores (id_instrutor) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE item_treino (
    id_item INTEGER GENERATED ALWAYS AS IDENTITY,
    divisao_treino VARCHAR(50) NOT NULL,
    series INTEGER NOT NULL,
    repeticoes INTEGER NOT NULL,
    carga NUMERIC(5, 2),
    id_exercicio INTEGER NOT NULL,
    id_ficha INTEGER NOT NULL,
    CONSTRAINT pk_item_treino PRIMARY KEY (id_item),
    CONSTRAINT fk_item_treino_exercicio FOREIGN KEY (id_exercicio) 
        REFERENCES exercicios (id_exercicio) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_item_treino_ficha FOREIGN KEY (id_ficha) 
        REFERENCES fichas (id_ficha) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT chk_item_series CHECK (series > 0),
    CONSTRAINT chk_item_repeticoes CHECK (repeticoes > 0),
    CONSTRAINT chk_item_carga CHECK (carga >= 0)
);

INSERT INTO alunos (nome, telefone, data_nascimento, email, cpf, endereco) VALUES
('Lucas Silva Pereira', '47991112233', '1998-04-12', 'lucas.silva@email.com', '12345678901', 'Rua das Flores, 120 - Centro'),
('Mariana Costa Ferreira', '47992223344', '2001-08-25', 'mariana.costa@email.com', '23456789012', 'Av. Brasil, 450 - América'),
('Carlos Eduardo Souza', '47993334455', '1992-11-03', 'carlos.souza@email.com', '34567890123', 'Rua XV de Novembro, 890 - Glória'),
('Beatriz Rodrigues Lima', '47994445566', '1995-02-18', 'beatriz.lima@email.com', '45678901234', 'Rua Santa Catarina, 310 - Anita Garibaldi'),
('Rafael Martins Dias', '47995556677', '2003-07-30', 'rafael.dias@email.com', '56789012345', 'Rua Dona Francisca, 1500 - Saguaçu'),
('Juliana Mendes Rocha', '47996667788', '1989-12-14', 'juliana.mendes@email.com', '67890123456', 'Rua Ottokar Doerffel, 200 - Atiradores'),
('Gabriel Alves Ribeiro', '47997778899', '2000-03-22', 'gabriel.ribeiro@email.com', '78901234567', 'Rua Tuiuti, 1020 - Aventureiro'),
('Patrícia Gomes Santos', '47998889900', '1985-09-09', 'patricia.gomes@email.com', '89012345678', 'Rua Marquês de Olinda, 430 - Costa e Silva'),
('Felipe Barros Nogueira', '47999990011', '1997-05-17', 'felipe.barros@email.com', '90123456789', 'Rua Iririú, 600 - Iririú'),
('Camila Duarte Ramos', '47990001122', '2004-10-05', 'camila.duarte@email.com', '01234567890', 'Rua Florianópolis, 80 - Bucarein');

INSERT INTO instrutores (cpf, cref, nome, telefone, email, endereco, especialidade) VALUES
('98765432100', '012345-G/SC', 'Marcos Vinicius Santos', '47988881122', 'marcos.instrutor@academia.com', 'Rua Blumenau, 600 - Centro', 'Musculação e Hipertrofia'),
('87654321099', '023456-G/SC', 'Fernanda Albuquerque', '47988882233', 'fernanda.albuquerque@academia.com', 'Rua Joinville, 750 - Atiradores', 'Treinamento Funcional'),
('76543210988', '034567-G/SC', 'Thiago Henrique Rocha', '47988883344', 'thiago.rocha@academia.com', 'Rua Rio Branco, 220 - Centro', 'Reabilitação e Mobilidade'),
('65432109877', '045678-G/SC', 'Aline Caroline Castro', '47988884455', 'aline.castro@academia.com', 'Rua Princesa Isabel, 115 - Centro', 'Condicionamento Físico'),
('54321098766', '056789-G/SC', 'Rodrigo Fagundes Lima', '47988885566', 'rodrigo.fagundes@academia.com', 'Rua São Paulo, 340 - Bucarein', 'Powerlifting e Força');


INSERT INTO planos (nome_plano, descricao, preco, duracao) VALUES
('Mensal Livre', 'Acesso ilimitado em horário comercial', 139.90, 30),
('Trimestral Fit', 'Acesso ilimitado com acompanhamento trimestral', 359.70, 90),
('Semestral Prime', 'Acesso total e avaliação inclusa', 649.90, 180),
('Anual VIP', 'Acesso total, 1 avaliação mensal e livre acesso de convidados', 1198.80, 365),
('Recorrente Básico', 'Mensalidade sem fidelidade no cartão de crédito', 119.90, 30),
('Day Use / Diária', 'Acesso avulso por 1 dia', 35.00, 1);

INSERT INTO equipamentos (nome, marca, status) VALUES
('Banco de Supino Reto', 'Movement', 'Disponível'),              -- 1
('Leg Press 45 Graus', 'Righetto', 'Disponível'),               -- 2
('Puxador Alto (Lat Pulldown)', 'Matrix Fitness', 'Disponível'), -- 3
('Crossover Polia Dupla', 'Movement', 'Disponível'),            -- 4
('Rack de Agachamento Livre', 'Hammer Strength', 'Disponível'),  -- 5
('Esteira Ergométrica Profissional', 'Movement', 'Em Manutenção'), -- 6
('Cadeira Extensora', 'Righetto', 'Disponível'),                -- 7
('Bicicleta Ergométrica Horizontal', 'Movement', 'Interditado'); -- 8


INSERT INTO exercicios (nome_exercicio, grupo_muscular, id_equipamento) VALUES
('Supino Reto com Barra', 'Peitoral', 1),
('Leg Press 45°', 'Quadríceps', 2),
('Puxada Aberta na Frente', 'Dorsal', 3),
('Tríceps Polia Alta', 'Tríceps', 4),
('Crossover Polia Média', 'Peitoral', 4),
('Agachamento Livre com Barra', 'Membros Inferiores', 5),
('Desenvolvimento Militar', 'Ombros', 5),
('Remada Baixa no Triângulo', 'Dorsal', 3),
('Cadeira Extensora', 'Quadríceps', 7),
('Flexão de Braços Solo', 'Peitoral', NULL),  
('Prancha Abdominal Isométrica', 'Core', NULL), 
('Afundo com Peso Corporal', 'Inferiores', NULL); 

INSERT INTO matricula (id_aluno, id_plano, data_inicio, data_vencimento, status) VALUES
(1, 1, '2025-01-10', '2025-02-10', 'Vencida'),
(1, 2, '2025-02-11', '2025-05-11', 'Vencida'),
(1, 4, '2025-05-12', '2026-05-12', 'Ativa'),
(2, 2, '2026-01-15', '2026-04-15', 'Ativa'),
(3, 1, '2026-01-05', '2026-02-05', 'Vencida'),
(4, 4, '2025-11-01', '2026-11-01', 'Ativa'),
(5, 5, '2026-01-10', '2026-02-10', 'Cancelada'),
(6, 3, '2026-02-01', '2026-08-01', 'Ativa'),
(7, 5, '2026-02-15', '2026-03-15', 'Ativa'),
(8, 3, '2026-01-01', '2026-07-01', 'Trancada'),
(9, 5, '2026-03-01', '2026-04-01', 'Ativa');

INSERT INTO avaliacao_fisica (id_aluno, id_instrutor, data_avaliacao, peso, altura, percentual_gordura, observacoes) VALUES
(1, 1, '2025-01-15', 86.00, 1.78, 22.50, 'Ingresso. Sedentário, leve desvio postural.'),
(1, 1, '2025-06-20', 82.20, 1.78, 18.00, 'Reavaliação semestral. Redução de 3.8kg de gordura.'),
(1, 1, '2026-01-18', 79.50, 1.78, 15.20, '1 ano de treino. Excelente composição corporal.'),
(2, 2, '2026-01-20', 62.00, 1.64, 25.50, 'Foco em condicionamento e resistência cardiovascular.'),
(2, 2, '2026-03-20', 59.80, 1.64, 22.10, 'Reavaliação 60 dias. Ganho de tônus muscular.'),
(4, 3, '2025-11-05', 58.00, 1.68, 18.00, 'Atleta de corrida. Foco em fortalecimento articular.'),
(6, 4, '2026-02-05', 71.30, 1.62, 31.00, 'Retorno pós-parto com liberação médica.'),
(7, 1, '2026-02-18', 68.00, 1.75, 12.00, 'Biotipo ectomorfo. Meta de ganho de massa magra.');

INSERT INTO fichas (id_aluno, id_instrutor, data_inicio, data_fim, objetivo) VALUES
(1, 1, '2025-01-20', '2025-04-20', 'Adaptação e Aprendizado Motor'),
(1, 1, '2026-01-20', '2026-06-20', 'Hipertrofia Avançada (Divisão ABC)'),
(2, 2, '2026-01-22', '2026-04-22', 'Condicionamento Físico e Tonificação (AB)'),
(4, 3, '2025-11-10', '2026-05-10', 'Fortalecimento de Joelho e Core'),
(6, 4, '2026-02-08', '2026-05-08', 'Readaptação Postural e Resistência'),
(7, 1, '2026-02-20', '2026-05-20', 'Hipertrofia Iniciante'),
(3, 1, '2026-01-06', '2026-02-06', 'Adaptação Inicial');

INSERT INTO item_treino (id_ficha, id_exercicio, divisao_treino, series, repeticoes, carga) VALUES
(2, 1,  'Treino A (Peito/Tríceps)', 4, 10, 32.50), 
(2, 5,  'Treino A (Peito/Tríceps)', 3, 12, 17.50), 
(2, 4,  'Treino A (Peito/Tríceps)', 4, 12, 27.50), 
(2, 10, 'Treino A (Peito/Tríceps)', 3, 15, NULL),  
(2, 3,  'Treino B (Costas/Dorsal)', 4, 10, 50.00), 
(2, 8,  'Treino B (Costas/Dorsal)', 3, 12, 45.00), 
(2, 6,  'Treino C (Pernas/Core)',   4, 8,  70.00), 
(2, 2,  'Treino C (Pernas/Core)',   4, 12, 160.00),
(2, 9,  'Treino C (Pernas/Core)',   3, 15, 40.00), 
(2, 11, 'Treino C (Pernas/Core)',   3, 60, NULL),
(3, 2,  'Treino A (Inferiores)',    3, 15, 70.00),
(3, 9,  'Treino A (Inferiores)',    3, 12, 25.00),
(3, 12, 'Treino A (Inferiores)',    3, 12, NULL),  
(3, 3,  'Treino B (Superiores)',    3, 12, 30.00),
(3, 1,  'Treino B (Superiores)',    3, 12, 10.00),
(3, 11, 'Treino B (Superiores)',    3, 45, NULL),
(4, 6,  'Treino Único',             4, 10, 35.00),
(4, 2,  'Treino Único',             3, 12, 90.00),
(4, 7,  'Treino Único',             3, 12, 14.00),
(4, 11, 'Treino Único',             4, 45, NULL);

COMMIT;