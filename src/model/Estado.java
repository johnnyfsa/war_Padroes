package model;

import java.util.ArrayList;

public class Estado {
	private String Nome;
	private Jogador Dominante;
	private int Quantidade_de_Tropas;
	private ArrayList<String> Vizinhos;
	
	
	public void addTropas(int quantidade) {
		Quantidade_de_Tropas += quantidade;
	}
	
	public Estado(String nome) {
		Nome = nome;
		Vizinhos = new ArrayList<String>();
	}
	
	public String getNome() {
		return Nome;
	}        
    public void setNome(String Nome){
            this.Nome = Nome;
    }
	//metodos
	public Jogador getDominante() {
		return Dominante;
	}
	public void setDominante(Jogador dominante) {
		Dominante = dominante;
	}
	public int getQuantidade_de_Tropas() {
		return Quantidade_de_Tropas;
	}
	public void setQuantidade_de_Tropas(int quantidade_de_Tropas) {
		Quantidade_de_Tropas = quantidade_de_Tropas;
	}
	public boolean fazFronteiraCom(String nome) {
		for (int i = 0; i < Vizinhos.size(); i++) {
			if (Vizinhos.get(i).equals(nome)) {
				return true;
			}
		}
		return false;
	}
	public void addVizinhos(String vizinho) {
		Vizinhos.add(vizinho);
	}

}
