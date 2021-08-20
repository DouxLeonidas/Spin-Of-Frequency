package controlador;

import modelo.Arma;
import vista.VistaArma;

public class ArmaControlador {
	private Arma modelo;
	private VistaArma cristal;
	public Arma getModelo() {
		return modelo;
	}
	public void setModelo(Arma modelo) {
		this.modelo = modelo;
	}
	public VistaArma getCristal() {
		return cristal;
	}
	public void setCristal(VistaArma cristal) {
		this.cristal = cristal;
	}	
	
}
