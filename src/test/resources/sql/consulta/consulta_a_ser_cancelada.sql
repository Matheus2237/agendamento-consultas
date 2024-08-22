-- Inserção do paciente

INSERT INTO paciente
    (cpf, email, endereco_bairro, endereco_cep,
    endereco_cidade, endereco_logradouro, endereco_numero,
    endereco_uf, nome, telefone_ddd, telefone_numero)
VALUES
    ('12345678900', 'maria.souza@exemplo.com', 'Bairro Jardim Oliveira',
    '12345678', 'São Paulo', 'Rua Prof Adalberto', '123',
    'SP', 'Maria Souza', '11', '987654321');

-- Inserção do médico

INSERT INTO medico
    (id, crm, email, endereco_bairro, endereco_cep,
    endereco_cidade, endereco_logradouro, endereco_numero,
    endereco_uf, nome, telefone_ddd, telefone_numero, especializacao)
VALUES
    (1, 'SP123456', 'jotaaug@gmail.com', 'Jardim Planalto',
    '32143654', 'São Paulo', 'Av. Bandeirantes', '21, andar 4, apartamento 42',
    'SP', 'João Augusto', '54', '954332345', 'CARDIOLOGIA');

INSERT INTO medico_horario_atendimento
    (hora_final, hora_inicial, id_medico, dia_da_semana)
VALUES
    ('17:00:00', '08:00:00', 1, 'DOMINGO'),
    ('17:00:00', '08:00:00', 1, 'QUINTA');

-- Inserção da consulta

INSERT INTO consulta
    (id, id_paciente, id_medico, data, horario)
VALUES
    (1, 1, 1, '2024-07-25', '10:30:00');