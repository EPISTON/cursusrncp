package tableauJavaForm;

import java.util.Arrays;

public class Program {

	public static void main(String[] args) {
		
		// déclaration d'une variable tableau entier 
		int[] tableau1;
		// creation d'un tableau de 5 entiers
		// et affectation de celui-ci dans la variable tableau1
		tableau1 = new int[5];
		tableau1[0] = 12;
		tableau1[1] = 25;
		tableau1[2] = 18;
		tableau1[3] = -5;
		tableau1[4] = 42;
		
		System.out.println("case no 2 = " + tableau1[2]);
		System.out.println("longueur tableau = " + tableau1.length);

		System.out.println("---------------------------------------");
		for (int index = 0; index < tableau1.length; index++) {
			System.out.println(tableau1[index]);
		}
		
		System.out.println("----------------------------------------");
		// pour chaque entier du tableau1, répéter la boucle avec
		// le contenu de la case tableau dans la variable "valeur"
		for (int valeur : tableau1) {
			System.out.println(valeur);
		}
		
		System.out.println("-----------------------------------------");
		System.out.println(Arrays.toString(tableau1));
		
		//tableau1[5] = 12;
		
		// tableau litteral
		String[] tableau2 = {"lundi", "mardi", "mercredi", "jeudi", "vendredi"};
		
		System.out.println("----------------------------------");
		for (String jour : tableau2)
			System.out.println(jour);
		
		// autre syntaxe pour les tableaux litteral
		double[] tableau3;
		tableau3 = new double[]{4.5, 2.3, -5.6, 8.4};
		
		System.out.println(Arrays.toString(tableau3));
		// tri du tableau par ordre croissant
		Arrays.sort(tableau3);
		System.out.println(Arrays.toString(tableau3));
		
		// copyof permet de copier un tableau dans un nouveau tableau plus grand
		double[] tableau4 = Arrays.copyOf(tableau3, 6);
		System.out.println(Arrays.toString(tableau4));
		
		// attention, une affectation entre 2 variables type référence
		// comme des tableaux ou des objets
		// ne copie le tableau/objet lui-même
		// mais fait pointer la 2eme variable vers le même
		// exemplaire/instance de l'objet
		// utiliser Arrays.copyOf ou clone si on veut faire une véritable
		// copie
		int[] tab5 = {5, 10, 15};
		int[] tab6 = tab5;
		System.out.println("-----------------");
		System.out.println(Arrays.toString(tab5));
		System.out.println(Arrays.toString(tab6));
		tab6[1] = 42;
		System.out.println("-----------------");
		System.out.println(Arrays.toString(tab5));
		System.out.println(Arrays.toString(tab6));
		
		System.out.println("-------------------------");
		double[][] mat1 = new double[3][4];
		mat1[1][0] = 5.5;
		mat1[0][2] = 4.5;
		mat1[2][1] = 3.5;
		for (int ligne=0; ligne < mat1.length; ligne++) {
			double[] values = mat1[ligne];
			for (int colonne = 0; colonne < values.length; colonne++)
				System.out.print(values[colonne] + " ");
			System.out.println();
		}
		System.out.println("-------------------------");
		for (double[] values : mat1) {
			for (double d : values) {
				System.out.print(d + " ");
			}
			System.out.println();
		}
		
		String[][] villes = new String[3][];
		villes[0] = new String[2];
		villes[0][0] = "paris";
		villes[0][1] = "lyon";
		villes[1] = new String[3];
		villes[1][0] = "madrid";
		villes[1][1] = "barcelone";
		villes[1][2] = "seville";
		villes[2] = new String[3];
		villes[2][0] = "berlin";
		villes[2][1] = "hambourg";
		villes[2][2] = "cologne";
		
		System.out.println(villes[1][0]);
		
		double[][] mat2= {{2.2, 1.5, 3.3},
						  {3.5, 8.2, 3.3},
						  {-5.5, 7.7, 1.1}};
		
		
	}

}
