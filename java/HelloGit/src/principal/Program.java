package principal;

public class Program {

	public static void main(String[] args) {
		System.out.println("bonjour git");
		System.out.println("et bon app�tit");

		IHello var1;
		var1 = new MyGreeting();
		var1.greetings("bob");
		
	}

}
