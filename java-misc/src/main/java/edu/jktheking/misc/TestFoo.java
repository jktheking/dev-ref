package edu.jktheking.misc;

public class TestFoo {
	
	public static void main(String[] args) {
		print(7);
	}

	
	private static void print(int n) {
		
		if(n==0) return;
		
		print(n-1);
		System.out.println(n);
	}
}
