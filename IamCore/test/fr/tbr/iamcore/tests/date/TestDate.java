package fr.tbr.iamcore.tests.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		
		
		System.out.println(parsedDate.after(date));
		System.out.println(parsedDate.before(date));
		
		System.out.println(date.getTime());
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.get(Calendar.MONTH);
		calendar.get(Calendar.DAY_OF_MONTH);
		calendar.get(Calendar.DAY_OF_WEEK);
		
		Date calendarDate = calendar.getTime();
		calendar.setTime(parsedDate);
		
		
		
	}

}
