package model;

import java.util.ArrayList;

public class Regiao {
	private ArrayList<Estado> Estados;

	public ArrayList<Estado> getEstados() {
		return Estados;
	}

	public void addEstados(Estado estado) {
		Estados.add(estado);
	}

}
