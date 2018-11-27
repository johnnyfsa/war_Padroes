package model;

public class Jogador {
	private Carta Mao[];
	private String Cor;
	private int Exercitos_Disponiveis;
        private int quantidadeTerritorios = 0;
        
        public void adicionarTerritorios(){
            quantidadeTerritorios++;
        }
        
        public void removerTerritorios(){
            quantidadeTerritorios--;
        }
        
        public int getQuantidadeTerritorios(){
            return quantidadeTerritorios;
        }
        
	public Carta[] getMao() {
		return Mao;
	}

	public void setMao(Carta mao[]) {
		Mao = mao;
	}

	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public int getExercitos_Disponiveis() {
		return Exercitos_Disponiveis;
	}

	public void setExercitos_Disponiveis(int exercitos_Disponiveis) {
		Exercitos_Disponiveis = exercitos_Disponiveis;
	}

}
