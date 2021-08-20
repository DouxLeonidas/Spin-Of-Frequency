package Catalogo;

import java.io.File;
import java.nio.file.FileSystems;

public class Rutas {
	public final static String separator = FileSystems.getDefault().getSeparator();
	public final static String RUTA_GENERAL = "." + separator;
	public final static String RUTA_PAREDES = RUTA_GENERAL + "img" + separator + "paredes";
	public final static String RUTA_CUARZOS = RUTA_GENERAL+ "img" + separator + "cuarzos";
	public final static String RUTA_TRAMPAS = RUTA_GENERAL+ "img" + separator + "trampas";
	public final static String RUTA_PERSONAJE = RUTA_GENERAL+ "img" + separator + "personaje";
	public final static String RUTA_RADAR =   RUTA_GENERAL+ "img" + separator + "radar";
	public final static String RUTA_PORTADA = RUTA_GENERAL+"img" + separator + "portada";
	public final static String RUTA_SUELO = RUTA_GENERAL+ "img" + separator + "suelo";
	public final static String RUTA_DIALOGOS = RUTA_GENERAL+ "img" + separator + "dialogos";
	public final static String RUTA_CORAZON = RUTA_GENERAL+ "img" + separator + "corazon";
	public final static String RUTA_WAVES = RUTA_GENERAL + "img" + separator + "waves";
	
	public final static String RUTA_SOUNDTRACK = RUTA_GENERAL+"audio";
}
