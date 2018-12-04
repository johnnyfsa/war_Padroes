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
	
	public Tabuleiro Mapa= new Tabuleiro();
	private MoveController Mover = new MoveController(Mapa);
	private CombateController Combate = new CombateController();
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
		Collections.shuffle(Baralho);
	}
	//testes
	    
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
		Mapa.printMapa();
	}
	
	public void rodadaInicial() throws Exception {
		//ArrayList<Regiao> AuxReg= Mapa.getRegioes();	
		for (int i = 0; i < Jogadores.size(); i++) {
			inicioDeTurno(Jogadores.get(i),0);
		}	

		System.out.println("--------------------------------------------------------------" +
		"FIM DAS TELAS DE INICIALIZACAO DO JOGO" +
		"--------------------------------------------------------------");
	}
	
	public void trocarCartas(Jogador player, int tropasExtras) 
	{
		//exibe a mão
		this.exibirMao(player);
		//faz mais alguma coisa
		ArrayList<Carta> auxMao = player.getMao();
		boolean circulo3;
		boolean quadrado3;
		boolean triangulo3;
		boolean diferentes;
		Scanner scan = new Scanner(System.in);
		int c1 = scan.nextInt();
		int c2 = scan.nextInt();
		int c3 = scan.nextInt();
		
		
		
			
		
	}
	
	private void exibirMao(Jogador player) 
	{
		for(int i=0;i<player.getMao().size();i++) 
		{
			if(player.getMao().get(i).getTipo().equals("Carta Estado")) 
			{
				CartaEstado aux = (CartaEstado) player.getMao().get(i);
				System.out.println(i+": "+ aux.getForma().getTipoForma());
			}
			else if(player.getMao().get(i).getTipo().equals("Coringa")) 
			{
				System.out.println(i+": "+player.getMao().get(i).getTipo());
			}
		}
	}
	
	public void rodadaComum() throws Exception
	{
		
		Scanner scan = new Scanner(System.in).useDelimiter("\\r\n|\\s|\\-");
		//Jogador_loop
		for (int i = Jogadores.size()-1; i >= 0; i--) {
			//Calcular tropas extras e associar na variavel
			int tropasExtras = 0;
			int territorioInicial = Jogadores.get(i).getQuantidadeTerritorios();
			inicioDeTurno(Jogadores.get(i), tropasExtras);
			if(Jogadores.get(i).getMao().size()>=3) 
			{
				System.out.println("Deseja Trocar Cartas?");
				//trocar
			}
			else 
			{
				System.out.println("Não tem cartas pra trocar");
			}
			System.out.println("Jogador da vez: " + Jogadores.get(i).getCor() + ": Selecione o que deseja fazer: \r\n1 - Atacar\r\n2 - Mover Tropas\r\n3 - Encerrar");						
			int escolha = scan.nextInt();
			
			//ataque
			if(escolha == 1) {
				Combate.combateTropas(Jogadores.get(i), Mapa);				
				escolha = 2;
			}
			//movimentação de tropas
			if(escolha == 2) {
				Mover.moveTropas();			
			}
			
			if (Jogadores.get(i).getQuantidadeTerritorios() > territorioInicial) {
//				Ganha cartas
				
			}
			//checar se jogador ganhou territorio, pegar cartar, encerrar turno
			
			
			
			
			//fim do turno
		}
	}
	
	public void inicioDeTurno(Jogador jogador, int tropasExtras) throws Exception {
		Scanner scan = new Scanner(System.in);
		int tropasDisponiveis = ((int)Math.ceil(jogador.getQuantidadeTerritorios()/2.0)) + tropasExtras;		
		boolean check = false;		
		String Escolhas[];
		Object n[];		
//		Checando entradas
		do {
			printMapa();
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
			} else if(maxTrops < tropasDisponiveis) {
				System.out.println("Quantidade de tropas menor que as disponiveis. Escolha novamente");
				check = true;
			}			
			
		} while (check);	
		
		for (int i = 0; i <  n.length; i+= 2) {
			Estado EstadoAux = (Estado)n[i];
			int tropasAux = Integer.parseInt((String) n[i+1]);
			EstadoAux.addTropas(tropasAux);
		}
		
		System.out.println("Fim da distribuicao de tropas do jogador " + jogador.getCor());
		System.out.println("------------------------------------------------------------");
		
	}	
}

