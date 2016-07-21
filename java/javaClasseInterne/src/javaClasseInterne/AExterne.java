package javaClasseInterne;

public class AExterne {
	
	private static int aex1 = 0;
	private int aex2;
	
	// classe interne statique
	// acces au membre statique privé
	// de la classe externe
	public static class AInterne1 {
		private int aint1;
		
		public AInterne1() {
			aint1 = 0;
		}
		public void test1() {
			//aex1 = 42;
			//aex2 = 5;
			System.out.println("aex1 = " + aex1++);
			System.out.println("aint1 = " + aint1++);
		}
	}
	
	public static void methodeDeAExterne() {
		// j'ai le doit d'instancier mes propres classes interne
		// même si elle sont privé
		AInterne2 a2 = new AInterne2();
	}
	
	// cette classe interne n'est visible que depuis AExterne
	private static class AInterne2 {
		public void test1() {
			
		}
	}
	
	public class AInterne3 {
		private int aint1;
		
		public AInterne3() {
			aint1 = 0;
		}
		public void test1() {
			//aex1 = 42;
			//aex2 = 5;
			System.out.println("aex1(variable statique externe) = " + aex1++);
			System.out.println("aex2(variable instance externe) = " + aex2++);
			System.out.println("aint1(variable interne) = " + aint1++);
		}
	}
	
	

}
