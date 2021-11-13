public class ListaPersona {
	private Persona [] lista_persona;
	private int cantidad;
	private int max;
	
	public ListaPersona(int max) {
		this.max = max;
		setCantidad(0);
		lista_persona = new Persona[max];
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public boolean agregarPersona(Persona persona) {
		if (cantidad<max) {
			lista_persona[cantidad] = persona;
			cantidad++;
			return true;
		}
		return false;
	}
	
	public Persona buscarPersonaporRut(String rut) {
		for (int i = 0 ; i < cantidad ; i++ ) {
			Persona persona_que_estamos_analizando = lista_persona[i];
			String rut_de_persona = persona_que_estamos_analizando.getRut();
			if (rut_de_persona.equals(rut)) {
				return persona_que_estamos_analizando;
			}
		}
		return null;
	}
	
	public void desplegarPersona() {
		for (int i = 0; i< cantidad;i++) {
			System.out.println("Persona "+i+": "+lista_persona[i].getRut());
		}
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getMax() {
		return max;
	}
}
