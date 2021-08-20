package controlador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Catalogo.Rutas;
import modelo.Mapa;
import modelo.Personaje;
import vista.MapaVista;
import vista.Trampa;

public class MapaControlador {
	private Mapa matriz;
	private MapaVista vista;
	private boolean[][] mascara;
	private ArmaControlador[][] cuarzos; // Localizables en el area
	private Trampa[][] trampa;
	private ControladorPersonaje controladorPersonaje;
	private int tam, despX, despY;
	private ImageIcon suelo;
	private ImageIcon[] waveScene;
	private boolean waveVisible;
	private int waveActual;

	public MapaControlador() {
		tam = 100;
		despX = despY = 0;
		waveActual = 0;
		waveVisible = false;
		waveScene = new ImageIcon[15];
		suelo = new ImageIcon(Rutas.RUTA_SUELO + Rutas.separator +  "Suelo_Copia1_estaAhora.jpg");
		for(int i=0; i<waveScene.length; i++) {
			System.out.println(Rutas.RUTA_WAVES +Rutas.separator+ "wave" + i + ".png");
			waveScene[i] = new ImageIcon(Rutas.RUTA_WAVES +Rutas.separator+ "wave" + i + ".png");
		}
	}
	public void cargarMapa(JPanel panel){
		
		BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D)bi.getGraphics();

		int miniTam = 7;
		BufferedImage miniMapa = new BufferedImage(matriz.getMapa().length * miniTam, matriz.getMapa()[0].length * miniTam, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dMiniMapa = (Graphics2D)miniMapa.getGraphics();
		
		int iIni = (int)controladorPersonaje.getPersonaje().getPosicion().getX() - 5;
		int jIni = (int)controladorPersonaje.getPersonaje().getPosicion().getY() - 5;
		int iMax = matriz.getMapa().length;
		int jMax = matriz.getMapa()[0].length;
		
		if(vista.getMovimientoX()) {
			if(despX != 0) {
				despX -= (int)(despX / Math.abs(despX));
			} else {
				vista.setMovientoX(false);
			}
		}
		
		if(vista.getMovimientoY()) {
			if(despY != 0) {
				despY -= (int)(despY / Math.abs(despY));
			} else {
				vista.setMovientoY(false);
			}
		}
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if((i + iIni < iMax) && (j + jIni < jMax) && (i + iIni >= 0) && (j + jIni >= 0)) { 
					if(mascara[i + iIni][j + jIni]) {
						g2d.drawImage(suelo.getImage(), (i-1)*tam + despX, (j-1)*tam + despY, tam, tam, null);
						g2d.drawImage(vista.getMosaicos()[j+jIni][i+iIni].getImage().getImage(), (i-1)*tam + despX, (j-1)*tam + despY, tam, tam, null);
						if(cuarzos[i+iIni][j+jIni] != null) {
							g2d.drawImage(cuarzos[i+iIni][j+jIni].getCristal().getCristal()[cuarzos[i+iIni][j+jIni].getCristal().getImagenActual()].getImage(), (i-1)*tam + despX + tam/3, (j-1)*tam + despY + tam/3, tam/3, tam/3, null);
							cuarzos[i+iIni][j+jIni].getCristal().setImagenActual(cuarzos[i+iIni][j+jIni].getCristal().getImagenActual() + 1);
							if(cuarzos[i+iIni][j+jIni].getCristal().getImagenActual() >= cuarzos[i+iIni][j+jIni].getCristal().getCristal().length)
								cuarzos[i+iIni][j+jIni].getCristal().setImagenActual(0);
						}
						if(trampa[i+iIni][j+jIni] != null) {
							g2d.drawImage(trampa[i+iIni][j+jIni].getTrampas()[trampa[i+iIni][j+jIni].getImagenActual()].getImage(), (i-1)*tam + despX, (j-1)*tam + despY, tam, tam, null);
							trampa[i+iIni][j+jIni].incrementarImagen();
						}
					} else {
						g2d.setColor(new Color(0,0,0));
						g2d.fillRect((i-1)*tam + despX, (j-1)*tam + despY, tam, tam);
					}
				} else {
					g2d.setColor(new Color(0,0,0));
					g2d.fillRect((i-1)*tam + despX, (j-1)*tam + despY, tam, tam);
				}
			}
		}

		if(vista.getMovimientoX() || vista.getMovimientoY()) {
			g2d.drawImage(controladorPersonaje.getVista().getMovimientos()[controladorPersonaje.getVista().getDireccion()][controladorPersonaje.getVista().getImagenActual()].getImage(), 4*tam+tam/5, 4*tam+tam/5, tam*3/5, tam*3/5, null);
			controladorPersonaje.getVista().incrementaImagenActual();
		} else {
			g2d.drawImage(controladorPersonaje.getVista().getMovimientos()[controladorPersonaje.getVista().getDireccion()][30].getImage(), 4*tam+tam/5, 4*tam+tam/5, tam*3/5, tam*3/5, null);	
		}
		//
		
		for(int j = 0; j < mascara[0].length; j++) {
			for(int i = 0; i < mascara.length; i++) {
				if(mascara[i][j]) {
					g2dMiniMapa.drawImage(vista.getMosaicos()[j][i].getImage().getImage(), i*miniTam, j*miniTam, miniTam, miniTam, null);
				} else {
					g2dMiniMapa.setColor(new Color(0,0,0));
					g2dMiniMapa.fillRect(i*miniTam, j*miniTam, miniTam, miniTam);
				}
			}
		}
		g2dMiniMapa.setColor(Color.RED);
		g2dMiniMapa.fillRect(controladorPersonaje.getPersonaje().getPosicion().x * miniTam, controladorPersonaje.getPersonaje().getPosicion().y * miniTam, miniTam/2 + miniTam/4, miniTam/2 + miniTam/4);
		g2dMiniMapa.setColor(Color.BLUE);
		g2dMiniMapa.fillRect(matriz.getFin().x * miniTam, matriz.getFin().y * miniTam, miniTam/2 + miniTam/4, miniTam/2 + miniTam/4);
		
		((Graphics2D)panel.getGraphics()).drawImage(miniMapa, 0, 0, miniMapa.getWidth(), miniMapa.getHeight(), null);
		
		
		if(waveVisible) {
			g2d.drawImage(waveScene[waveActual].getImage(), -1 * tam, -1 * tam, panel.getWidth(), panel.getWidth(), null);
			waveActual++;
			if(waveActual >= waveScene.length) {
				waveActual = 0;
				waveVisible = false;
			}
		}

		g2d = (Graphics2D)panel.getGraphics();

		g2d.drawImage(bi, (panel.getWidth()-panel.getHeight())/2, 0, panel.getWidth(), panel.getHeight(), null);
		
	}

	public Mapa getMatriz() {
		return matriz;
	}

	public void setMatriz(Mapa matriz) {
		this.matriz = matriz;
	}

	public MapaVista getVista() {
		return vista;
	}

	public void setVista(MapaVista vista) {
		this.vista = vista;
	}

	public boolean[][] getMascara() {
		return mascara;
	}

	public void setMascara(boolean[][] mascara) {
		this.mascara = mascara;
	}

	public ArmaControlador[][] getCuarzos() {
		return cuarzos;
	}

	public void setCuarzos(ArmaControlador[][] cuarzos) {
		this.cuarzos = cuarzos;
	}

	public Trampa[][] getTrampa() {
		return trampa;
	}

	public void setTrampa(Trampa[][] trampa) {
		this.trampa = trampa;
	}
	public void setControladorPersonaje(ControladorPersonaje controladorPersonaje) {
		this.controladorPersonaje = controladorPersonaje;
	}
	public ControladorPersonaje getControladorPersonaje() {
		return controladorPersonaje;
	}
	
	public void setDespX(int despX) {
		this.despX = despX;
	}
	
	public void setDespY(int despY) {
		this.despY = despY;
	}
	
	public int getDespX(){
		return despX;
	}
	
	public int getDespY(){
		return despY;
	}
	
	public int getTam() {
		return tam;
	}
	
	public void setTam(int tam) {
		this.tam = tam;
	}
	
	public ImageIcon getSuelo() {
		return suelo;
	}

	public void setWaveScene(ImageIcon[] waveScene) {
		this.waveScene = waveScene;
	}

	public void setWaveActual(int waveActual) {
		this.waveActual = waveActual;
	}

	public void setWaveVisible(boolean waveVisible) {
		this.waveVisible = waveVisible;
	}

	public ImageIcon[] getWaveScene() {
		return waveScene;
	}

	public int getWaveActual() {
		return waveActual;
	}

	public boolean getWaveVisible() {
		return waveVisible;
	}
}
