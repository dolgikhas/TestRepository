package mny.checkwords.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import mny.checkwords.model.Constants;
import mny.checkwords.model.DateAndTime;
import mny.checkwords.model.ResultCheck;

public class CheckWordsLog {
	final static String logFile = "log.txt";
	
	public static void initializeLogFile(String fileWords) throws IOException {
		FileWriter fileWriter = new FileWriter(Constants.PATH_LISTS_WORDS + logFile, true);
		BufferedWriter fileBufferWriter = new BufferedWriter(fileWriter);
		fileBufferWriter.write("\n\n\n===========================================================================\n" +
				"\t\tпроверка файла " + fileWords + ". " + DateAndTime.getCurrentDate() + " " +
				DateAndTime.getCurrentTime() + "\n");
		fileBufferWriter.close();
	}
	
	public static void writeToLogFile(String word, ResultCheck resultCheck) throws IOException {
		FileWriter fileWriter = new FileWriter(Constants.PATH_LISTS_WORDS + logFile, true);
		BufferedWriter fileBufferWriter = new BufferedWriter(fileWriter);
		String result = getSymbolFromResultCheck(resultCheck);
		fileBufferWriter.write(word + "\t" + result + "\n");
		fileBufferWriter.close();
	}

	private static String getSymbolFromResultCheck(ResultCheck result) {
		if (ResultCheck.Minus == result) {
			return "-";
		} else if (ResultCheck.Plus == result) {
			return "+";
		} else if (ResultCheck.Minus4 == result) {
			return "-4";
		} else if (ResultCheck.Equal == result) {
			return "=";
		} else if (ResultCheck.Asterisk == result) {
			return "*";
		} else if (ResultCheck.Doubt == result) {
			return "d";
		}

		return null;
	}
	
	public static void writeToLogFile(String text) throws IOException {
		FileWriter fileWriter = new FileWriter(Constants.PATH_LISTS_WORDS + logFile, true);
		BufferedWriter fileBufferWriter = new BufferedWriter(fileWriter);
		fileBufferWriter.write(text + "\n");
		fileBufferWriter.close();
	}

}
