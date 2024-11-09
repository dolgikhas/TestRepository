package mny.checkwords.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateAndTime {
	public static String getFileWordsWithDateAndTime(String fileWords) {
//		String currentDate = getCurrentDate();
//		String currentTime = getCurrentTime();
//		String fileWordsWithDateAndTime = fileWords.substring(0, fileWords.length() - 4) + "_" +
//					currentDate + "_" + currentTime + ".txt";
//		return fileWordsWithDateAndTime;
		return fileWords;
	}

	public static String getCurrentTime() {
		LocalTime timeNow = LocalTime.now();
		String hour = getTwoRangeStringNumber(timeNow.getHour());
		String minute = getTwoRangeStringNumber(timeNow.getMinute());
		String second = getTwoRangeStringNumber(timeNow.getSecond());

		return hour + minute + second;
	}

	public static String getCurrentDate() {
		LocalDate dateNow = LocalDate.now();
		int year = dateNow.getYear() % 100;
		int nMonth = dateNow.getMonthValue();
		String strMonth = getTwoRangeStringNumber(nMonth);
		int nDay = dateNow.getDayOfMonth();
		String strDay = getTwoRangeStringNumber(nDay);
		
		return "" + year + strMonth + strDay;
	}

	private static String getTwoRangeStringNumber(int intNumber) {
		String strNumber;
		if (10 > intNumber) {
			strNumber = "0" + Integer.toString(intNumber);
		} else {
			strNumber = Integer.toString(intNumber);
		}
		return strNumber;
	}
}
