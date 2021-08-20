package vista;

import java.awt.Image;

import javax.swing.ImageIcon;

import Catalogo.Rutas;

public class PersonajeVista {
	private ImageIcon[] arriba;
	private ImageIcon[] abajo;
	private ImageIcon[] izquierda;
	private ImageIcon[] derecha;
	private ImageIcon[][] movimientos;
	private ImageIcon quieto;
	private int direccion;
	private int imagenActual;
	
	public PersonajeVista() {
		movimientos = new ImageIcon[4][60];		
		arriba = new ImageIcon[60];
		abajo = new ImageIcon[60];
		izquierda = new ImageIcon[60];
		derecha = new ImageIcon[60];
		for (int i = 0; i < arriba.length; i++) {
			arriba[i] = new ImageIcon(Rutas.RUTA_PERSONAJE+Rutas.separator+"Movimiento_atras"+Rutas.separator+(i<10?"000":(i<100?"00":"0"))+i+".png");
			abajo[i] = new ImageIcon(Rutas.RUTA_PERSONAJE+Rutas.separator+"Movimiento_frente"+Rutas.separator+(i<10?"000":(i<100?"00":"0"))+i+".png");
			izquierda[i] = new ImageIcon(Rutas.RUTA_PERSONAJE+Rutas.separator+"Movimiento_izquierda"+Rutas.separator+(i<10?"000":(i<100?"00":"0"))+i+".png");
			derecha[i] = new ImageIcon(Rutas.RUTA_PERSONAJE+Rutas.separator+"Movimiento_derecha"+Rutas.separator+(i<10?"000":(i<100?"00":"0"))+i+".png");
		}
		movimientos[0] = arriba;
		movimientos[1] = derecha;
		movimientos[2] = abajo;
		movimientos[3] = izquierda;
	}
	public ImageIcon[] getArriba() {
		return arriba;
	}
	public void setArriba(ImageIcon[] arriba) {
		this.arriba = arriba;
	}
	public ImageIcon[] getAbajo() {
		return abajo;
	}
	public void setAbajo(ImageIcon[] abajo) {
		this.abajo = abajo;
	}
	public ImageIcon[] getIzquierda() {
		return izquierda;
	}
	public void setIzquierda(ImageIcon[] izquierda) {
		this.izquierda = izquierda;
	}
	public ImageIcon[] getDerecha() {
		return derecha;
	}
	public void setDerecha(ImageIcon[] derecha) {
		this.derecha = derecha;
	}
	public ImageIcon getQuieto() {
		return quieto;
	}
	public void setQuieto(ImageIcon quieto) {
		this.quieto = quieto;
	}
	public ImageIcon[][] getMovimientos() {
		return movimientos;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	public int getImagenActual() {
		return imagenActual;
	}
	public void setImagenActual(int imagenActual) {
		this.imagenActual = imagenActual;
	}
	
	public void incrementaImagenActual() {
		imagenActual++;
		if(imagenActual >= movimientos[direccion].length)
			imagenActual = 0;
	}
	
}
