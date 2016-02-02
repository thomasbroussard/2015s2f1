package fr.tbr.iamcore.tests;

public class TestArrays {
	
	
	public static void Main(String[] args){
		String[] directArray = {"red","green","blue"};
		String[][] directArrayOfArray = {{"red","car"},{"blue","boat"},{"green","bicycle"}};
		

		for (int i = 0; i < directArray.length; i++) {
			System.out.println(directArray[i]);
		}
		
		for (String string : directArray) {
			System.out.println(string);
		}
		
	}

}
