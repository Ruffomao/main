package planit;

import java.util.ArrayList;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateParser {

	public static String getStartDate(String str) {
		ArrayList<String> arr = Parser.toArrayList(str.trim().toLowerCase());
		int index = Parser.isDateTimePresent(str.trim());
		if (index != -1 && Parser.isTodayTmr(arr.get(index)) == 1) {
			return getTodayDate();
		} else if (index != -1 && Parser.isTodayTmr(arr.get(index)) == -1) {
			return getTmrDate();
		}
	}
	
	public static String getEndDate(String str) {
	}

	private static String getTodayDate() {
		LocalDateTime date = LocalDateTime.now();
		return date.toString(ParserConstants.FORMAT_DATE_STORAGE);
	}

	private static String getTmrDate() {
		LocalDateTime date = LocalDateTime.now();
		date = date.plusDays(ParserConstants.INT_ONE);
		return date.toString(ParserConstants.FORMAT_DATE_STORAGE);
	}
}