package javaPatternFabrique1Form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextConfigApp implements IConfigApp {
	
	private Map<String,String> values;
	private String configFileName;
	
	public TextConfigApp(String configFileName) {
		values = new HashMap<String, String>();
		this.configFileName = configFileName;
	}
	
	@Override
	public String getConfigValue(String name) {
		return values.get(name);
	}

	@Override
	public void setConfigValue(String name, String value) {
		values.put(name, value);
	}

	@Override
	public void save() {
		try {
			PrintWriter pw = new PrintWriter(configFileName);
			// je parcours toutes mes valeur de config
			for (String cle : values.keySet()) {
				pw.println(cle + "=" + getConfigValue(cle));
			}
			pw.close();
		} catch (FileNotFoundException e) {e.printStackTrace();	}
	}

	@Override
	public void load() {
		try {
			Scanner reader = new Scanner(new File(configFileName));
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] champs = line.split("=");
				setConfigValue(champs[0], champs[1]);
			}
			reader.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}

}
