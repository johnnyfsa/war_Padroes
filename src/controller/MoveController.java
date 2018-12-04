package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Estado;
import model.Tabuleiro;

public class MoveController {
	private Scanner scan;
	private Estado Origem;
	private Estado Destino;
	private int numTropas;
	ArrayList<Estado> Recebeu;
	private Tabuleiro Mapa;
	
	public MoveController(Tabuleiro Mapa) 
	{
		scan = new Scanner(System.in).useDelimiter("\\s|\\r\n|\\-");
		this.Mapa=Mapa;
		Recebeu = new ArrayList<Estado>();
	}
	public void leitura()
	{	
		
		System.out.println("Digite o estado de Origem, o Destino e a quatidade de tropas:\r\n");
		String Org = scan.next();
		String Dest = scan.next();
		numTropas = scan.nextInt();
		try {
			Origem= Mapa.getEstado(Org);
			Destino = Mapa.getEstado(Dest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void moveTropas()
	{	
		
		this.leitura();
		
		if(!Recebeu.isEmpty()) 
		 {
			 for(int i=0;i<Recebeu.size();i++) 
				{
					if(Recebeu.contains(Origem)) 
					{
						System.out.println("Esse Estado acabou de receber tropas, ele não pode ter tropas removidas");
						return;
					}
				} 
		 }
		if(Origem.getDominante().equals(Destino.getDominante())) 
		{
			if(numTropas>=Origem.getQuantidade_de_Tropas()) 
			{
				System.out.println("Número Inválido de tropas");
			}
			else 
			{
				if(Origem.fazFronteiraCom(Destino.getNome())) 
				{
					Origem.setQuantidade_de_Tropas(Origem.getQuantidade_de_Tropas()-numTropas);
					Destino.addTropas(numTropas);
					Recebeu.add(Destino);
				}
				else 
				{
					System.out.println("Os estados solicitados não fazem fronteira");
				}
			}
		}
		Scanner scanMove = new Scanner(System.in);
		System.out.println("Deseja mover tropas novamente?\r\n1 - Sim\r\n2 - Nao");
		int escolha = scanMove.nextInt();
		if (escolha == 1)
			this.moveTropas();
	}
	
	public Estado getOrigem() {
		return Origem;
	}
	public void setOrigem(Estado origem) {
		Origem = origem;
	}
	public Estado getDestino() {
		return Destino;
	}
	public void setDestino(Estado destino) {
		Destino = destino;
	}
}
