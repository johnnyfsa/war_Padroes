package model;

public class CartaEstado extends Carta {
	private Estado Estado;
	private Forma Forma;

	public CartaEstado() {
		this.setTipo("Carta Estado");
	}
	
	public Estado getEstado() {
		return Estado;
	}

	public void setEstado(Estado estado) {
		Estado = estado;
	}

	public Forma getForma() {
		return Forma;
	}

	public void setForma(Forma forma) {
		Forma = forma;
	}

}
