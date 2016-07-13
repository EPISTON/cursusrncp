package javaExercice2_3Form;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Scanner lecteur = new Scanner(System.in);
		System.out.println("nombre a traduire ? ");
		int nombre = Integer.parseInt(lecteur.nextLine());
		afficheNombreRomain(nombre);
	}
	
	
	public static void afficheNombreRomain(int nombre) {
		String[] unites = {"I", "V", "X" };
		String[] dizaines = {"X", "L", "C" };
		String[] centaines = {"C", "D", "M" };
		
		StringBuilder sb = new StringBuilder();
		sb.append(afficheChiffreRomain(nombre / 100 % 10, centaines));
		sb.append(afficheChiffreRomain(nombre / 10 % 10, dizaines));
		sb.append(afficheChiffreRomain(nombre  % 10, unites));
		System.out.println(sb.toString());
	}
	
	public static String afficheChiffreRomain(int chiffre, String[] sigles) {
		switch(chiffre) {
			case 1: return sigles[0];
			case 2: return sigles[0] + sigles[0];
			case 3: return sigles[0] + sigles[0] + sigles[0];
			case 4: return sigles[0] + sigles[1];
			case 5: return sigles[1];
			case 6: return sigles[1] + sigles[0];
			case 7: return sigles[1] + sigles[0] + sigles[0];
			case 8: return sigles[1] + sigles[0] + sigles[0] + sigles[0];
			case 9: return sigles[0] + sigles[2];
		}
		return "";
	}

}
