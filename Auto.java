public class Auto {
	private String patente;
	private Persona dueno;
	
	public Auto(String patente) {
		this.patente=patente;
	}
	
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public String getPatente() {
		return patente;
	}

	public Persona getDueno() {
		return dueno;
	}

	public void setDueno(Persona dueno) {
		this.dueno = dueno;
	}
}
