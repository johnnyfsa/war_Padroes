package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterMakeAndModel;

import model.*;


public class Game {
	
	public Tabuleiro Mapa;	
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
	
	public void rodadaInicial() throws Exception {
		//ArrayList<Regiao> AuxReg= Mapa.getRegioes();	
		for (int i = 0; i < Jogadores.size(); i++) {
			printMapa();
			inicioDeTurno(Jogadores.get(i));
		}
	}
	
	public void atacar(Estado atk, Estado def) 
	{
		Random rand = new Random();
		if(atk.fazFronteiraCom(def.getNome())==true) 
		{
			if(atk.getQuantidade_de_Tropas()>3) 
			{
				System.out.println("Ataque Usando 3 Dados");
				int dados_atk[]= {0,0,0};
				for(int i=0;i<dados_atk.length;i++) 
				{
					dados_atk[i]=rand.nextInt(6)+1;
					
				}
			}
			else if(atk.getQuantidade_de_Tropas()==3) 
			{
				System.out.println("Ataque Usando 2 Dados ");
				int dados_atk[]= {0,0};
				
				for(int i=0;i<dados_atk.length;i++) 
				{
					dados_atk[i]=rand.nextInt(6)+1;
					
				}
			}
			else if(atk.getQuantidade_de_Tropas()==2) 
			{
				System.out.println("Ataque usando 1 Dado");
				int dados_atk=rand.nextInt(6)+1;
				
			}
			else 
			{
				System.out.println("Não há tropas sificientes para atacar");
			}	
		}
		else 
		{
			System.out.println("Nao faz fronteira");
		}
		
	}
	
	public void rodadaComum() throws Exception
	{
		Scanner scan = new Scanner(System.in).useDelimiter("\\r\n|\\s|\\-");
		Jogador_loop:
		for (int i = 0; i < Jogadores.size(); i++) {
			
			inicioDeTurno(Jogadores.get(i));
			/*if(Jogadores.get(i).getMao().length>=3) 
			{
				System.out.println("Deseja Trocar Cartas?");
				//trocar
			}*/
			System.out.println("Selecione o que deseja fazer: \r\n 1. Atacar"
					+ "\r\n 2. Mover Tropas"
					+ "\r\n 3.Encerrar");
			int escolha = scan.nextInt();
			switch(escolha) 
			{
			case 1:
				System.out.println("Indique Origem e Destino do Ataque:");
				String atk = scan.next();
				String def = scan.next();
				//System.out.println(atk+" "+def);
				Estado offense = Mapa.getEstado(atk);
				Estado deffense = Mapa.getEstado(def);
				atacar(offense,deffense);
				break;
			case 2:
				break;
			case 3:
				continue Jogador_loop;
				//break;
			default:
				System.out.println("esta nao e uma opção valida");
			}
			//ataque
			//movimentação de tropas
			//fim do turno
		}
	}
	
	public void inicioDeTurno(Jogador jogador) throws Exception {
		Scanner scan = new Scanner(System.in);
		int tropasDisponiveis = (int)Math.ceil(jogador.getQuantidadeTerritorios()/2.0);		
		boolean check = false;		
		String Escolhas[];
		Object n[];
//		Checando entradas
		do {
			check = false;
			System.out.println("Jogador de cor " + jogador.getCor() + ": Tem " + tropasDisponiveis + " tropas para distribuir:");
			Escolhas = scan.nextLine().replaceAll("\\s", "").split("-|\\,");
			n = new Object[Escolhas.length];
			int[] tropas;
			int maxTrops = 0;
			for (int i = 0; i < Escolhas.length;i += 2) {
				
				//System.out.println(Escolhas[i] + " " + Escolhas[i+1]);
				maxTrops += Integer.parseInt(Escolhas[i+1]);
				Estado Aux = Mapa.getEstado(Escolhas[i]);
				//System.out.println(Mapa.getEstado(Escolhas[i]).getDominante().getCor() + " - " + jogador.getCor());
				if (!(Mapa.getEstado(Escolhas[i]).getDominante().equals(jogador))) {
					System.out.println("Estado " + Aux.getNome() +" nao pertence a voce! Escolha novamente");
					check = true;
					break;
				}
				
				n[i] = Aux;
				n[i+1] = Escolhas[i+1];
				
			}
			if (maxTrops > tropasDisponiveis) {
				System.out.println("Quantidade de tropas excede as disponiveis. Escolha novamente");
				check = true;
			}
			
			
		} while (check);	
		
		for (int i = 0; i <  n.length; i+= 2) {
			Estado EstadoAux = (Estado)n[i];
			int tropasAux = Integer.parseInt((String) n[i+1]);
			EstadoAux.addTropas(tropasAux);
		}
		
		System.out.println("Fim da distribuicao de tropas do jogador " + jogador.getCor());
		
		
	
	}
	
	
    public void distribuirTropas(Scanner scan) throws Exception{
    	ArrayList<Regiao> AuxReg= Mapa.getRegioes();    	
        for (int i = 0; i < Jogadores.size();i++){
        	Jogador jogadorAux = Jogadores.get(i);
        	int tropas_disponiveis = (int)Math.ceil(Jogadores.get(i).getQuantidadeTerritorios()/2.0);
            System.out.println("Jogador " + (i+1) + " cor " + Jogadores.get(i).getCor() + ": Tem " + tropas_disponiveis + " tropas para distribuir:");
            	while(tropas_disponiveis>0) 
            	{
            		String auxEstado = scan.next();
            		int auxTropas = scan.nextInt();
            		if(auxTropas<=tropas_disponiveis) 
            		{
            			for(int j=0;j<AuxReg.size();j++) 
            			{
            				for(int k=0;k<AuxReg.get(j).getEstados().size();k++) 
            				{
            					Estado CurrentState = AuxReg.get(j).getEstados().get(k);
            					if(CurrentState.getNome().equals(auxEstado) && CurrentState.getDominante().equals(jogadorAux)) 
            					{
            						int tropas_atuais= CurrentState.getQuantidade_de_Tropas();
            						CurrentState.setQuantidade_de_Tropas(tropas_atuais+auxTropas);
            						tropas_disponiveis = tropas_disponiveis-auxTropas;
            						break;
            					}
            				}
            			}
            		}
            		else 
            		{
            			System.out.println("O jogador nao possui "+ auxTropas + " tropas disponiveis");
            			i--;
            			break;
            		}
            		
            	}
        }
    }
}

