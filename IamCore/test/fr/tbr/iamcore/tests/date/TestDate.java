package fr.tbr.iamcore.tests.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	
	public static void main(String... args) throws ParseException{
		Date date = new Date();
		System.out.println(date);
		
		SimpleDateFormat sdfDateOnly = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		
		System.out.println(sdfDateOnly.format(date));
		System.out.println(sdfDateTime.format(date));
		
		
		Date parsedDate = sdfDateTime.parse("06/01/2016 19:00:52");
		System.out.println(parsedDate);
		
	}

}
