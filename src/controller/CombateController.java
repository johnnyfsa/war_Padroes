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
	
	public void combateTropas(Jogador jogador, Tabuleiro mapa) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		String[] Escolhas;
		Estado origem, destino;
	
		System.out.println("Jogador " + jogador.getCor() + ": Indique a origem do ataque e o destino do ataque");
		Escolhas = scan.nextLine().replaceAll("\\s", "").split("-");
		origem = mapa.getEstado(Escolhas[0]);
		destino = mapa.getEstado(Escolhas[1]);
		if (!(origem.getDominante().equals(jogador)) || destino.getDominante().equals(jogador)) {				
			System.out.println("Opcao de estado de origem ou destino invalidas.");			
			return;
		}
		if (!(origem.fazFronteiraCom(destino.getNome()))) {
			System.out.println("Estados devem fazer fronteira!");			
			return;
		}			
		if (origem.getQuantidade_de_Tropas() == 1) {
			System.out.println("Eh necessario mais tropas para atacar!");
			return;
		}		
//			Entra aqui lógica para ataque!
		
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
			System.out.println("Jogador " + jogador.getCor() + " ganhou o território e moveu uma tropa para " + destino.getNome());
			destino.setDominante(jogador);
			origem.addTropas(-1);
			destino.setQuantidade_de_Tropas(1);
			int move = 0;
			if ((origem.getQuantidade_de_Tropas()-1) > 3) {
				move = 3;				
			} else {
				move = (origem.getQuantidade_de_Tropas()-1);
			}
			System.out.println("Jogador " + jogador.getCor() + " pode transferir de 0 até " + move);
//			deseja passar?
		}
					    
		mapa.printMapa();	
		
		
	}
	
	// recursao entra aqui
	
	
}
