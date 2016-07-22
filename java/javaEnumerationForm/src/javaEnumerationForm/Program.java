package javaEnumerationForm;

public class Program {

	public static void main(String[] args) {
		
		Langage l = Langage.JAVA;
		
		if (l == Langage.JAVA) {
			System.out.println("felicitation!");
		}
		else {
			System.out.println("dommage");
		}
		System.out.println(l);
		System.out.println(l.ordinal());
		l = Langage.LISP;
		System.out.println(l);
		System.out.println(l.ordinal());
		
		Mois m = Mois.FEVRIER;
		
		switch (m) {
			case JANVIER:
				System.out.println("le debut de l'année");
				break;
			case FEVRIER:
				System.out.println("vive le ski");
				break;
		}
		System.out.println(m);
		System.out.println(m.getNumero());
		
	}

}
