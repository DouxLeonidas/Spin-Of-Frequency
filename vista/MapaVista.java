package vista;

import modelo.Mapa;

public class MapaVista {
	private Mosaico mosaicos[][];
	private boolean movimientoX, movimientoY;

	public Mosaico[][] getMosaicos() {
		return mosaicos;
	}

	public void setMosaicos(Mosaico[][] mosaicos) {
		this.mosaicos = mosaicos;
	}
	
	public void setMovientoX(boolean movimientoX) {
		this.movimientoX = movimientoX;
	}
	
	public boolean getMovimientoX() {
		return movimientoX;
	}

	public void setMovientoY(boolean movimientoY) {
		this.movimientoY = movimientoY;
	}
	
	public boolean getMovimientoY() {
		return movimientoY;
	}
}
