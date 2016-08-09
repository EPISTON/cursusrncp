package javaPatternComposite;

public class Program {

	public static void main(String[] args) {
		MediaElement document = new MediaElement();
		document.addChild(new MediaText("bonjour"));
		document.addChild(new MediaImage("logo.png"));
		document.addChild(new MediaText(" chez OCP"));
		MediaParagraphe mp = new MediaParagraphe("<p>", "</p>");
		mp.addChild(new MediaText("hello world"));
		mp.addChild(new MediaImage("mascotte.jpg"));
		document.addChild(mp);
		
		document.afficher();
		System.out.println("---------------------------");
		mp.afficher();
	}

}
