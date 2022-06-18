package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return sdf.format(date);
	}
	
	public static Date parseDate(String token) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		try {
			return sdf.parse(token);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
