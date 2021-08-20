package modelo;

import java.awt.Point;

public class Mapa {
	private int[][] mapa;
	private Point inicio;
	private Point fin;
	public int[][] getMapa() {
		return mapa;
	}
	public void setMapa(int[][] mapa) {
		this.mapa = mapa;
	}
	public Point getInicio() {
		return inicio;
	}
	public void setInicio(Point inicio) {
		this.inicio = inicio;
	}
	public Point getFin() {
		return fin;
	}
	public void setFin(Point fin) {
		this.fin = fin;
	}	
}
