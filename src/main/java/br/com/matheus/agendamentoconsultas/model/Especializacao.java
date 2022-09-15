package br.com.matheus.agendamentoconsultas.model;

public enum Especializacao {
	IMUNOLOGIA ("Imunologia"),
	ANESTESIOLOGIA ("Anestesiologia"),
	ANGIOLOGIA ("Angiologia"),
	CARDIOLOGIA ("Cardiologia"),
	CLINICA ("Clínica"),
	COLOPROCTOLOGIA ("Coloproctologia"),
	DERMATOLOGIA ("Dermatologia"),
	ENDOCRINOLOGIA ("Endocrinologia"),
	GASTROENTEROLOGIA ("Gastroenterologia"),
	GENETICA_MEDICA ("Genética Médica"),
	GERIATRIA ("Geriatria"),
	GINECOLOGIA ("Ginecologia"),
	HEMATOLOGIA ("Hematologia"),
	INFECTOLOGIA ("Infectologia"),
	MASTOLOGIA ("Mastologia"),
	ESPORTIVA ("Medicina Esportiva"),
	PERICIA ("Perícia"),
	NEFROLOGIA ("Nefrologia"),
	NEUROLOGIA ("Neurologia"),
	OFTALMOLOGIA ("Oftalmologia"),
	ONCOLOGIA ("Oncologia"),
	ORTOPEDIA ("Ortopedia"),
	OTORRINOLARINGOLOGIA ("Otorrinolaringologia"),
	PATOLOGIA ("Patologia"),
	PEDIATRIA ("Pediatria"),
	PNEUMOLOGIA ("Pneumologia"),
	PSIQUIATRIA ("Psiquiatria"),
	REUMATOLOGIA ("Reumatologia"),
	UROLOGIA ("Urologia");
	
	private final String name;

	Especializacao(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Especializacao stringToEnum(String name) {
		return Especializacao.valueOf(name);
	}
}