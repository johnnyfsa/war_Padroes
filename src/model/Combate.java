package model;

public class Combate {
	private Tabuleiro Tabuleiro;
	private Jogador Jogadores[];
	private int Num_tropas_atk;
	private int Num_tropas_def;
	private Jogador Atacante;
	private Jogador Defensor;
	private int Valor_atk;
	private int Valor_def;
	
	public Tabuleiro getTabuleiro() {
		return Tabuleiro;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		Tabuleiro = tabuleiro;
	}
	public Jogador[] getJogadores() {
		return Jogadores;
	}
	public void setJogadores(Jogador jogadores[]) {
		Jogadores = jogadores;
	}
	public int getNum_tropas_atk() {
		return Num_tropas_atk;
	}
	public void setNum_tropas_atk(int num_tropas_atk) {
		Num_tropas_atk = num_tropas_atk;
	}
	public int getNum_tropas_def() {
		return Num_tropas_def;
	}
	public void setNum_tropas_def(int num_tropas_def) {
		Num_tropas_def = num_tropas_def;
	}
	public Jogador getAtacante() {
		return Atacante;
	}
	public void setAtacante(Jogador atacante) {
		Atacante = atacante;
	}
	public Jogador getDefensor() {
		return Defensor;
	}
	public void setDefensor(Jogador defensor) {
		Defensor = defensor;
	}
	public int getValor_atk() {
		return Valor_atk;
	}
	public void setValor_atk(int valor_atk) {
		Valor_atk = valor_atk;
	}
	public int getValor_def() {
		return Valor_def;
	}
	public void setValor_def(int valor_def) {
		Valor_def = valor_def;
	}

}
