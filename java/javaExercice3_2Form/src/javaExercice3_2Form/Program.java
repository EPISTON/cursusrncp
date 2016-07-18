package javaExercice3_2Form;
//import java.util.Date;

import java.util.Random;

//import geometrie.Rectangle;
import geometrie.*;

public class Program {

	public static void main(String[] args) {
		//geometrie.Rectangle r1;
		
	//	Date d1;
	//	java.sql.Date d2;
		
		Rectangle r1 = new Rectangle(10, 8, 16, 12);
		System.out.println("aire de r1 = " + r1.getAire());
		r1.afficher();
		
		Random rd = new Random();
		Rectangle[] rectangles = new Rectangle[5];
		for (int i = 0; i < rectangles.length; i++) {
			rectangles[i] = new Rectangle(rd.nextInt(15),
											rd.nextInt(15),
											rd.nextInt(15),
											rd.nextInt(15));
			rectangles[i].getAire();
		}
		for (Rectangle r : rectangles) {
			System.out.println("(" + r.getX1() + ", " + r.getY1() + ", "
								+ r.getX2() + ", " + r.getY2() + ")");
			System.out.println("en collision ? " + Rectangle.collision(r1, r));
		}
		
		
	}

}
