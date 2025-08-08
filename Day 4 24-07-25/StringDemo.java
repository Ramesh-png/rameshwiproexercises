package com.wipro.basic;

public class StringDemo {

	public static void main(String[] args) {
		//string is immutable
//		String str="Jayanta";	
//		String str1= new String("Jayanta");
//		if(str.equalsIgnoreCase(str1))
//		{
//			System.out.println("Same");
//		}
//		String str1=str;
//		str1="Amit";
//		if(str==str1)
//		{
//			System.out.println("Same");
//		}
		
		
		 StringBuilder sb= new StringBuilder("jay");
		 System.out.println(sb);
		 sb.reverse();
		 System.out.println(sb);
		

	}

}
