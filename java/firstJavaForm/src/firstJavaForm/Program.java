package firstJavaForm;

public class Program {

	public static void main(String[] args) {
		// commentaire
		/*
		 * bloc de commentaire
		*/
		System.out.println("bonjour monde");
		
		System.out.println("il fait plus frais aujourd'hui");
		
		// variables numériques
		
		// entiers
		
		int var1; // declaration d'un entier 32 bits
		var1 = 42;
		System.out.println("var1 = " + var1);
		
		
		long var2 = 145; // entier 64 bits
		System.out.println("var2 = " + var2);
		
		long var3 = 8000000000L;
		System.out.println("var3 = " + var3);
		
		short var4 = 25000; // entier 16 bits
		System.out.println("var4 = " + var4);
		
		byte var5 = 112; // entier 8 bits -> un octet
		System.out.println("var5 = " + var5);
		
		// + addition
		// * multiplication
		// / division
		// - soustraction
		// % modulo (reste de la division entiere)
		
		// les variables en java sont fortement typées
		
		// les conversion peuvent etre soit implicite, soit explicite
		
		// les conversions implicite sont celles qui sont sure de réussir
		// sans perte de données
		
		// short -> int
		var1 = var4; // implicite
		System.out.println(var1);
		
		// les conversion explicite peuvent echouer (mal convertir)
		// int -> short 
		var4 = (short)var1;
		
		System.out.println(var4);
		
		
		
		
		
		
		
		
	}

}
