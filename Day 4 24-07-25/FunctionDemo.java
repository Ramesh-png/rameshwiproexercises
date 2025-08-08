package com.wipro.basic;

public class FunctionDemo {

	public static void main(String[] args) {
		 
		boolean ret=isEven(4);
		System.out.println(ret);
		
		boolean ret1=isEven(45);
		System.out.println(ret1);

	}
	
	static boolean isEven(int num)
	{
//		if(num%2==0)
//		{
//			return true;
//		}else
//		{
//			return false;
//		}
		
		return num%2==0?true:false;
	}
	
	 

}
