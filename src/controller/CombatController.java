package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import model.Estado;
import model.Jogador;
import model.Tabuleiro;

public class CombatController {

	public CombatController() {
		ArrayList<Integer> teste = new ArrayList();
		teste.add(1);
		teste.add(5);
		teste.add(2);
		teste.add(6);
		for (int i = 0; i < teste.size(); i++) {
			System.out.println(teste.get(i));
		}
		
		Collections.sort(teste,Collections.reverseOrder());
		
		for (int i = 0; i < teste.size(); i++) {
			System.out.println(teste.get(i));
		}	
	}
		
	public int setTropas(int quantidade) {
		if (quantidade >=3) {
			return 3;
		} else {
			return quantidade;
		}
	}
	
	public void combate(Jogador jogador, Tabuleiro mapa) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		String[] Escolhas;
		Estado origem, destino;
		boolean check;
		do {
			check = false;
			System.out.println("Indique a origem do ataque e o destino do ataque");
			Escolhas = scan.nextLine().replaceAll("\\s", "").split("-");
			origem = mapa.getEstado(Escolhas[0]);
			destino = mapa.getEstado(Escolhas[1]);
			if (origem.getDominante().equals(jogador) || !(destino.getDominante().equals(jogador))) {				
				System.out.println("Opcao de estado de origem ou destino invalidas.");
				check = false;
				break;
			}
			if (!(origem.fazFronteiraCom(destino.getNome()))) {
				System.out.println("Estados devem fazer fronteira!");
				check = false;
				break;
			}			
		
//			Entra aqui lógica para ataque!
			
			int tropasAtacantes = setTropas(origem.getQuantidade_de_Tropas()-1);
			int tropasDefensoras = setTropas(destino.getQuantidade_de_Tropas());
			
			ArrayList<Integer> dadosAtaque = new ArrayList<Integer>();					
			ArrayList<Integer> dadosDefesa = new ArrayList<Integer>();
			
			
			for (int i = 0; i < dadosAtaque.size();i++) {
				Random rn = new Random();
				dadosAtaque.add(rn.nextInt(6)+1);			
			}
			
			for (int i = 0; i < dadosDefesa.size();i++) {
				Random rn = new Random();
				dadosDefesa.add(rn.nextInt(6)+1);						
			}
			
			Collections.sort(dadosAtaque,Collections.reverseOrder());
			Collections.sort(dadosDefesa,Collections.reverseOrder());
			
			int combates = Math.min(dadosAtaque.size(), dadosDefesa.size());
			
			
			for (int i = 0; i < combates; i++) {
				System.out.println("Luta " + (i+1) + ": Ataque: " + dadosAtaque.get(i) + " vs " + dadosDefesa.get(i) + " Defesa");
				if (dadosAtaque.get(i) <= dadosDefesa.get(i)) {
					System.out.println("Defesa venceu a luta " + (i+1));					
				} else {
					System.out.println("Ataque venceu a luta " + (i+1));					
				}
				System.out.println("------------------------------");
			}
			
			
		} while (check);
		
		
		
		
		
	}
	
	
	
}
