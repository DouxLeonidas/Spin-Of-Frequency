package controlador;

import modelo.Personaje;
import vista.PersonajeVista;

public class ControladorPersonaje {
	private Personaje personaje;
	private PersonajeVista vista;
	public Personaje getPersonaje() {
		return personaje;
	}
	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
	public PersonajeVista getVista() {
		return vista;
	}
	public void setVista(PersonajeVista vista) {
		this.vista = vista;
	}
	
}
