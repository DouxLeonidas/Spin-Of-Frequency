package controlador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Catalogo.Cristales;
import Catalogo.Etiquetas;
import Catalogo.Mapas;
import Catalogo.Rutas;
import modelo.Personaje;
import vista.VentanaNivel;



public class NivelControlador implements KeyListener{
	VentanaNivel nivel;
	private JPanel panel;
	private MapaControlador mapa;
	private int tiempoTranscurrido, tiempoLimite;
	private Municiones municiones;
	
	public NivelControlador(){
		nivel = new VentanaNivel();
		panel = nivel.getPanel();
		municiones = new Municiones();
		/*try {
			File audioArchivo = new File(Rutas.RUTA_SOUNDTRACK + "\\intro.wav");
			//FileInputStream fis = new FileInputStream(audioArchivo);
			Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioArchivo);
			sonido.open(ais);
			sonido.start();
		} catch(Exception ex) { 
			System.err.println(ex);
		}*/
		
		//JOptionPane.showMessageDialog(null, "Inicia a Cargar");
		nivel.getPanel().setSize(nivel.getPanel().getWidth(), nivel.getPanel().getHeight() + 160);
		
		JLabel pila = new JLabel();
		pila.setIcon(new ImageIcon(new ImageIcon(Rutas.RUTA_PORTADA+Rutas.separator+"loading.gif").getImage().getScaledInstance(400, 100, 0)));
		pila.setBounds(100, 600, 400, 100);
		panel.add(pila);
		panel.repaint();
		
		Graphics2D g2d = (Graphics2D)panel.getGraphics();
		g2d.drawImage((new ImageIcon(Rutas.RUTA_PORTADA+Rutas.separator+"PORTADA.jpg")).getImage(),(int)(panel.getWidth()*6/10), 0, (int)(panel.getWidth()*4/10), panel.getHeight()*694/771, null);
		g2d.drawImage((new ImageIcon(Rutas.RUTA_PORTADA+Rutas.separator+"Intro1.1.png")).getImage(), 0, 0, (int)(panel.getWidth()*6/10), panel.getHeight(), null);
		
		//g2d.drawImage((new ImageIcon(Rutas.RUTA_PORTADA+"\\Intro1.1.jpg")).getImage(), 0, 0, panel.getHeight()*4/3, panel.getHeight(), null);
		mapa = Mapas.getInstace().getMapas()[0];
		nivel.addKeyListener(this);
		try {
			File audioArchivo = new File(Rutas.RUTA_SOUNDTRACK + Rutas.separator +  "untitled.wav");
			//FileInputStream fis = new FileInputStream(audioArchivo);
			Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioArchivo);
			sonido.open(ais);
			sonido.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception ex) { 
			System.err.println(ex);
		}
		
		//Se especifica Mapa e imagenes de paredes
		//mapa.ge
		
		//JOptionPane.showMessageDialog(null, "Termina Cargar");
		nivel.getPanel().remove(pila);
		nivel.getPanel().setSize(nivel.getPanel().getWidth(), nivel.getPanel().getHeight() - 160);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(panel.getWidth()-panel.getHeight()*771/694, 0, panel.getHeight()*771/694, panel.getHeight());
		g2d.fillRect(0, 0, panel.getHeight()*771/694, panel.getHeight());
		nivel.getSelector().setVisible(true);
		nivel.getAlmanaque().setVisible(true);
		nivel.getBarraVida().setVisible(true);
		
		mapa.getControladorPersonaje().getPersonaje().setVida(100);
		
		tiempoLimite = 1000 * 60 * 7; //Tiempo en milisegundos
		tiempoTranscurrido = 0;
		
		
		while(true) {
			mapa.cargarMapa(panel);
			try {
				Thread.sleep(1000/120);
			} catch(Exception ex) {}
			tiempoTranscurrido += 1000/120;
			mapa.getControladorPersonaje().getPersonaje().setVida(100 - (tiempoTranscurrido * 100 / tiempoLimite));
			nivel.getBarraVida().setValue((int)(mapa.getControladorPersonaje().getPersonaje().getVida()));
			if(tiempoTranscurrido >= tiempoLimite) {
				JOptionPane.showMessageDialog(null, "Oxigeno terminado");
				System.exit(0);
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		Point posActual = mapa.getControladorPersonaje().getPersonaje().getPosicion();
		System.out.println(posActual.x + "," + posActual.y);
		int idex=0;
		switch(code) {
			case 49:
				System.out.println("Tab");
				idex = municiones.getIndex();
				if(municiones.getDisparos().size()!=0) {
					System.out.println("idex:"+idex+" size:"+municiones.getDisparos().size()+"");
					municiones.setIndex(idex = ((idex+1) % municiones.getDisparos().size()));
					mapa.getControladorPersonaje().getPersonaje().setArma(municiones.getTipos().get(idex).getModelo());
					System.out.println("idex:"+idex+" size:"+municiones.getDisparos().size()+"");
					idex = -1;
				}
			break;
			case 32:
				System.out.println("Space");
				mapa.setWaveVisible(true);
				mapa.setWaveActual(0);
				//if(municiones.isEmpty()) break;
				if(municiones.getIndex() != -1 && municiones.getDisparos().get(municiones.getIndex())<1) break;
				municiones.getDisparos().set(municiones.getIndex(),municiones.getDisparos().get(municiones.getIndex())-1);
				if(mapa.getControladorPersonaje().getPersonaje().getArma()!=null) {
					boolean mask[][] = mapa.getMascara();
					int[][] alcance = mapa.getControladorPersonaje().getPersonaje().getArma().getAlcance();
					for (int i = 0; i < mask.length; i++) {
						for (int j = 0; j < mask[i].length; j++) {
							int posX = i+(mask.length-posActual.x);
							int posY = j+(mask[0].length-posActual.y);
							if(posX<mask.length && posY<mask[0].length){
								if(alcance[posX][posY]==1){
									mask[i][j] = true;
								}
							}							 						
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Aun no has encontrado un arma");										
				}
					
			break;
			case 37:
				System.out.println("Izquierda");
				if(!(Arrays.binarySearch(Mapas.bloqueadoIzquierda, mapa.getMatriz().getMapa()[posActual.y][posActual.x])>-1) && posActual.x > 0 && !mapa.getVista().getMovimientoY() && !mapa.getVista().getMovimientoX()) {
					mapa.getControladorPersonaje().getPersonaje().setPosicion(new Point((int)Math.max(posActual.getX()-1, 0), (int)posActual.getY()));
					mapa.setDespX(mapa.getTam() * -1);
					mapa.getVista().setMovientoX(true);
					mapa.getControladorPersonaje().getVista().setDireccion(3);
				}
				break;
			case 38:
				System.out.println("Arriba");
				if(!(Arrays.binarySearch(Mapas.bloqueadoArriba, mapa.getMatriz().getMapa()[posActual.y][posActual.x])>-1) && posActual.y > 0 && !mapa.getVista().getMovimientoY() && !mapa.getVista().getMovimientoX()) {
					mapa.getControladorPersonaje().getPersonaje().setPosicion(new Point((int)posActual.getX(), (int)Math.max(posActual.getY()-1, 0)));
					mapa.setDespY(mapa.getTam() * -1);
					mapa.getVista().setMovientoY(true);
					mapa.getControladorPersonaje().getVista().setDireccion(0);
				}
				break;
			case 39:
				System.out.println("Derecha");
				int ancho = mapa.getMatriz().getMapa().length;
				if(!(Arrays.binarySearch(Mapas.bloqueadoDerecha, mapa.getMatriz().getMapa()[posActual.y][posActual.x])>-1) && posActual.x < ancho && !mapa.getVista().getMovimientoY() && !mapa.getVista().getMovimientoX()) {
					mapa.getControladorPersonaje().getPersonaje().setPosicion(new Point((int)Math.min(posActual.getX()+1, ancho-1), (int)posActual.getY()));
					mapa.setDespX(mapa.getTam());
					mapa.getVista().setMovientoX(true);
					mapa.getControladorPersonaje().getVista().setDireccion(1);
				}
				break;
			case 40:
				System.out.println("Abajo");
				int alto = mapa.getMatriz().getMapa()[0].length;
				if(!(Arrays.binarySearch(Mapas.bloqueadoAbajo, mapa.getMatriz().getMapa()[posActual.y][posActual.x])>-1) && posActual.y < alto && !mapa.getVista().getMovimientoY() && !mapa.getVista().getMovimientoX()) {
					mapa.getControladorPersonaje().getPersonaje().setPosicion(new Point((int)posActual.getX(),(int)Math.min(posActual.getY()+1, alto-1)));
					mapa.setDespY(mapa.getTam());
					mapa.getVista().setMovientoY(true);
					mapa.getControladorPersonaje().getVista().setDireccion(2);
				}
				break;
			case 27:
			case 521:
				System.exit(0);
				break;
			default:
				System.out.println("Nuevo "+code);	
				break;
		}
		Point nuevaPos = mapa.getControladorPersonaje().getPersonaje().getPosicion();
		mapa.getMascara()[nuevaPos.x][nuevaPos.y] = true;
		if(mapa.getCuarzos()[nuevaPos.x][nuevaPos.y] != null) {
			//JOptionPane.showMessageDialog(null, "Cuarzo!");
			((Graphics2D)nivel.getBarraVidaPanel().getGraphics()).drawImage((new ImageIcon(Etiquetas.getInstance().getRutasImagenes().get(1))).getImage(), 0, 0, nivel.getBarraVidaPanel().getWidth()*4/6, (int)(nivel.getBarraVidaPanel().getHeight()*1.2), null);
			nivel.getBarraVida().repaint();
			municiones.getTipos().add(mapa.getCuarzos()[nuevaPos.x][nuevaPos.y]);
			municiones.getDisparos().add(new Integer(5));
			municiones.setIndex(municiones.getIndex()+1);
			mapa.getControladorPersonaje().getPersonaje().setArma(mapa.getCuarzos()[nuevaPos.x][nuevaPos.y].getModelo());
			mapa.getCuarzos()[nuevaPos.x][nuevaPos.y] = null;			
		}
		if(mapa.getTrampa()[nuevaPos.x][nuevaPos.y] != null) {
			//JOptionPane.showMessageDialog(null, "Trampa :(");
			((Graphics2D)nivel.getBarraVidaPanel().getGraphics()).drawImage((new ImageIcon(Etiquetas.getInstance().getRutasImagenes().get(4))).getImage(), 0, 0, nivel.getBarraVidaPanel().getWidth()*4/6, (int)(nivel.getBarraVidaPanel().getHeight()*1.2), null);
			nivel.getBarraVida().repaint();

			boolean[][] mascara = mapa.getMascara();

			for (int i = 0; i < mascara.length; i++) 
				for (int j = 0; j < mascara[i].length; j++) 
					mascara[i][j] = false;
			Mapas.getInstace().addCuarzos();
			Mapas.getInstace().addTrampas();

			Personaje personaje = mapa.getControladorPersonaje().getPersonaje();
			personaje.setPosicion(mapa.getMatriz().getInicio());
			mascara[(int)personaje.getPosicion().getX()][(int)personaje.getPosicion().getY()] = true;
			
		}
		if(nuevaPos.equals(mapa.getMatriz().getFin())) {
			JOptionPane.showMessageDialog(null, "Salvaste a la humanidad! Has Ganado :D");
			//((Graphics2D)nivel.getBarraVidaPanel().getGraphics()).drawImage((new ImageIcon(Etiquetas.getInstance().getRutasImagenes().get(4))).getImage(), 0, 0, nivel.getBarraVidaPanel().getWidth()*4/6, (int)(nivel.getBarraVidaPanel().getHeight()*1.2), null);
			//nivel.getBarraVida().repaint();
			boolean[][] mascara = mapa.getMascara();

			for (int i = 0; i < mascara.length; i++) 
				for (int j = 0; j < mascara[i].length; j++) 
					mascara[i][j] = false;
			Mapas.getInstace().addCuarzos();
			Mapas.getInstace().addTrampas();

			Personaje personaje = mapa.getControladorPersonaje().getPersonaje();
			personaje.setPosicion(mapa.getMatriz().getInicio());
			mascara[(int)personaje.getPosicion().getX()][(int)personaje.getPosicion().getY()] = true;
			tiempoTranscurrido = 0;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public Municiones getMuniciones() {
		return municiones;
	}
}
