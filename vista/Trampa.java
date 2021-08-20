package vista;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Catalogo.Rutas;

public class Trampa {
	
	private ImageIcon[] trampas;
	private int imagenActual;
	
	public Trampa() {
		trampas = new ImageIcon[25];
		for (int i = 0; i < trampas.length; i++) {
			String arc = Rutas.RUTA_TRAMPAS+Rutas.separator+((i<9)?"000":(i<99)?"00":"0")+(i+1)+".png";
			trampas[i] = new ImageIcon(arc);
		}
		imagenActual = 0;
	}
	
	public void setTrampas(ImageIcon[] trampas) {
		this.trampas = trampas;
	}
	
	public ImageIcon[] getTrampas() {
		return trampas;
	}
	
	public void incrementarImagen() {
		imagenActual++;
		if(imagenActual >= trampas.length)
			imagenActual = 0;
	}
	
	public int getImagenActual() {
		return imagenActual;
	}
}
