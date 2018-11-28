package model;

import java.util.ArrayList;

public class Tabuleiro {
	private ArrayList<Regiao> Regioes;
	
	public Tabuleiro() {
		Regioes = new ArrayList<Regiao>();
	}

	public ArrayList<Regiao> getRegioes() {
		return Regioes;
	}

	public void addRegiao(Regiao Reg) {
		Regioes.add(Reg);
	}
}
