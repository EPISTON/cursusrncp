package geometrie;

public class Rectangle {
	public static final int X1_DEFAUT = 1;
	public static final int Y1_DEFAUT = 1;
	public static final int X2_DEFAUT = 2;
	public static final int Y2_DEFAUT = 2;
	public static final char BORD_STYLE = '#';
	public static final char CENTRE_STYLE = 'O';
	
	private int x1, y1, x2, y2;

	public int getX1() {return x1;}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {return y1;}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getX2() {return x2;}
	public void setX2(int x2) {
		// this.x2 = (x2 > x1)? x2 : x1;
		this.x2 = x2;
	}
	public int getY2() {return y2;}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	// echange les coordonnées si elle ne sont pas bien alignées
	private void check_coordinate() {
		if (x2 < x1) {
			int x = x1;
			x1 = x2;
			x2 = x;
		}
		if (y2 < y1) {
			int y = y1;
			y1 = y2;
			y2 = y;
		}
	}
	
	public Rectangle() {this(X1_DEFAUT, Y1_DEFAUT, X2_DEFAUT, Y2_DEFAUT);}
	public Rectangle(int x1, int y1, int x2, int y2) {
		setX1(x1);
		setY1(y1);
		setX2(x2);
		setY2(y2);
	}
	
	public int getAire() {
		check_coordinate();
		return (x2 - x1) * (y2 - y1);
	}
	
	public void  afficher() {
		check_coordinate();
		for (int ligne = y1; ligne < y2; ligne++) {
			for (int colonne = x1; colonne < x2; colonne++) {
				if (ligne == y1 /* bord haut */ 
					|| ligne == y2 - 1 /*bord bas */
					|| colonne == x1 /* bord gauche*/
					|| colonne == x2 - 1 /* bord droit */) {
					System.out.print(BORD_STYLE);
				}
				else
					System.out.print(CENTRE_STYLE);
			}
			System.out.println();
		}
	}
	
	public static boolean collision(Rectangle r1, Rectangle r2) {
		r1.check_coordinate();
		r2.check_coordinate();
		// si r2 est a gauche de r1 (r2.x2 < r1.x1), pas de collision
		if (r2.getX2() < r1.getX1())
			return false;
		if (r1.getX2() < r2.getX1()) // r2 est a droite de r1
			return false;
		if (r2.getY2() < r1.getY1()) //r2 est au dessus de r1
			return false;
		if (r1.getY2() < r2.getY1()) // r2 est en dessous de r1
			return false;
		
		return true;
	}
	
	
	
}
