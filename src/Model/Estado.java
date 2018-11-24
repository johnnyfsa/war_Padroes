package Model;

public class Estado {
	private Jogador Dominante;
	private int Quantidade_de_Tropas;
	private Estado Vizinhos[];
	
	//métodos
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
	public Estado[] getVizinhos() {
		return Vizinhos;
	}
	public void setVizinhos(Estado vizinhos[]) {
		Vizinhos = vizinhos;
	}

}
