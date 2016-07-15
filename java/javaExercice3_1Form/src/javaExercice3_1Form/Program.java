package javaExercice3_1Form;

public class Program {

	public static void main(String[] args) {
		Produit p1 = new Produit( "tie fighter", 45000.0, 3500.0);
		Produit p2 = new Produit( "X-Wing", 65000.0, 6500.0);
		Produit p3 = new Produit( "AT-AT walker", 75000.0, 8500.0);
		Produit p4 = new Produit( "lk", -75000.0, -8500.0);
		
		p1.afficher();
		p4.afficher();
		
		System.out.println(p2.getPrice(15));
	}

}
