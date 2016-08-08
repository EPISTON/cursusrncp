package javaPatternFabrique1Form;

public class AppFactory {
	public final static String[] configTypes;
	
	static {
		configTypes = new String[]{"memory", "textFile", "database"};
	}

	public IConfigApp BuildConfig(String type, String parameters) {
		IConfigApp app = null;
		switch (type) {
			case "memory":
				// construction d'un MemoryConfig
				app = new MemoryConfigApp();
				String[] values = parameters.split(";");
				for (String value : values) {
					String keyvalue[] = value.split("=");
					app.setConfigValue(keyvalue[0], keyvalue[1]);
				}
				break;
			case "textFile":
				app = new TextConfigApp(parameters);
				app.load();
				break;
		}
		return app;
	}
}
