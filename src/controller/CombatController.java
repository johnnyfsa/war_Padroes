package controller;

import java.util.ArrayList;
import java.util.Collections;
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
//			System.out.println(teste.get(i));
		}
		
		Collections.sort(teste,Collections.reverseOrder());
		
		for (int i = 0; i < teste.size(); i++) {
//			System.out.println(teste.get(i));
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
		
		} while (check);
		
		
		
		
		
	}
	
	
	
}
