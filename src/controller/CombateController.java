package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import model.Estado;
import model.Jogador;
import model.Tabuleiro;

public class CombateController {

	public CombateController() {
			
	}
		
	public int setTropas(int quantidade) {
		if (quantidade >=3) {
			return 3;
		} else {
			return quantidade;
		}
	}	
	
	public boolean combateTropas(Jogador jogador, Tabuleiro mapa) throws Exception {
		boolean ret = false;
		System.out.println("Modo Combate");
		boolean podeAtacar = true;
		Scanner scan = new Scanner(System.in);
		String[] Escolhas;
		Estado origem, destino;
		mapa.printMapa();
		System.out.println("Jogador " + jogador.getCor() + ": Indique a origem do ataque e o destino do ataque");
		Escolhas = scan.nextLine().replaceAll("\\s", "").split("-");
		origem = mapa.getEstado(Escolhas[0]);
		destino = mapa.getEstado(Escolhas[1]);
		if (!(origem.getDominante().equals(jogador)) || destino.getDominante().equals(jogador)) {				
			System.out.println("Opcao de estado de origem ou destino invalidas.");			
			podeAtacar = false;
		} else if (!(origem.fazFronteiraCom(destino.getNome()))) {
			System.out.println("Estados devem fazer fronteira!");			
			podeAtacar = false;
		} else if (origem.getQuantidade_de_Tropas() == 1) {
			System.out.println("Eh necessario mais tropas para atacar!");
			podeAtacar = false;
		}		
//			Entra aqui l�gica para ataque!
		if (podeAtacar) {
			int tropasAtacantes = setTropas(origem.getQuantidade_de_Tropas()-1);
			int tropasDefensoras = setTropas(destino.getQuantidade_de_Tropas());
			
			ArrayList<Integer> dadosAtaque = new ArrayList<Integer>();					
			ArrayList<Integer> dadosDefesa = new ArrayList<Integer>();
					
			for (int i = 0; i < tropasAtacantes;i++) {
				Random rn = new Random();
				dadosAtaque.add(rn.nextInt(6)+1);			
			}
			
			for (int i = 0; i < tropasDefensoras;i++) {
				Random rn = new Random();
				dadosDefesa.add(rn.nextInt(6)+1);						
			}
			
			Collections.sort(dadosAtaque,Collections.reverseOrder());
			Collections.sort(dadosDefesa,Collections.reverseOrder());
			
			int combates = Math.min(dadosAtaque.size(), dadosDefesa.size());
					
			for (int i = 0; i < combates; i++) {
				System.out.println("Luta " + (i+1) + ": Ataque: " + dadosAtaque.get(i) + " vs " + dadosDefesa.get(i) + " :Defesa");
				if (dadosAtaque.get(i) <= dadosDefesa.get(i)) {
					System.out.println("Defesa venceu a luta " + (i+1));					
					origem.addTropas(-1);
				} else {
					System.out.println("Ataque venceu a luta " + (i+1));
					destino.addTropas(-1);
				}
				System.out.println("------------------------------");
			}
			
			if (destino.getQuantidade_de_Tropas() == 0) {
				System.out.println("Jogador " + jogador.getCor() + " ganhou o territ�rio " + destino.getNome());
				destino.getDominante().alteraTerritorio(-1);
				destino.setDominante(jogador);
				jogador.alteraTerritorio(1);
				ret = true;
				int move = 0;
				if ((origem.getQuantidade_de_Tropas()-1) >= 3) {
					move = 3;				
				} else {
					move = (origem.getQuantidade_de_Tropas()-1);
				}			
				boolean check = false;
				do {
					System.out.println("Quantas vai passar? (Entre 1 e " + move + ")");
					int escolha = scan.nextInt();
					if (escolha >= 1 && escolha <= move) {
						System.out.println("Movendo " + escolha + " de " + origem.getNome() + " para " + destino.getNome());
						destino.addTropas(escolha);
						origem.addTropas(-escolha);
						
						check = false;
					} else {
						System.out.println("Deve passar ao menos 1 tropa!");
						check = true;
					}			
				} while (check);
			}
						    
			mapa.printMapa();	
		}
				
		System.out.println("Jogador " + jogador.getCor() + ", voc� deseja atacar novamente?\r\n1 - Sim\r\n2 - N�o");
		int escolha = scan.nextInt();
		if (escolha == 1) {
			return combateTropas(jogador, mapa);
		} else {
		return ret;
	
		}
	}
	
	
}
