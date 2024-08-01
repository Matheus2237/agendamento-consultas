-- limpeza das tabelas da base de teste

DELETE FROM consulta;
DELETE FROM medico_horario_atendimento;
DELETE FROM medico;
DELETE FROM paciente;

-- Reset do id autoincrementável
ALTER TABLE paciente AUTO_INCREMENT = 1;
ALTER TABLE medico AUTO_INCREMENT = 1;
ALTER TABLE consulta AUTO_INCREMENT = 1;