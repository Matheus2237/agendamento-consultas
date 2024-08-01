-- Schema do banco de testes

-- Criação da tabela 'paciente'
CREATE TABLE IF NOT EXISTS paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    endereco_bairro VARCHAR(255),
    endereco_cep VARCHAR(255),
    endereco_cidade VARCHAR(255),
    endereco_logradouro VARCHAR(255),
    endereco_numero VARCHAR(255),
    endereco_uf VARCHAR(255),
    nome VARCHAR(255) NOT NULL,
    telefone_ddd VARCHAR(255),
    telefone_numero VARCHAR(255)
);

-- Criação da tabela 'medico'
CREATE TABLE IF NOT EXISTS medico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crm VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    endereco_bairro VARCHAR(255),
    endereco_cep VARCHAR(255),
    endereco_cidade VARCHAR(255),
    endereco_logradouro VARCHAR(255),
    endereco_numero VARCHAR(255),
    endereco_uf VARCHAR(255),
    nome VARCHAR(255) NOT NULL,
    telefone_ddd VARCHAR(255),
    telefone_numero VARCHAR(255),
    especializacao ENUM('IMUNOLOGIA', 'ANESTESIOLOGIA', 'ANGIOLOGIA', 'CARDIOLOGIA', 'CLINICA', 'COLOPROCTOLOGIA', 'DERMATOLOGIA', 'ENDOCRINOLOGIA', 'GASTROENTEROLOGIA', 'GENETICA_MEDICA', 'GERIATRIA', 'GINECOLOGIA', 'HEMATOLOGIA', 'INFECTOLOGIA', 'MASTOLOGIA', 'ESPORTIVA', 'PERICIA', 'NEFROLOGIA', 'NEUROLOGIA', 'OFTALMOLOGIA', 'ONCOLOGIA', 'ORTOPEDIA', 'OTORRINOLARINGOLOGIA', 'PATOLOGIA', 'PEDIATRIA', 'PNEUMOLOGIA', 'PSIQUIATRIA', 'REUMATOLOGIA', 'UROLOGIA') NOT NULL
);

-- Criação da tabela 'medico_horario_atendimento'
CREATE TABLE IF NOT EXISTS medico_horario_atendimento (
    hora_final TIME(6) NOT NULL,
    hora_inicial TIME(6) NOT NULL,
    id_medico BIGINT NOT NULL,
    dia_da_semana ENUM('DOMINGO', 'SEGUNDA', 'TERCA', 'QUARTA', 'QUINTA', 'SEXTA', 'SABADO') NOT NULL,
    PRIMARY KEY (id_medico, dia_da_semana),
    FOREIGN KEY (id_medico) REFERENCES medico(id) ON DELETE CASCADE
);

-- Criação da tabela 'consulta'
CREATE TABLE IF NOT EXISTS consulta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    horario TIME(6) NOT NULL,
    id_medico BIGINT NOT NULL,
    id_paciente BIGINT NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES medico(id) ON DELETE CASCADE,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id) ON DELETE CASCADE
);