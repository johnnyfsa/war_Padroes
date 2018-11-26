package controller;

import java.util.ArrayList;

import model.Carta;
import model.Estado;
import model.Jogador;
import model.Tabuleiro;

public class Game {
	
	private Tabuleiro Mapa;	
	private ArrayList<Jogador> Jogadores = new ArrayList<Jogador>();
	private Carta Baralho[];
	private int NumJogadores;
	
	public void setNumJogadores(int num) {
		NumJogadores = num;
	}
	
	public void setCordoJogador(String Cor)
	{
		Jogador aux = new Jogador();
		aux.setCor(Cor);
		Jogadores.add(aux);
	}
	public ArrayList<Jogador> getJogadores()
	{
		return Jogadores;
	}
		
	
	public void criarMapa() {
		// Estados Regi�o Norte
		Estado AC = new Estado("AC");
		{
			String[] AuxViz = {"AM", "RO"};	
			AC.setVizinhos(AuxViz);
		}		
		Estado AM = new Estado("AM");
		{
			String[] AuxViz = {"AC", "RO", "RR", "PA", "MT"};
			AM.setVizinhos(AuxViz);
		}		
		Estado RO = new Estado("RO");
		{
			String[] AuxViz = {"AC", "RO", "AM", "MT"};
			RO.setVizinhos(AuxViz);
		}		
		Estado RR = new Estado("RR");
		{
			String[] AuxViz = {"PA", "AM"};
			RR.setVizinhos(AuxViz);
		}
		Estado AP = new Estado("AP");
		{
			String[] AuxViz = {"PA"};
			AP.setVizinhos(AuxViz);
		}
		Estado PA = new Estado("PA");		
		{
			String[] AuxViz = {"AM", "MT", "GO", "MA", "AP", "RR"};
			PA.setVizinhos(AuxViz);
		}
		
		// Regi�o Nordeste
		Estado AL = new Estado("AL");
		{
			String[] AuxViz = {"BA", "PE", "SE"};
			AL.setVizinhos(AuxViz);
		}
		Estado MA = new Estado("MA");
		{
			String[] AuxViz = {"PA", "PI", "GO"};
			MA.setVizinhos(AuxViz);
		}
		Estado PI = new Estado("PI");
		{
			String[] AuxViz = {"BA", "MA", "GO", "CE", "PE"};
			PI.setVizinhos(AuxViz);
		}
		Estado CE = new Estado("CE");
		{
			String[] AuxViz = {"PI", "RN", "PB", "PE"};
			CE.setVizinhos(AuxViz);
		}
		Estado RN = new Estado("RN");
		{
			String[] AuxViz = {"PI", "RN", "PB", "PE"};
			RN.setVizinhos(AuxViz);
		}		
		Estado PB = new Estado("PB");
		{
			String[] AuxViz = {"PB", "CE"};
			PB.setVizinhos(AuxViz);
		}
		Estado PE = new Estado("PE");
		{
			String[] AuxViz = {"AL", "PB", "BA", "PI", "CE"};
			PE.setVizinhos(AuxViz);
		}
		Estado SE = new Estado("SE");
		{
			String[] AuxViz = {"BA", "AL"};
			SE.setVizinhos(AuxViz);
		}
		Estado BA = new Estado("BA");
		{
			String[] AuxViz = {"PI", "SE", "AL", "PE", "MG", "ES", "GO"};
			BA.setVizinhos(AuxViz);
		}
		
		// Regi�o Centro-Oeste
		Estado MT = new Estado("MT");
		{
			String[] AuxViz = {"RO", "AM", "PA", "GO", "MG", "SP", "PR"};
			MT.setVizinhos(AuxViz);			
		}
		Estado GO = new Estado("GO");
		{
			String[] AuxViz = {"MT", "PA", "MA", "PI", "BA", "MG"};
			GO.setVizinhos(AuxViz);			
		}
		
		// Regi�o Sudeste
		Estado MG = new Estado("MG");
		{
			String[] AuxViz = {"SP", "GO", "RJ", "BA", "ES"};
			MG.setVizinhos(AuxViz);			
		}
		Estado ES = new Estado("ES");
		{
			String[] AuxViz = {"RJ", "MG"};
			ES.setVizinhos(AuxViz);			
		}
		Estado SP = new Estado("SP");
		{
			String[] AuxViz = {"MG", "PR", "RJ", "MT", "GO"};
			SP.setVizinhos(AuxViz);			
		}
		Estado RJ = new Estado("RJ");
		{
			String[] AuxViz = {"SP", "MG", "ES"};
			RJ.setVizinhos(AuxViz);			
		}
		
		// Regi�o Sul
		Estado PR = new Estado("PR");
		Estado SC = new Estado("SC");
		Estado RS = new Estado("RS");				
		
	}
}
