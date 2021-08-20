package modelo;

import java.awt.Point;

public class Personaje {
	private double vida;
	private Point posicion;
	private Arma arma;
	
	public Personaje() {
		// TODO Auto-generated constructor stub
	}
	
	public Personaje(double vida, Point posicion, Arma arma) {
		super();
		this.vida = vida;
		this.posicion = posicion;
		this.arma = arma;
	}

	public double getVida() {
		return vida;
	}
	public void setVida(double vida) {
		this.vida = vida;
	}
	public Point getPosicion() {
		return posicion;
	}
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	public Arma getArma() {
		return arma;
	}
	public void setArma(Arma arma) {
		this.arma = arma;
	}
}
