package javaInterfaceObjet;

import java.util.Arrays;
import java.util.Random;

import banque.CompteLCL;
import banque.CompteSG;
import banque.ICompteBancaire;

public class Program {

	public static void main(String[] args) {
		
		CompteSG c1 = new CompteSG(123456, "FR1345435435", 400.0, "capitaine crab");
		CompteLCL c2 = new CompteLCL("CPT123456", 300.0, "FR789456123");

		System.out.println(c1);
		System.out.println(c2);
		
		transfert(c1, c2, 200.0);
		
		System.out.println(c1);
		System.out.println(c2);
	
		ICompteBancaire ic1 = c1;
		
		ICompteBancaire[] comptes = new ICompteBancaire[10];
		Random rd = new Random();
		for (int i = 0; i < comptes.length; i++) {
			if (rd.nextBoolean())
				comptes[i] = new CompteSG(	i,
											"FR33" + i,
											rd.nextDouble() * 1000.0,
											"bob" + i);
			else
				comptes[i] = new CompteLCL("CP" + i,
											rd.nextDouble() * 1000.0,
											"FR33" + i);
		}
		
		System.out.println("----------------------");
		for (ICompteBancaire ic : comptes)
			System.out.println(ic);
		
		Arrays.sort(comptes);
		
		System.out.println("----------------------");
		for (ICompteBancaire ic : comptes)
			System.out.println(ic);
		
		
	}
	
	
	public static boolean transfert(ICompteBancaire source,
									ICompteBancaire destination,
									double montant) {
		if (source.retirer(montant)) {
			destination.deposer(montant);
			return true;
		}
		return false;
	}
	
	

}
