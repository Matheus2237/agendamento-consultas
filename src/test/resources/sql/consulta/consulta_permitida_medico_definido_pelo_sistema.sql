-- Inserção do paciente

INSERT INTO paciente
    (cpf, email, endereco_bairro, endereco_cep,
    endereco_cidade, endereco_logradouro, endereco_numero,
    endereco_uf, nome, telefone_ddd, telefone_numero)
VALUES
    ('12345678900', 'maria.souza@exemplo.com', 'Bairro Jardim Oliveira',
    '12345678', 'São Paulo', 'Rua Prof Adalberto', '123',
    'SP', 'Maria Souza', '11', '987654321'),
    ('23456789011', 'joao.silva@exemplo.com', 'Bairro Novo Horizonte',
    '87654321', 'São Paulo', 'Rua das Palmeiras', '321',
    'SP', 'João Silva', '11', '998765432'),
    ('34567890122', 'ana.oliveira@exemplo.com', 'Vila Esperança',
    '12349876', 'São Paulo', 'Av. Central', '456',
    'SP', 'Ana Oliveira', '11', '912345678'),
    ('45678901233', 'carlos.santos@exemplo.com', 'Jardim Planalto',
    '65432189', 'São Paulo', 'Rua dos Pinheiros', '789',
    'SP', 'Carlos Santos', '11', '934567890'),
    ('56789012344', 'lucia.almeida@exemplo.com', 'Centro',
    '78965412', 'São Paulo', 'Rua das Flores', '101',
    'SP', 'Lúcia Almeida', '11', '956789012'),
    ('67890123455', 'pedro.fernandes@exemplo.com', 'Vila Mariana',
    '32178965', 'São Paulo', 'Rua da Esperança', '202',
    'SP', 'Pedro Fernandes', '11', '978901234'),
    ('78901234566', 'fernanda.costa@exemplo.com', 'Jardim Paulista',
    '98732154', 'São Paulo', 'Av. Paulista', '303',
    'SP', 'Fernanda Costa', '11', '990123456'),
    ('89012345677', 'marcos.rodrigues@exemplo.com', 'Vila Nova Conceição',
    '65498732', 'São Paulo', 'Rua das Acácias', '404',
    'SP', 'Marcos Rodrigues', '11', '912345987'),
    ('90123456788', 'juliana.martins@exemplo.com', 'Bairro da Liberdade',
    '12378945', 'São Paulo', 'Rua São Bento', '505',
    'SP', 'Juliana Martins', '11', '934598765'),
    ('01234567899', 'ricardo.lima@exemplo.com', 'Jardim América',
    '78912345', 'São Paulo', 'Rua Oscar Freire', '606',
    'SP', 'Ricardo Lima', '11', '956789321'),
    ('11234567890', 'patricia.souza@exemplo.com', 'Mooca',
    '32165498', 'São Paulo', 'Rua da Paz', '707',
    'SP', 'Patrícia Souza', '11', '978932145'),
    ('21234567891', 'roberto.alves@exemplo.com', 'Tatuapé',
    '45698732', 'São Paulo', 'Av. Salim Farah Maluf', '808',
    'SP', 'Roberto Alves', '11', '990876543');

-- Inserção do médico

INSERT INTO medico
    (id, crm, email, endereco_bairro, endereco_cep,
    endereco_cidade, endereco_logradouro, endereco_numero,
    endereco_uf, nome, telefone_ddd, telefone_numero, especializacao)
VALUES
    (1, 'SP123456', 'jotaaug@gmail.com', 'Jardim Planalto',
    '32143654', 'São Paulo', 'Av. Bandeirantes', '21, andar 4, apartamento 42',
    'SP', 'João Augusto', '54', '954332345', 'CARDIOLOGIA'),
    (2, 'SP987654', 'ana.cardio@hospital.com', 'Centro',
    '98765432', 'São Paulo', 'Rua das Flores', '100',
    'SP', 'Ana Cardoso', '11', '912345678', 'CARDIOLOGIA');

INSERT INTO medico_horario_atendimento
    (hora_final, hora_inicial, id_medico, dia_da_semana)
VALUES
    ('17:00:00', '08:00:00', 1, 'DOMINGO'),
    ('17:00:00', '08:00:00', 1, 'QUINTA'),
    ('18:00:00', '09:00:00', 2, 'SEGUNDA'),
    ('18:00:00', '09:00:00', 2, 'SEXTA');

-- Inserção de consultas

INSERT INTO consulta
    (id, id_paciente, id_medico, data, horario)
VALUES
    (1, 2, 1, '2024-07-25', '08:00:00'),
    (2, 3, 1, '2024-07-25', '08:30:00'),
    (3, 4, 1, '2024-07-25', '09:00:00'),
    (4, 5, 1, '2024-07-25', '09:30:00'),
    (5, 6, 1, '2024-07-25', '11:00:00'),
    (6, 7, 1, '2024-07-25', '11:30:00'),
    (7, 8, 1, '2024-07-25', '12:00:00'),
    (8, 9, 1, '2024-07-25', '12:30:00'),
    (9, 10, 1, '2024-07-25', '13:00:00'),
    (10, 11, 1, '2024-07-25', '13:30:00'),
    (11, 12, 1, '2024-07-25', '14:00:00'),
    (12, 3, 2, '2024-07-22', '15:00:00'),
    (13, 4, 2, '2024-07-22', '09:00:00');