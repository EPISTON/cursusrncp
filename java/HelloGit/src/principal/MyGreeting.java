package principal;

public class MyGreeting implements IHello {

	@Override
	public void greetings(String nom) {
		System.out.println("greetings " + nom);

	}

}
