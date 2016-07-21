package javaExercice6Form;

public class TemperatureException extends RuntimeException {
	
	private double temp;
	public double getTemp() {return temp;}


	public TemperatureException(double temp) {
		super("temperature trop basse");
		this.temp = temp;
	}
	
}
