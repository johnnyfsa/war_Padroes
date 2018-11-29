package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import model.*;


public class Game {
	
	private Tabuleiro Mapa;	
	private ArrayList<Jogador> Jogadores = new ArrayList<Jogador>();
	private ArrayList<Carta> Baralho = new ArrayList<Carta>();
	
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
		Mapa = new Tabuleiro();
		try {
			Scanner scanner = new Scanner (new FileReader("map.txt"));
			while (scanner.hasNext()) 
			{
				String aux = scanner.next();
				Regiao Reg = new Regiao(aux);
				while(!aux.equals("}")) 
				{
					aux = scanner.next();
					if(aux.equals("}"))continue;
					Estado Est = new Estado(aux);
					while(!aux.equals("END")) 
					{
						aux = scanner.next();
						if(aux.equals("END"))continue;
						Est.addVizinhos(aux);
					}
					Reg.addEstados(Est);
				}
				Mapa.addRegiao(Reg);
			//	scanner.close();
			}
			/*for(int regs=0;regs<Mapa.getRegioes().size();regs++) 
			{
				System.out.println(Mapa.getRegioes().get(regs).getNome());
				Regiao Aux = Mapa.getRegioes().get(regs);
				for(int estd=0;estd<Aux.getEstados().size();estd++) 
				{
					System.out.println(Mapa.getRegioes().get(regs).getEstados().get(estd).getNome());
				}
			}*/
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//inicia o Baralho
	public void initBaralho() {
		ArrayList<Regiao> AuxReg= Mapa.getRegioes();
		int EstadoIndex=1;//essa variavel aqui controla quantas cartas foram distribuidas no baralho ate o momento
		for (int i = 0;i < AuxReg.size(); i++) {
			for (int j = 0; j < AuxReg.get(i).getEstados().size();	j++) {
				CartaEstado auxCarta = new CartaEstado();
				auxCarta.setEstado(AuxReg.get(i).getEstados().get(j));
				EstadoIndex++;
				
				if(EstadoIndex%3==0) 
				{
					auxCarta.setForma(new Triangulo());
				}
				else if(EstadoIndex%3==1) 
				{
					auxCarta.setForma(new Quadrado());
				}
				else if(EstadoIndex%3==2) 
				{
					auxCarta.setForma(new Circulo());
				}
				Baralho.add(auxCarta);
			}
		}
		//parte que adiciona os coringas
		
		/*for (int i=0;i<6;i++) 
		{
			Baralho.add(new Coringa());
		}*/
	}
	//testes
	
        
        public void embaralhar(){
            Collections.shuffle(Baralho);
        }
        
        public void distribuirEstados(){
            int jogadorIndex;
            for (int i = 0; i < Baralho.size();i++){                
                jogadorIndex = i%Jogadores.size();
                if(!(Baralho.get(i).getTipo().equals("Coringa"))){
                    ((CartaEstado)Baralho.get(i)).getEstado().setDominante(Jogadores.get(jogadorIndex));
                    ((CartaEstado)Baralho.get(i)).getEstado().setQuantidade_de_Tropas(1);
                    Jogadores.get(jogadorIndex).adicionarTerritorios();
                }
            }
        }
        
        public int getNumeroJogadores(){
            return Jogadores.size();
        }
	//printa o baralho
	public void printaBaralho()
	{
		for(int i=0;i<Baralho.size();i++) 
		{
			if(Baralho.get(i).getTipo()!= "Coringa" ) 
			{
				System.out.println("Carta Tipo:"+ Baralho.get(i).getTipo() + " Estado:" + ((CartaEstado) Baralho.get(i)).getEstado().getNome() +" Forma:"+((CartaEstado)Baralho.get(i)).getForma().getTipoForma());
			}
			else 
			{
				System.out.println("Carta Tipo:" + Baralho.get(i).getTipo());
			}
		}
	}
	//printa as regiões e seus estados
	public void debug() {
		ArrayList<Regiao> AuxReg= Mapa.getRegioes();
		for (int i = 0;i < AuxReg.size(); i++) {
			
			for (int j = 0; j < AuxReg.get(i).getEstados().size();	j++) {
				System.out.println("Regiao: " + AuxReg.get(i).getNome() + ": Estado > " + AuxReg.get(i).getEstados().get(j).getNome() + ". Jogador: " + AuxReg.get(i).getEstados().get(j).getDominante().getCor());
				
			}
		}
	}
      
	public void printMapa() {
		ArrayList<Regiao> AuxReg= Mapa.getRegioes();
		for (int i = 0;i < AuxReg.size(); i++) {
			System.out.print(AuxReg.get(i).getNome() + " - ");
			for (int j = 0; j < AuxReg.get(i).getEstados().size();	j++) {
				System.out.print(AuxReg.get(i).getEstados().get(j).getNome() + "(" + AuxReg.get(i).getEstados().get(j).getDominante().getCor() + "-" + AuxReg.get(i).getEstados().get(j).getQuantidade_de_Tropas()+")");				
				if(j < AuxReg.get(i).getEstados().size()-1) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}
	
    public void distribuirTropas() throws Exception{
    	ArrayList<Regiao> AuxReg= Mapa.getRegioes();    	
        for (int i = 0; i < Jogadores.size();i++){
        	
            System.out.println("Jogador " + (i+1) + " cor " + Jogadores.get(i).getCor() + ": Tem " + (int)Math.ceil(Jogadores.get(i).getQuantidadeTerritorios()/2.0) + " tropas para distribuir:");
            boolean check = false;
            do {
            	Scanner scan = new Scanner(System.in);
            	String[] Escolha = (scan.nextLine()).split("-");
            	System.out.println(Escolha[0] + " " + Escolha[1]);
            	
            	try {
            		Estado es = Mapa.getEstado(scan.toString());
            		if(es.getDominante().equals(Jogadores.get(i).getCor())) {
            			
            		}
            	} catch (Exception e) {
            		System.out.println("Tente novamente: " + e);
            	}
            	
            	
            } while (check);
            
            
        }
    }
        

}

