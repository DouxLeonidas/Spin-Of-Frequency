package Catalogo;

import java.util.ArrayList;

public class Etiquetas {
	private static Etiquetas instance = null;
	private ArrayList<String> rutasImagenes;
	public Etiquetas(){
		rutasImagenes = new ArrayList<String>();
		rutasImagenes.add(Rutas.RUTA_DIALOGOS+Rutas.separator+"Dialogo1.png");
		rutasImagenes.add(Rutas.RUTA_DIALOGOS+Rutas.separator+"Dialogo2-1.png");
		rutasImagenes.add(Rutas.RUTA_DIALOGOS+Rutas.separator+"Dialogo2-2.png");
		rutasImagenes.add(Rutas.RUTA_DIALOGOS+Rutas.separator+"Dialogo3.png");
		rutasImagenes.add(Rutas.RUTA_DIALOGOS+Rutas.separator+"Dialogo4.png");
	}
	public ArrayList<String> getRutasImagenes() {
		return rutasImagenes;
	}
	public static Etiquetas getInstance() {
		if(instance == null) instance = new Etiquetas();
		return instance;
	}
}
