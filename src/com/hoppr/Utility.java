package com.hoppr;

public class Utility {

	public static void printOutput(String output){
		System.out.println(output);
	}
	
	public static boolean isNumber(String string){
		boolean isNumber=false;
		try{	
			if(Double.valueOf(string).isNaN()){
				//string is not a number
				isNumber=false;
			}
			else{
				//string is a number
				isNumber=true;
			}
		}catch(NumberFormatException e){
			//string is not a number
			isNumber=false;
		}
		return isNumber;
	}
}
