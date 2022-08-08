package ua.engexercises.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ExercisesModel {
	DBGetData dbData;
	ArrayList<String> listThemes;
	private CommonElements commonElements;
	public static Logger logger;
	private ProcessingPattern patternData;
	private PatternTask patternTask;
	
	public ExercisesModel( Logger logger ) {
		dbData = new DBGetData();
		listThemes = null;
		this.logger = logger;
		commonElements = new CommonElements( dbData );
		patternData = null;
		patternTask = null;
	}

	public ArrayList<String> getListThemes() throws FileNotFoundException, IOException {
		if ( null == listThemes )
			listThemes = dbData.getListThemes();
		
		return listThemes;
	}

	public int getThemesNumber() {
		return listThemes.size();
	}
	
	public String getThemeByIndex( int index ) {
		return listThemes.get(index);
	}

	public String getRandomTheme() {
		return listThemes.get( RandomNumber.getRandomNumber( listThemes.size() ) );
	}

	public ArrayList<String> getListVariants( String theme ) throws FileNotFoundException, IOException {
		return dbData.getListVariants( theme );
	}

	public ArrayList<String> getListTemplates(String theme, String variant) throws FileNotFoundException, IOException {
		return dbData.getListTemplates( theme, variant );
	}

	public void setProcessingPatternObject(String theme, String variant, String pattern) throws Exception {
		// 1. get list pattern forms
		ArrayList<String> patternForms = dbData.getListPatternForms( theme, variant, pattern );
		
		// 2. get random number
		int randomNumber = RandomNumber.getRandomNumber(patternForms.size());
		
		// 3. get random pattern form from list
		String[] patternStr = patternForms.get(randomNumber).split("\t");
		String patternTaskStr = patternStr[ 0 ];
		String patternAnswer = patternStr[ 1 ];
		logger.info( "pattern answer and task from pattern file: " + patternTaskStr + "\t" + patternAnswer );
		
		// 4. get ProcessingPattern object
		patternData = new ProcessingPattern.Builder().setAnswer( patternAnswer )
							.setTask( patternTaskStr ).setElements( commonElements )
							.build();
	}

	public PatternTask getPatternTask() throws Exception {
		patternTask = patternData.getTaskAndAnswer(); 
		return patternTask;
	}

	public boolean checkIsUserInputEqualToAnswer(String userInput) {
		String answer = patternTask.getAnswer().toLowerCase();
//		logger.info( "get answer from pattern task: " + answer );
		userInput = userInput.toLowerCase();
//		logger.info( "userInput: " + userInput );
		
		if ( answer.equals(userInput) )
			return true;
//		logger.info( "answer is not equal to userInput" );
		
		if ( answer.endsWith(".") &&
				answer.substring(0, answer.length() - 1).equals(userInput) )
			return true;
//		logger.info( "answer without point is not equal to userInput" );
//		logger.info( "answer without point: " + answer.substring(0, answer.length() - 1) );
		
		return false;
	}


}
