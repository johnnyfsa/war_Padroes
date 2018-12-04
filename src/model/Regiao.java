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
	
	public Jogador getDomintante() {
		Jogador dominante = Estados.get(0).getDominante();
		for (int i = 1; i < Estados.size(); i++) {
			if (!(Estados.get(i).getDominante().equals(dominante))) {
				return null;
			}			
		}
		return dominante;
	}
	
}
