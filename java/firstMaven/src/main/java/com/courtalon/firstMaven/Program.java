package com.courtalon.firstMaven;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.courtalon.produitLib.Produit;

public class Program {

	public static void main(String[] args) {
		System.out.println("bonjour Maven");
		CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';').withRecordSeparator('\n');
		
		try {
			FileWriter writer = new FileWriter("export.csv");
			CSVPrinter  printer = new CSVPrinter(writer, format);
			printer.printRecord("chaise; design", 45.99, 2.5);
			printer.printRecord("table tek noire", 55.99, 3.5);
			printer.printRecord("lit baldaquin reine des neige", 1445.99, 55.5);
			
			printer.close();
			
		} catch (IOException e) {e.printStackTrace();}
		
		Produit p = new Produit("chaise ikea", 9.99, 5.5);
		
		
		
	}

}
