package br.com.prowayflix;

import java.util.Scanner;

public class Utils {
	
	public static boolean Perguntar(String pergunta) {
		Scanner scan= new Scanner(System.in);
		System.out.println(pergunta+" (S/N)");
		String resposta=scan.next();
		return resposta.equalsIgnoreCase("S"); 
	}
	
	

}
