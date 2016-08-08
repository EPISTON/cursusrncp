package javaPatternFabrique1Form;

public class Program {

	public static void main(String[] args) {
		
		AppFactory factory = new AppFactory();
		
		IConfigApp cfg = factory.BuildConfig("memory", "langage=francais;savedir=svg");
		System.out.println(cfg.getConfigValue("langage"));
		
		IConfigApp cfg2 = factory.BuildConfig("textFile", "appcfg.txt");
		System.out.println(cfg2.getConfigValue("langage"));
		
	}

}
