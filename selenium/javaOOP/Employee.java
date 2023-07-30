package javaOOP;

public class Employee extends Person implements People {
	
	// Class kế thừa class: extends (Cùng cấp)
	// Class kế thừa interface: implements (khác cấp)
	// 1 class kế thừa 1 class, nhưng kế thừa được nhiều interface
	// Interface kế thừa interface: extends (Cùng cấp)
	// Không có interface kế thừa class
	public void work() {
		System.out.println(hairColor);
		System.out.println(eyeColor);
		eat();
	}
	public static void main(String[] args) {
		
	}
}
