package com.wipro.basic;

public class EnumDemo {

	public static void main(String[] args) {
     
//		String [] days= {"Sun","Mon","Tues"};
//		
//		String day="sunday";
//		
//		if(day.equalsIgnoreCase(days[0]))
//		{
//			System.out.println("Sunday");
//		}
		
		enum Day {
			SUN,
			MON,
			TUE,
			WED,
			THU,
			FRI,
			SAT
		}
		
//		Day day=Day.Mon;
//		if(day==Day.Mon)
//		{
//			System.out.println("Monday");
//		}
		
		for(Day d:Day.values())
		{
			System.out.println(d);
		}
		

	}

}
