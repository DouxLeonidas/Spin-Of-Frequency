package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Catalogo.Rutas;

public class VentanaNivel extends JFrame{
	private JPanel panel;
	private JPanel barraVida;
	private JPanel almanaque;
	private JPanel selector;
	
	private JLabel selectorLabel,almanaqueLabel,olaLabel;
	private JProgressBar vidaRestante;
	
	public VentanaNivel() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("Nivel nombre");
		setLayout(null);		
		setUndecorated(true);
		setSize(d.width, d.height);
		setLocation(0,0);
		
		//((Graphics2D)this.getGraphics()).fillRect(0,0,getWidth(),getHeight());
		
		panel = new JPanel();
		panel.setSize(this.getWidth(), (int)(this.getHeight()*0.70));
		panel.setBounds(0, (int)(this.getHeight()*0.1), panel.getWidth(), panel.getHeight());
		panel.setBackground(Color.BLACK);

		barraVida = new JPanel();
		barraVida.setSize(this.getWidth(), (int)(this.getHeight()*0.1));
		barraVida.setBounds(0,0,barraVida.getWidth(),barraVida.getHeight());
		barraVida.setBackground(Color.BLACK);
		barraVida.setLayout(null);
		
		vidaRestante = new JProgressBar();
		vidaRestante.setSize(200, 20);
		vidaRestante.setLayout(null);
		vidaRestante.setLocation(this.getHeight() - 210, 10);
		vidaRestante.setValue(100);
		barraVida.add(vidaRestante);
		vidaRestante.setVisible(false);
		
		selector = new JPanel();
		selector.setSize((int)(this.getWidth()/2), (int)(this.getHeight()*0.20));
		selector.setBounds((int)(this.getWidth()/2),(int)(this.getHeight()*0.80),selector.getWidth(),selector.getHeight());
		selector.setBackground(Color.BLACK);
		selector.setVisible(false);
		
		selectorLabel = new JLabel();
		selectorLabel.setIcon(new ImageIcon(new ImageIcon(Rutas.RUTA_RADAR+Rutas.separator+"RADAR.gif").getImage().getScaledInstance(selector.getWidth()-80, selector.getHeight(), 0)));
		selectorLabel.setBounds(40, 0, selector.getWidth(), selector.getHeight());
		selector.add(selectorLabel);
		
		almanaque = new JPanel();
		almanaque.setSize((int)(this.getWidth()/2), (int)(this.getHeight()*0.20));
		almanaque.setBounds(0,(int)(this.getHeight()*0.80),almanaque.getWidth(), almanaque.getHeight());
		almanaque.setBackground(Color.BLACK);
		almanaque.setVisible(false);

		almanaqueLabel = new JLabel();
		almanaqueLabel.setIcon(new ImageIcon(new ImageIcon(Rutas.RUTA_CORAZON+Rutas.separator+"VIDAnegro.gif").getImage().getScaledInstance(almanaque.getWidth()-80, almanaque.getHeight(), 0)));
		almanaqueLabel.setBounds(40, 0, almanaque.getWidth(), almanaque.getHeight());
		almanaque.add(almanaqueLabel);
		
		olaLabel = new JLabel();
		olaLabel.setIcon(new ImageIcon(new ImageIcon(Rutas.RUTA_WAVES+Rutas.separator+"WAVE.gif").getImage()));
		olaLabel.setBounds(40, 40, 500, 500);
		panel.add(olaLabel);

		add(panel);
		add(barraVida);
		add(selector);
		add(almanaque);
		setBackground(Color.BLACK);
		setVisible(true);
	}
	public JPanel getPanel() {
		return panel;
	}
	
	public JProgressBar getBarraVida() {
		return vidaRestante;
	}
	public JPanel getBarraVidaPanel() {
		return barraVida;
	}
	public JPanel getSelector() {
		return selector;
	}
	public JPanel getAlmanaque() {
		return almanaque;
	}
}
