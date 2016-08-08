package javaPatternFabrique1Form;

import java.util.HashMap;
import java.util.Map;

public class MemoryConfigApp implements IConfigApp {

	private Map<String,String> values;
	
	
	public MemoryConfigApp() {
		values = new HashMap<>();
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
	public void save() {}

	@Override
	public void load() {}

}
