package ua.engexercises.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DBGetData {

	HashMap<String, ArrayList<String>> dataDB;

	public DBGetData(HashMap<String, ArrayList<String>> dataDB) {
		this.dataDB = dataDB;
	}
	
	public DBGetData() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String> getListData( String key ) throws Exception {
		if ( false == dataDB.containsKey(key) )
			throw new Exception( "Cannot get DBData for key: " + key + "!!!" );
		
		return dataDB.get( key );
	}

	public ArrayList<String> getListThemes() throws FileNotFoundException, IOException {
		return FilesGetData.getListItems();
	}
	
	public static String getMessageListThemes() {
		return DBGetDataConstants.MESSAGE_LIST_THEMES;
	}
	
	public static String getListAppOptions() {
		return DBGetDataConstants.MESSAGE_APP_OPTIONS;
	}
	
	public static String getMessageNotCorrentInput() {
		return DBGetDataConstants.MESSAGE_NOT_CORRECT_INPUT;
	}
	
	public static String getMessageNotCorrectAppOption() {
		return DBGetDataConstants.MESSAGE_NOT_CORRECT_APP_OPTION;
	}

	public static String getThemeNumber() {
		return DBGetDataConstants.MESSAGE_THEME_NUMBER;
	}

	public static String getMessageNotCorrectThemeNumber() {
		return DBGetDataConstants.MESSAGE_NOT_CORRECT_THEME_NUMBER;
	}

	public String getRandomPattern(String theme) {
		return FilesGetData.getRandomPattern( theme );		
	}
}
