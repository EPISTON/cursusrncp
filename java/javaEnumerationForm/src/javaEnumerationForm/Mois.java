package javaEnumerationForm;

public enum Mois {
	JANVIER (1),
	FEVRIER (2),
	MARS (3),
	AVRIL(4);
	
	private int numero;

	Mois(int numero) {
		this.numero = numero;
	}
	public int getNumero() {
		return this.numero;
	}
}
