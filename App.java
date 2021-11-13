import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		ListaPersona lista_persona = new ListaPersona(100);
		ListaAutos lista_auto = new ListaAutos(100);
		
		LecturaArchivo(lista_persona,lista_auto);


		while (true) {
			System.out.println("[1] Desplegar patentes de una persona\n[2] Desplegar rut del dueno de un auto\n[3] Cerrar Sistema\nIngrese opción: ");
			String opcion = scanner.nextLine();
			if (opcion.equals("1")) {
				desplegar_por_rut(lista_persona);
			}
			else {
				if (opcion.equals("2")) {
					desplegar_por_patente(lista_auto);
				}
				else {
					if (opcion.equals("3")) {
						System.out.println("Adios.");
						break;
					}
					else {
						System.out.println("Opción incorrecta, vuelva a ingresar opción");
					}
				}
			}
		}
	}
	
	private static void desplegar_por_rut(ListaPersona l_p) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese rut: ");
		String rut = scan.nextLine();
		Persona A = l_p.buscarPersonaporRut(rut);
		if (A==null) {
			System.out.println("El rut no existe. ");
		}
		else {
			System.out.println();
			A.getLista_Autos().desplegarAutos();
			System.out.println();
		}
	}

	private static void desplegar_por_patente(ListaAutos l_a) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese patente: ");
		String patente = scan.nextLine();
		Auto A = l_a.buscarAutoporPatente(patente);
		if (A==null) {
			System.out.println("La patente no existe. ");
		}
		else {
			Persona p = A.getDueno();
			System.out.println("\nEl rut del dueno del auto con patente "+patente+" es: "+p.getRut()+"\n");
		}
	}

	public static void LecturaArchivo(ListaPersona lista_persona, ListaAutos lista_autos) throws IOException {
		Scanner scan = new Scanner(new File("autos.txt"));
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			String [] partes = linea.split(",");
			String patente = partes[0];
			String rut = partes[1];
			/*
			   Obtendremos a la persona con ese rut en el sistema. (puede ser null)
			   
			   Crearemos al auto porque si o si la patente es nueva, por lo tanto el auto no será igual a alguno
			   ya ingresado en el sistema, entonces lo agregamos.
			   
			*/
			Persona persona = lista_persona.buscarPersonaporRut(rut);
			Auto auto = new Auto(patente);
			if (!lista_autos.agregarAuto(auto) /*Agregamos el auto al sistema*/) {
				// No hay mas espacio en la lista de autos, por lo tanto no se debe seguir leyendo
				// el archivo.
				break;
			}
			/* 
			   Logicamente una persona puede tener muchos autos de distinta patente, pero
			   una misma patente no le puede pertenecer a muchas personas, por lo tanto, un
			   auto le puede pertenecer sólo a una persona, pero esta puede tener muchos autos
			   en su poder. (ver Modelo de dominio)
			   
			   Debido a esto, es que en el archivo (ya que los datos que se ingresan son correctos)
			   puede aparecer muchas veces una persona pero los autos sólo aparecerán una vez.
			   
			   Al leer el archivo nos encontraremos con un par de posibilidades:
			   
			   		1) Es primera vez que se lee a la persona en el archivo.
			   		2) No es primera vez que se lee a la persona en el archivo.
			   		
			   En las dos opciones anteriores si o si el auto será nuevo, ya que no se pueden repetir
			   las patentes (porque una patente solo le puede pertenecer a una persona, por lo tanto
			   existirán patentes únicas)
			   
			   Entonces la implementación de las opciones sería:
			   
			*/ 
			if (persona==null) { //Si la persona no existia en el sistema (es primera vez que se lee en el archivo)
				persona = new Persona(rut); //Creamos la persona
				lista_persona.agregarPersona(persona); //Agregamos la persona a la lista de personas del sistema.
				auto.setDueno(persona); //Establecemos que el dueno de este auto será esa persona. -->Referencia de persona a auto
				persona.getLista_Autos().agregarAuto(auto); //Agregamos al auto a la lista de autos de la persona.
			}
			else { // Si la persona existía en el sistema (ya se habia leído en el archivo)
				auto.setDueno(persona);
				persona.getLista_Autos().agregarAuto(auto); //Agregamos al auto a la lista de autos de la persona.
			}
		}//Fin del while (iteración de lineas del archivo)
	}

}
