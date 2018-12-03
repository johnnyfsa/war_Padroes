package model;

import java.util.ArrayList;

public class Jogador {
	private ArrayList<Carta> Mao;
	private String Cor;
	private int Exercitos_Disponiveis;
    private int quantidadeTerritorios = 0;
    
    public Jogador() 
    {
    	Mao = new ArrayList<Carta>();
    }
    
    public void adicionarTerritorios(){
        quantidadeTerritorios++;
    }
    
    public void removerTerritorios(){
        quantidadeTerritorios--;
    }
    
    public int getQuantidadeTerritorios(){
        return quantidadeTerritorios;
    }
        
	public ArrayList<Carta> getMao() {
		return Mao;
	}

	public void setMao(ArrayList<Carta> mao) {
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
