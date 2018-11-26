package model;

public class Estado {
	private String Nome;
	private Jogador Dominante;
	private int Quantidade_de_Tropas;
	private String Vizinhos[];
	
	public Estado(String nome) {
		Nome = nome;
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
		for (int i = 0; i < Vizinhos.length; i++) {
			if (Vizinhos[i].equals(nome)) {
				return true;
			}
		}
		return false;
	}
	public void setVizinhos(String[] vizinhos) {
		Vizinhos = vizinhos;
	}

}
