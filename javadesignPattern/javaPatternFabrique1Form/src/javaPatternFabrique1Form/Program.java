package javaPatternFabrique1Form;

import java.util.Date;

public class Program {

	public static void main(String[] args) {
		
		AppFactory factory = new AppFactory();
		
		IConfigApp cfg = factory.BuildConfig("memory", "langage=francais;savedir=svg");
		System.out.println(cfg.getConfigValue("langage"));
		
		IConfigApp cfg2 = factory.BuildConfig("textFile", "appcfg.txt");
		System.out.println(cfg2.getConfigValue("langage"));
		
		StringBuilder sb = new StringBuilder();
		sb.append("com.mysql.jdbc.Driver;");
		sb.append("jdbc:mysql://localhost:3306/base_config;");
		sb.append("root;");
		IConfigApp cfg3 = factory.BuildConfig("database", sb.toString());
		cfg3.setConfigValue("lastUpdated", (new Date()).toString());
		System.out.println(cfg3.getConfigValue("langage"));
		cfg3.save();
	}

}
