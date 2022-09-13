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
		dataDB = new HashMap<String, ArrayList<String>>();
	}

	public ArrayList<String> getListData( String key ) throws Exception {
		if ( false == dataDB.containsKey(key) )
			dataDB.put( key, FilesGetData.getListItems(
					DBGetDataConstants.DIR_COMMON + "\\" + key + ".txt" ) );
		
		return dataDB.get( key );
	}

	public ArrayList<String> getListThemes() throws FileNotFoundException, IOException {
		return FilesGetData.getListItems( DBGetDataConstants.DIR_THEMES + "\\"
					+ DBGetDataConstants.FILE_LIST_THEMES );
	}
	
	public static String getMessageListThemes() {
		return DBGetDataConstants.MESSAGE_LIST_THEMES;
	}
	
	public static String getMessageNotCorrentInput() {
		return DBGetDataConstants.MESSAGE_NOT_CORRECT_INPUT;
	}
	
	public static String getMessageGetThemeNumber() {
		return DBGetDataConstants.MESSAGE_THEME_NUMBER;
	}

	public static String getMessageNotCorrectThemeNumber() {
		return DBGetDataConstants.MESSAGE_NOT_CORRECT_THEME_NUMBER;
	}

	public ArrayList<String> getListVariants( String theme ) throws FileNotFoundException, IOException {
		return FilesGetData.getListItems( DBGetDataConstants.DIR_THEMES + "\\"
					+ theme + "\\" + theme + DBGetDataConstants.SUFFIX_VARIANTS_FILE );
	}

	public static String getMessageListVariants() {
		return DBGetDataConstants.MESSAGE_LIST_VARIANTS;
	}

	public static String getMessageGetVariantNumber() {
		return DBGetDataConstants.MESSAGE_VARIANT_NUMBER;
	}

	public static String getMessageNotCorrectVariantNumber() {
		return DBGetDataConstants.MESSAGE_NOT_CORRECT_VARIANT_NUMBER;
	}

	public ArrayList<String> getListTemplates(String theme, String variant) throws FileNotFoundException, IOException {
		return FilesGetData.getListItems( DBGetDataConstants.DIR_THEMES + "\\"
				+ theme + "\\" + variant + "\\" + variant + DBGetDataConstants.SUFFIX_PATTERNS_FILE );
	}

	public ArrayList<String> getListPatternForms(String theme, String variant, String pattern) throws FileNotFoundException, IOException {
		return FilesGetData.getListItems( DBGetDataConstants.DIR_THEMES + "\\"
				+ theme + "\\" + variant + "\\" + pattern + ".txt" );
	}

	public static String getMessageInputAnswerOrQuit() {
		return DBGetDataConstants.MESSAGE_INPUT_ANSWER_OR_QUIT;
	}

	public static Object getTextGetAnswer() {
		return DBGetDataConstants.USER_INPUT_GET_ANSWER;
	}

	public static String getMessageNotCorrectAnswer() {
		return DBGetDataConstants.MESSAGE_NOT_CORRECT_ANSWER;
	}

	public static String getMessageRepeatInputOrGetAnswerOrQuit() {
		return DBGetDataConstants.MESSAGE_REPEAT_INPUT_OR_GET_ANSWER_OR_QUIT;
	}

	public static String getMessageCompleteWorkWithPattern() {
		return DBGetDataConstants.MESSAGE_COMPLETE_WORK_WITH_PATTERN;
	}

	public static String getTextQuit() {
		return DBGetDataConstants.USER_INPUT_QUIT;
	}

	public static String getMessageGetPatternNumber() {
		return DBGetDataConstants.MESSAGE_GET_PATTERN_NUMBER;
	}

	public static String getMessageNotCorrectPatternNumber() {
		return DBGetDataConstants.MESSAGE_NOT_CORRECT_PATTERN_NUMBER;
	}

	public static String getMessageListPatterns() {
		return DBGetDataConstants.MESSAGE_LIST_PATTERNS;
	}

	public HashMap<String, String> getMapContractions() throws FileNotFoundException, IOException {
		return FilesGetData.getMapItems( DBGetDataConstants.DIR_COMMON + "\\"
				+ DBGetDataConstants.FILE_MAP_CONTRACTIONS );
	}

	public static String getLabelTheme() {
		return DBGetDataConstants.LABEL_GET_THEME;
	}

	public static String getLabelVariant() {
		return DBGetDataConstants.LABEL_GET_VARIANT;
	}

	public static String getLabelPattern() {
		return DBGetDataConstants.LABEL_GET_PATTERN;
	}

	public static String getLblSetCheckPatternMode() {
		return DBGetDataConstants.LABEL_SET_CHECK_PATTERN_MODE;
	}

	public static String getLblStopCheckPatternMode() {
		return DBGetDataConstants.LABEL_STOP_CHECK_PATTERN_MODE;
	}

	public static String getMessageDefaultTask() {
		return DBGetDataConstants.MESSAGE_DEFAULT_TASK;
	}

	public static String getMessageCorectAnswer() {
		return DBGetDataConstants.MESSAGE_CORRECT_ANSWER;
	}

	public static String getMessageNumberCorrectAnswers() {
		return DBGetDataConstants.MESSAGE_NUMBER_CORRECT_ANSWERS;
	}
}
