package javaIPLibraryForm;

public class Program {

	public static void main(String[] args) {
		IPAdress myip = new IPAdress(192, 168, 1, 10);
		System.out.println(myip);

		IPAdress myip2 = new IPAdress("192.168.1.60");
		System.out.println(myip2);
		
	/*	IPInterval int1 = new IPInterval(myip, myip2);
		System.out.println(int1);
		
		for (IPAdress ip : int1) {
			System.out.println(ip);
		}*/
		IPInterval int1 = new IPInterval(new IPAdress("192.168.4.10"), new IPAdress("192.168.5.50"));
		System.out.println(int1);
		
		for (IPAdress ip : int1) {
			System.out.println(ip);
		}
		
		
	}

}
