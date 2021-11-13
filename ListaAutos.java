public class ListaAutos {
	private Auto [] lista_autos;
	private int cantidad;
	private int max;
	
	public ListaAutos(int max) {
		this.max = max;
		setCantidad(0);
		lista_autos = new Auto[max];
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public boolean agregarAuto(Auto auto) {
		if (cantidad<max) {
			lista_autos[cantidad] = auto;
			cantidad++;
			return true;
		}
		return false;
	}
	
	public Auto buscarAutoporPatente(String patente) {
		for (int i = 0 ; i < cantidad ; i++ ) {
			Auto auto_que_estamos_analizando = lista_autos[i];
			String patente_de_auto = auto_que_estamos_analizando.getPatente();
			if (patente_de_auto.equals(patente)) {
				return auto_que_estamos_analizando;
			}
		}
		return null;
	}
	
	public void desplegarAutos() {
		for (int i = 0; i< cantidad;i++) {
			System.out.println("Auto "+(i+1)+": "+lista_autos[i].getPatente());
		}
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getMax() {
		return max;
	}
}
