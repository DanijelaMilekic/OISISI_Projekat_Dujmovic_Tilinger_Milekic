package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Formating {

	public static String formatList(Object[] list) {
		StringBuilder sb = new StringBuilder();

		if (list != null && list.length > 0) {
			sb.append(list[0].toString());
		}

		for (int i = 1; i < list.length; i++) {
			sb.append(";");
			sb.append(list[i].toString());
		}

		return sb.toString();
	}
	
	public static List<String> toList(String line) {
		String[] tokens = line.split(";");
		List<String> list = new ArrayList<>();
		for (String string : tokens) {
			list.add(string);
		}
		return list;
	}

	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		return sdf.format(date);
	}
	
	public static Date parseDate(String token) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		try {
			return sdf.parse(token);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	//za proveravanje formata datuma
	public static boolean checkFormat(String token) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		try {
			sdf.parse(token);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	//proveravamo da li je integer
	public static boolean checkNumber(String token) {
		try {
			Integer.parseInt(token);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
		
	}
}
