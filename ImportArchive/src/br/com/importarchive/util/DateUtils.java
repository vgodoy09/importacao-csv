package br.com.importarchive.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	public static String formataData(Date date, String format){
		return Data(date, format, new Locale("pt", "BR"));
	}
	
	public static String Data(Date date, String format){
		return Data(date, format, new Locale("pt", "BR"));
	}
	
	public static String Data(Date date, String format, Locale locale) {
		SimpleDateFormat f1 = new SimpleDateFormat(format,locale);
		String strDate = f1.format(date);
		return strDate;
	}
}
