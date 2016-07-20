package javaException;

public class AgeException extends RuntimeException {
	
	public AgeException() {
		super("une valeur d'age non cohérente");
	}
}
