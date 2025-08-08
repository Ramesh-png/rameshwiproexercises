package com.wipro.basic;

public class Confitional {
	
	// ctrl + space
	public static void main(String[] args) {
		
//		boolean flag=true;
//		
//		if(flag==true)
//		{
//			System.out.print("True-");
//			int x=0;
//			x=x+1;
//			System.out.print(x);
//			
//			
//		}
//		else
//		{
//			System.out.println("False");
//		}
		
		char gender='F';
		
		switch(gender)
		{
		case 'M':
			System.out.println("Male");
			break;
			
		case 'F':
			System.out.println("Female");
			break;
		case 'O':
			System.out.println("Other");
			break;
		default:
			System.out.println("Unknown");
			break;
		
		}
		
		
//		if(gender=='M')
//		{
//			System.out.println("Male");
//		}
//		else if(gender=='F')
//		{
//			System.out.println("Female");
//		}
//		else if(gender=='O')
//		{
//			System.out.println("Other");
//		}
//		else
//		{
//			System.out.println("Unknown");
//		}
		
		
	}

}
