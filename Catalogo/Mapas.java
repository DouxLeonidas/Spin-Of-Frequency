package Catalogo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Arrays;
import java.nio.file.FileSystems;

import javax.swing.ImageIcon;

import controlador.ArmaControlador;
import controlador.ControladorPersonaje;
import controlador.MapaControlador;
import modelo.Arma;
import modelo.Mapa;
import modelo.Personaje;
import vista.MapaVista;
import vista.Mosaico;
import vista.PersonajeVista;
import vista.Trampa;

public class Mapas {	
	private static Mapas instace=null;
	private MapaControlador[] mapas;
	public static final int[] bloqueadoIzquierda = {1,5,6,10,11,12,13};
	public static final int[] bloqueadoDerecha = {3,7,8,10,11,13,14};
	public static final int[] bloqueadoArriba = {2,6,7,9,12,13,14};
	public static final int[] bloqueadoAbajo = {4,5,8,9,11,12,14};
	private Mapas() {
		mapas = new MapaControlador[1];
		MapaControlador mapa = new MapaControlador();
		mapas[0] = mapa;
		Mapa matriz ;
		mapa.setMatriz(matriz = new Mapa());
		int mapaArreglo[][] = new int[][]{
			{12,2,9,14,6,7,12,9,2,9,2,7,6,9,9,9,14,6,9,9,7,6,9,2,9,9,7,6,9,9,9,7},
			{6,3,6,9,8,5,7,13,5,14,10,10,5,9,7,6,7,10,6,9,8,5,7,5,9,7,10,1,9,9,7,10},
			{10,10,10,12,2,7,5,4,7,6,3,5,7,12,4,8,5,8,1,9,7,6,8,6,9,8,10,5,14,6,3,10},
			{10,5,8,6,8,10,6,9,8,10,5,14,1,7,13,6,9,7,5,7,11,10,12,8,6,9,8,6,9,8,10,10},
			{10,6,9,4,7,5,8,6,7,10,6,7,10,5,8,10,12,8,6,8,6,8,6,2,4,9,9,8,12,9,8,10},
			{11,5,7,12,4,7,6,3,5,4,8,10,5,2,7,10,6,9,4,9,4,7,10,5,9,7,6,2,9,9,7,11},
			{6,7,5,9,7,5,3,1,7,6,9,8,12,8,5,8,5,9,7,12,7,10,5,9,7,10,10,10,6,14,5,7},
			{10,10,6,14,10,12,8,10,10,5,7,6,7,6,7,6,7,6,8,6,8,5,9,14,10,1,8,11,5,9,7,10},
			{10,5,4,7,5,2,14,10,10,13,5,8,5,8,5,8,5,4,7,1,14,6,7,6,8,11,6,9,2,14,10,10},
			{5,7,13,5,7,5,7,10,5,4,9,9,14,6,9,7,6,9,8,10,6,3,5,8,6,9,8,12,4,7,1,8},
			{6,8,1,7,5,7,11,5,9,9,7,6,9,8,6,8,10,6,7,5,8,11,6,14,5,7,6,9,7,10,5,7},
			{10,12,3,10,12,8,6,9,7,13,10,1,7,13,5,9,4,8,5,7,6,9,8,6,7,10,5,7,10,5,7,10},
			{10,6,8,5,9,9,8,6,4,3,10,10,10,1,9,7,6,7,13,10,10,6,9,3,10,5,9,8,10,13,5,8},
			{5,8,6,2,9,7,6,8,13,5,8,11,1,3,6,8,10,10,5,8,10,11,6,8,5,9,9,9,8,1,9,7},
			{6,14,10,10,6,8,1,7,10,6,7,6,8,11,5,9,8,10,6,9,3,6,4,14,6,7,6,9,14,10,12,8},
			{5,2,8,11,5,7,10,11,10,10,10,5,9,7,13,6,9,8,5,7,5,8,6,7,10,5,8,6,2,8,6,7},
			{13,10,12,7,12,3,1,7,1,8,1,9,7,10,10,5,7,12,7,5,9,9,8,11,10,13,6,8,10,13,10,10},
			{5,4,7,10,6,3,10,5,8,12,4,7,10,10,1,7,5,7,10,6,14,6,9,9,8,10,5,7,5,8,10,10},
			{6,9,8,10,10,11,5,9,9,9,7,10,11,5,8,1,7,10,10,1,7,10,6,9,7,1,7,5,9,9,8,10},
			{10,12,9,3,10,6,7,6,7,6,8,5,7,6,7,10,10,10,10,10,10,1,8,13,5,8,1,14,6,7,6,8},
			{5,9,7,10,10,10,5,8,10,5,7,6,4,8,11,10,11,5,8,10,10,5,7,5,9,7,5,9,8,10,5,7},
			{6,9,4,8,1,8,6,7,5,9,8,11,6,9,7,5,7,6,7,10,5,2,8,13,6,3,6,7,13,5,7,10},
			{10,6,9,7,10,6,8,5,9,9,9,7,5,14,5,7,10,10,5,8,12,4,7,5,8,5,3,5,8,12,3,10},
			{10,10,6,8,11,10,6,2,9,14,6,8,6,7,12,15,3,5,14,6,7,6,3,6,9,7,10,6,2,7,5,8},
			{5,8,10,6,9,8,10,10,6,7,5,9,8,10,6,8,10,6,7,10,10,11,10,10,12,8,5,8,10,5,7,13},
			{6,14,10,11,6,9,8,10,10,10,6,9,7,10,10,6,8,10,5,3,10,6,8,1,9,9,9,9,4,7,10,10},
			{5,9,4,7,1,7,13,5,8,5,8,13,5,3,10,5,7,10,6,8,10,5,9,8,6,9,9,2,14,10,1,8},
			{6,9,14,1,8,10,1,9,7,6,7,10,6,8,5,7,11,10,10,6,4,14,6,9,3,6,7,5,7,11,5,7},
			{10,6,7,10,13,5,4,14,5,8,10,10,11,6,7,5,9,8,11,5,9,7,11,6,8,11,5,7,5,7,6,8},
			{5,8,10,1,8,6,9,9,7,6,4,8,6,8,5,9,7,6,9,9,9,4,9,3,6,2,9,8,6,8,5,7},
			{6,7,5,8,6,8,13,6,8,5,9,7,5,9,7,12,8,5,7,13,6,9,9,8,10,11,6,14,5,7,6,8},
			{11,5,9,9,4,14,5,4,9,9,9,4,9,14,5,9,9,9,8,5,4,9,9,14,5,9,4,9,9,4,4,14}};
		matriz.setMapa(mapaArreglo);
		matriz.setInicio(new Point(mapaArreglo.length -1, mapaArreglo[0].length-1)); //Modificar
		matriz.setFin(new Point(0, 0)); //Modificar
		
		MapaVista mapaVista;		
		mapa.setVista(mapaVista = new MapaVista());		
		Mosaico[][] mosaicos = new Mosaico[mapaArreglo.length][mapaArreglo[0].length];//Revisar
		for (int i = 0; i < mosaicos.length; i++) {
			for (int j = 0; j < mosaicos[i].length; j++) {
				mosaicos[i][j] = new Mosaico();
				mosaicos[i][j].setImage(new ImageIcon(Rutas.RUTA_PAREDES+Rutas.separator+mapaArreglo[i][j]+".png"));
			}
		}
		mapaVista.setMosaicos(mosaicos);
		
		mapa.setCuarzos(new ArmaControlador[mapaArreglo.length][mapaArreglo[0].length]);//Revisar
		addCuarzos();
		
		Trampa[][] trampa;
		mapa.setTrampa(trampa = new Trampa[mapaArreglo.length][mapaArreglo[0].length]);//Revisar
		for (int i = 0; i < trampa.length; i++) 
			for (int j = 0; j < trampa[i].length; j++) 
				trampa[i][j] = null;
		addTrampas();
		
		boolean[][] mascara;
		mascara = new boolean[mapaArreglo.length][mapaArreglo[0].length];//Revisar
		for (int i = 0; i < mascara.length; i++) 
			for (int j = 0; j < mascara[i].length; j++) 
				mascara[i][j] = false;
		
		mapa.setControladorPersonaje(new ControladorPersonaje());
		mapa.getControladorPersonaje().setPersonaje(new Personaje(1d, mapa.getMatriz().getInicio(),null));
		mapa.getControladorPersonaje().setVista(new PersonajeVista());
		mapa.getControladorPersonaje().getVista().setDireccion(2);
		Personaje personaje = mapa.getControladorPersonaje().getPersonaje();
		
		mascara[(int)personaje.getPosicion().getX()][(int)personaje.getPosicion().getY()] = true;
		mapa.setMascara(mascara);
		
	}
	public void addCuarzos(){
		ArmaControlador[][] cuarzos = mapas[0].getCuarzos();
		cuarzos[24][19] = Cristales.getInstance().getArmas()[0];
		cuarzos[9][12] = Cristales.getInstance().getArmas()[1];

	}
	public void addTrampas() {
		Trampa[][] trampa = mapas[0].getTrampa();
		// TODO Auto-generated method stub
		trampa[0][10] = new Trampa();
		trampa[2][12] = new Trampa();
		trampa[4][15] = new Trampa();
		trampa[5][18] = new Trampa();
		trampa[6][19] = new Trampa();
		trampa[3][16] = new Trampa();
		trampa[5][10] = new Trampa();
		trampa[7][27] = new Trampa();
		trampa[9][24] = new Trampa();
		trampa[10][28] = new Trampa();
		trampa[12][30] = new Trampa();
		trampa[20][30] = new Trampa();
		trampa[18][30] = new Trampa();
		trampa[15][30] = new Trampa();
		trampa[15][4] = new Trampa();
		trampa[20][6] = new Trampa();
		trampa[19][2] = new Trampa();
		trampa[30][9] = new Trampa();
		trampa[25][18] = new Trampa();
		trampa[27][20] = new Trampa();
		trampa[27][28] = new Trampa();
		trampa[17][20] = new Trampa();
	}
	public static Mapas getInstace() {
		if(instace == null) instace = new Mapas();
		return instace;
	}
	public MapaControlador[] getMapas() {
		return mapas;
	}
}
