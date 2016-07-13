package chaineCaracterejavaForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner lecteur = new Scanner(System.in);
		
		String str1 = "bonjour";
		String str2 = str1;
		str2 = str2.toUpperCase();
		System.out.println(str1);
		System.out.println(str2);
/*
		// illustration des problemes de performance
		// liés aux chaines
		String buffer = "";
		for (int i = 0; i < 40000; i++) {
			buffer += "log no\n"+ i;
			if (i % 1000 == 0)
				System.out.println("log ligne no " + i);
		}
		System.out.println("fini!");
		lecteur.nextLine();
		
		// utiliser stringbuilder pour les traitements lourds
		// de construction chaine
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 40000; i++) {
			sb.append("log no\n"+ i);
			if (i % 1000 == 0)
				System.out.println("log ligne no " + i);
		}
		String result = sb.toString();
		System.out.println("fini2!");
		*/
		String strA = "bonjour";
		String strB = "au revoir";
		String strC = "bon";
		strC += "jour";
		
		System.out.println("strA == strC -> " + (strA == strC));
		System.out.println("strA == strB -> " + (strA == strB));
		System.out.println("strA equals strC -> " + (strA.equals(strC)));
		System.out.println("strA equels strB -> " + (strA.equals(strB)));
		
		// exemple de lecture de fichier texte
		// je pointe vers un fichier
		File f = new File("sample.txt");
		if (f.exists()) {
			// j'ouvre un scanner pour lire le fichier
			Scanner reader = new Scanner(f);
			// tant qu'il reste des lignes a lire
			while(reader.hasNextLine()) {
				// afficher la prochaine ligne du fichier
				System.out.println(reader.nextLine());
			}
			reader.close();
		}
		
	}

}
