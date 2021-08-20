package controlador;

import java.util.ArrayList;

public class Municiones {
	private ArrayList<ArmaControlador> tipos;
	private ArrayList<Integer> disparos;
	private int index;
	public Municiones() {
		tipos= new ArrayList<ArmaControlador>();
		disparos = new ArrayList<Integer>();
		index = -1;
	}
	public ArrayList<ArmaControlador> getTipos() {
		return tipos;
	}
	public void setTipos(ArrayList<ArmaControlador> tipos) {
		this.tipos = tipos;
	}
	public ArrayList<Integer> getDisparos() {
		return disparos;
	}
	public void setDisparos(ArrayList<Integer> disparos) {
		this.disparos = disparos;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
