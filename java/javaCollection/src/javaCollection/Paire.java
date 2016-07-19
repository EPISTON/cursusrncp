package javaCollection;

// j'indique que ma classe Paire recois 2 types en parametres
// le type T1 et le type T2
public class Paire <T1, T2>
{
	// j'utilise T1 et T2 comme des types classiques
	// dans ma classe
	// ils seront remplacée par les véritables types une fois choisis
	private T1 valeur1;
	private T2 valuer2;
	
	public T1 getValeur1() {return valeur1;}
	public void setValeur1(T1 valeur1) {this.valeur1 = valeur1;}
	public T2 getValuer2() {return valuer2;}
	public void setValuer2(T2 valuer2) {this.valuer2 = valuer2;}
	
	public Paire(T1 v1, T2 v2) {
		setValeur1(v1);
		setValuer2(v2);
	}
	
	@Override
	public String toString() {
		return "Paire [valeur1=" + valeur1 + ", valuer2=" + valuer2 + "]";
	}
	
	
}
