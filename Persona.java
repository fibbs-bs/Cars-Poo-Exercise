public class Persona {
	private String rut;
	private ListaAutos lista_autos;
	
	public Persona(String rut) {
		this.rut = rut;
		lista_autos = new ListaAutos(10);
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public ListaAutos getLista_Autos() {
		return lista_autos;
	}
}
