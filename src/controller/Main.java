package controller;
import model.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game Jogo = new Game();
		Jogo.criarMapa();
		System.out.println("Digite o número de jogadores");
		Scanner scan = new Scanner(System.in);
		int numJogadores = scan.nextInt();
		if(numJogadores<2||numJogadores>5) 
		{
			System.out.println("Número de Jogadores Inválido");
		}
		else 
		{
			Jogo.setNumJogadores(numJogadores);
			for(int i=1;i<= numJogadores;i++) 
			{
				System.out.println("Associe uma Cor ao jogador " + i);
				String Cor = scan.next();
				Jogador aux = new Jogador();
				Jogo.setCordoJogador(aux, Cor);
				Jogo.addJogador(aux);
			}
		}
	}

}
