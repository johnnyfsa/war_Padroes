package model;

import java.util.ArrayList;

public class Regiao {
	private String Nome;
	private ArrayList<Estado> Estados;
	
	public Regiao(String nome) {
		Nome = nome;
		Estados = new ArrayList();
	}
	
	public String getNome() {
		return Nome;
	}
	
	public ArrayList<Estado> getEstados() {
		return Estados;
	}

	public void addEstados(Estado estado) {
		Estados.add(estado);
	}

}
