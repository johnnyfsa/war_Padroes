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
	
	public Estado getEstado(String nome) throws Exception {
		if (Regioes == null) {
			throw new Exception("Mapa não instanciado");
		} else {
			for (int i=0;i<Regioes.size();i++) {				
				for (int j = 0; j < Regioes.get(i).getEstados().size();j++) {
					if (Regioes.get(i).getEstados().get(j).getNome().equals(nome)) {
						return Regioes.get(i).getEstados().get(j);
					}
				}
			}
			throw new Exception("Estado não existe");
		}
		
	}
}
