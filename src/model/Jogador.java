package model;

import java.util.ArrayList;

public class Jogador {
	private ArrayList<Carta> Mao = new ArrayList<Carta>();
	private String Cor;
	private int Exercitos_Disponiveis;
    private int quantidadeTerritorios = 0;
    
    public Jogador() 
    {
    	Mao = new ArrayList<Carta>();
//    	CartaEstado aux= new CartaEstado();
//    	aux.setForma(new Triangulo());
//    	CartaEstado au2x= new CartaEstado();
//    	au2x.setForma(new Triangulo());
//    	CartaEstado aux3= new CartaEstado();
//    	aux3.setForma(new Triangulo());
//    	
//    	Mao.add(aux);
//    	Mao.add(au2x);
//    	Mao.add(aux3);
    }
    
    public void adicionarTerritorios(){
        quantidadeTerritorios++;
    }
    
    public void alteraTerritorio(int i) {
    	quantidadeTerritorios += i;
    }
    
    public void addCarta(Carta carta) {
    	Mao.add(carta);
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
