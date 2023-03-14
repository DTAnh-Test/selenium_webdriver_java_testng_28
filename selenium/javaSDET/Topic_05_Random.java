package javaSDET;

import java.util.Random;

public class Topic_05_Random {

	public static void main(String[] args) {
		Random rand = new Random();
//		System.out.println(rand.nextInt(10));
		String emailAddress = "automation" + rand.nextInt(999)+"@gmail.com";
		System.out.println(emailAddress);
	}

} 
