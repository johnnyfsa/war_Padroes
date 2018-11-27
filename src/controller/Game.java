package controller;

import java.util.ArrayList;

import model.Carta;
import model.Estado;
import model.Jogador;
import model.Regiao;
import model.Tabuleiro;

public class Game {
	
	private Tabuleiro Mapa;	
	private ArrayList<Jogador> Jogadores = new ArrayList<Jogador>();
	private Carta Baralho[];
	private int NumJogadores;
	
	public void setNumJogadores(int num) {
		NumJogadores = num;
	}
	
	public void setCordoJogador(Jogador player, String Cor)
	{
		player.setCor(Cor);
	}
	
	public void addJogador(Jogador player) 
	{
		Jogadores.add(player);
	}
	
	public ArrayList<Jogador> getJogadores()
	{
		return Jogadores;
	}
		
	
	public void criarMapa() {
		//Estados Regiao Norte
		Regiao norte = new Regiao("Norte"); 
		Estado AC = new Estado("AC");
		{
			String[] AuxViz = {"AM", "RO"};	
			AC.setVizinhos(AuxViz);
		}
		norte.addEstados(AC);
		
		Estado AM = new Estado("AM");
		{
			String[] AuxViz = {"AC", "RO", "RR", "PA", "MT"};
			AM.setVizinhos(AuxViz);
		}
		norte.addEstados(AM);
		
		Estado RO = new Estado("RO");
		{
			String[] AuxViz = {"AC", "RO", "AM", "MT"};
			RO.setVizinhos(AuxViz);
		}
		norte.addEstados(RO);
		
		Estado RR = new Estado("RR");
		{
			String[] AuxViz = {"PA", "AM"};
			RR.setVizinhos(AuxViz);
		}
		norte.addEstados(RR);
		
		Estado AP = new Estado("AP");
		{
			String[] AuxViz = {"PA"};
			AP.setVizinhos(AuxViz);
		}
		norte.addEstados(AP);
		
		Estado PA = new Estado("PA");		
		{
			String[] AuxViz = {"AM", "MT", "GO", "MA", "AP", "RR"};
			PA.setVizinhos(AuxViz);
		}
		norte.addEstados(PA);

		// Regiao Nordeste
		Regiao nordeste = new Regiao("Nordeste"); 
		Estado AL = new Estado("AL");
		{
			String[] AuxViz = {"BA", "PE", "SE"};
			AL.setVizinhos(AuxViz);
		}
		nordeste.addEstados(AL);
		Estado MA = new Estado("MA");
		{
			String[] AuxViz = {"PA", "PI", "GO"};
			MA.setVizinhos(AuxViz);
		}
		nordeste.addEstados(MA);
		Estado PI = new Estado("PI");
		{
			String[] AuxViz = {"BA", "MA", "GO", "CE", "PE"};
			PI.setVizinhos(AuxViz);
		}
		nordeste.addEstados(PI);
		Estado CE = new Estado("CE");
		{
			String[] AuxViz = {"PI", "RN", "PB", "PE"};
			CE.setVizinhos(AuxViz);
		}
		nordeste.addEstados(CE);
		Estado RN = new Estado("RN");
		{
			String[] AuxViz = {"PI", "RN", "PB", "PE"};
			RN.setVizinhos(AuxViz);
		}		
		nordeste.addEstados(RN);
		Estado PB = new Estado("PB");
		{
			String[] AuxViz = {"PB", "CE"};
			PB.setVizinhos(AuxViz);
		}
		nordeste.addEstados(PB);
		Estado PE = new Estado("PE");
		{
			String[] AuxViz = {"AL", "PB", "BA", "PI", "CE"};
			PE.setVizinhos(AuxViz);
		}
		nordeste.addEstados(PE);
		Estado SE = new Estado("SE");
		{
			String[] AuxViz = {"BA", "AL"};
			SE.setVizinhos(AuxViz);
		}
		nordeste.addEstados(SE);
		Estado BA = new Estado("BA");
		{
			String[] AuxViz = {"PI", "SE", "AL", "PE", "MG", "ES", "GO"};
			BA.setVizinhos(AuxViz);
		}
		nordeste.addEstados(BA);
		
		// Regi�o Centro-Oeste
		Regiao centroOeste = new Regiao("Centro Oeste");
		Estado MT = new Estado("MT");
		{
			String[] AuxViz = {"RO", "AM", "PA", "GO", "MG", "SP", "PR"};
			MT.setVizinhos(AuxViz);			
		}
		centroOeste.addEstados(MT);
		Estado GO = new Estado("GO");
		{
			String[] AuxViz = {"MT", "PA", "MA", "PI", "BA", "MG"};
			GO.setVizinhos(AuxViz);			
		}
		centroOeste.addEstados(GO);
		
		// Regi�o Sudeste
		Regiao sudeste = new Regiao("Sudeste");
		Estado MG = new Estado("MG");
		{
			String[] AuxViz = {"SP", "GO", "RJ", "BA", "ES"};
			MG.setVizinhos(AuxViz);			
		}
		centroOeste.addEstados(MG);
		Estado ES = new Estado("ES");
		{
			String[] AuxViz = {"RJ", "MG"};
			ES.setVizinhos(AuxViz);			
		}
		centroOeste.addEstados(ES);
		Estado SP = new Estado("SP");
		{
			String[] AuxViz = {"MG", "PR", "RJ", "MT", "GO"};
			SP.setVizinhos(AuxViz);			
		}
		centroOeste.addEstados(SP);
		Estado RJ = new Estado("RJ");
		{
			String[] AuxViz = {"SP", "MG", "ES"};
			RJ.setVizinhos(AuxViz);			
		}
		centroOeste.addEstados(RJ);
		
		// Regiao Sul
		Regiao sul = new Regiao("Sul");
		Estado PR = new Estado("PR");
		{
			String[] AuxViz = {"MT", "SP", "SC"};
			PR.setVizinhos(AuxViz);			
		}
		sul.addEstados(PR);
		Estado SC = new Estado("SC");
		{
			String[] AuxViz = {"PR", "RS"};
			SC.setVizinhos(AuxViz);			
		}
		sul.addEstados(SC);
		Estado RS = new Estado("RS");				
		{
			String[] AuxViz = {"SC"};
			RS.setVizinhos(AuxViz);			
		}
		sul.addEstados(RS);
		
		
		Regiao[] AuxReg = {norte, nordeste, centroOeste, sudeste, sul};
		Mapa = new Tabuleiro();
		Mapa.setRegioes(AuxReg);
	}
	
	public void debug() {
		Regiao[] AuxReg = Mapa.getRegioes();
		for (int i = 0;i < AuxReg.length; i++) {
			
			for (int j = 0; j < AuxReg[i].getEstados().size();	j++) {
				System.out.println("Regiao: " + AuxReg[i].getNome() + ": Estado > " + AuxReg[i].getEstados().get(j).getNome());				
			}
		}
	}

}

