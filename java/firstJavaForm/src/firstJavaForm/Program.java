package firstJavaForm;

import java.io.Console;

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
		
		// les numériques flottant
		
		// flottant 64 bits
		double var6 = 3.1415;
		System.out.println("var6 = " + var6);
		var6 = 3.1415161718;
		System.out.println("var6 = " + var6);
		var6 = 3.141516171819202122;
		System.out.println("var6 = " + var6);
		
		// flottant 32 bits
		float var7 = 3.1415F;
		System.out.println("var7 = " + var7);
		var7 = 3.1415161718F;
		System.out.println("var7 = " + var7);
		var7 = 3.141516171819202122F;
		System.out.println("var7 = " + var7);
		
		// type boolean (true/false)
		
		boolean var8 = true;
		boolean var9 = false;
		System.out.println("var8 = " + var8);
		System.out.println("var9 = " + var9);
		
		System.out.println("var8 et var9 = " + (var8 && var9));
		System.out.println("var8 ou var9 = " + (var8 || var9));
		System.out.println("not var8 = " + (!var8));
		
		
		// type caractere
		
		char vara = 'c';
		System.out.println("vara = " + vara);
		
		
		// chaine de caractere
		String str1 = "hello";
		System.out.println("str1 = " + str1);
		System.out.println("longeur str1 = " + str1.length());
		String str2 = new String("bye bye");
		System.out.println("str2 = " + str2);
		
		String str3 = str1 + " " + str2;
		System.out.println(str3);
		
		String str4 = " lundi \n mardi";
		System.out.println(str4);
		System.out.print("un ");
		System.out.print("deux ");
		
		
		
	}

}
