package vista;

import java.awt.Image;

import javax.swing.ImageIcon;

public class VistaArma {
	private ImageIcon[] cristal;
	private int imagenActual;
	public VistaArma() {
		imagenActual = 0;
	}

	public ImageIcon[] getCristal() {
		return cristal;
	}

	public void setCristal(ImageIcon[] cristal) {
		this.cristal = cristal;
	}
	
	public int getImagenActual() {
		return imagenActual;
	}
	
	public void setImagenActual(int imagenActual) {
		this.imagenActual = imagenActual;
	}
	
}
