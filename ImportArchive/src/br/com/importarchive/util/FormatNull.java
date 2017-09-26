package br.com.importarchive.util;

public class FormatNull {

	public static String String(Object object) {
		if(object == null) 
			return null;
		if(object instanceof String || object instanceof Number)
			return object.toString().isEmpty() ? null : object.toString(); 
		return null;
	}
	
	public static Integer Integer(Object object) {
		if(object == null) 
			return null;
		if(object instanceof Number) 
			return ((Number) object).intValue();
		return null;
	}
	
	public static Double Double(Object object) {
		if(object == null) 
			return null;
		if(object instanceof Number) 
			return ((Number) object).doubleValue();
		return null;
	}
	
	public static Boolean Boolean(Object object) {
		if(object == null)
			return null;
		if(object instanceof Number)
			return ((Number)object).intValue() == 1 ? true : false;
		return null;
	}
}
