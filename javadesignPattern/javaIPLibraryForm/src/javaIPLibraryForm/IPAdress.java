package javaIPLibraryForm;

import java.util.Arrays;

public class IPAdress implements Comparable<IPAdress>
{
	private short[] parts;
	
	public IPAdress(String adress) {
		String[] champs = adress.split("[.]");
		parts = new short[4];
		parts[0] = Short.parseShort(champs[0]);
		parts[1] = Short.parseShort(champs[1]);
		parts[2] = Short.parseShort(champs[2]);
		parts[3] = Short.parseShort(champs[3]);		
	}
	public IPAdress(int a, int b, int c, int d) {
		parts = new short[4];
		parts[0] = (short)a;
		parts[1] = (short)b;
		parts[2] = (short)c;
		parts[3] = (short)d;
	}
	
	public IPAdress successor() {
		short[] newParts = parts.clone();
		for (int i = 3; i >= 0; i--) {
			// j'augmente de 1 la valeur
			newParts[i] = (short)(parts[i] + 1);
			if (newParts[i] <= 255)
				break;
			newParts[i] = 0;
		}
		return new IPAdress(newParts[0], newParts[1], newParts[2], newParts[3]);
	}
	
	public IPAdress predecessor() {
		short[] newParts = parts.clone();
		for (int i = 3; i >= 0; i--) {
			// je decremente la valeur de 1
			newParts[i] = (short)(parts[i] - 1);
			if (newParts[i] >= 0)
				break;
			newParts[i] = 255;
		}
		return new IPAdress(newParts[0], newParts[1], newParts[2], newParts[3]);
	}
	
	
	

	@Override
	public String toString() {
		return parts[0] + "." + parts[1] + "." + parts[2] + "." + parts[3];
	}
	
	@Override
	public int compareTo(IPAdress o) {
		for (int i = 0; i < parts.length; i++) {
			if (parts[i] > o.parts[i])
				return 1;
			if (parts[i] < o.parts[i])
				return -1;
		}
		return 0;
	}

	
	
}
