package ua.engexercises.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ua.engexercises.model.DBGetData;
import ua.engexercises.model.ExercisesModel;
import ua.engexercises.model.PatternTask;
import ua.engexercises.model.ProcessingPattern;
import ua.engexercises.model.RandomNumber;
import ua.engexercises.view.ExercisesView;

public class ExercisesController {
	ExercisesModel model;
	ExercisesView view;
	Scanner scanner;
	private static Logger logger;
	public static final int NUMBER_PROCESS_PATTERNS = 10;

	public ExercisesController(ExercisesModel model, ExercisesView view, Logger logger ) {
		this.model = model;
		this.view = view;
		this.logger = logger;
		scanner = new Scanner(System.in);
	}

	public void processingUser() throws FileNotFoundException, IOException {

		// get theme by user chose
		String theme, variant, pattern;
		
		try {
			theme = getThemeByUserInput();
			variant = getVariantByUserInput( theme );
			pattern = getRandomPatternByVariant( theme, variant );
			
			model.setProcessingPatternObject( theme, variant, pattern );
	
			String userInput = "";
			int correctAnswers = 0;
			while ( (correctAnswers < NUMBER_PROCESS_PATTERNS) &&
					!userInput.toLowerCase().equals( DBGetData.getTextQuit() ) )
			{
				PatternTask patternTask = model.getPatternTask();
				view.printMessage( DBGetData.getMessageInputAnswerOrQuit() );
				view.printMessage( patternTask.getTask() + ": " );
				
				userInput = scanner.nextLine();
				
				while ( !userInput.toLowerCase().equals(patternTask.getAnswer())
					 && !userInput.toLowerCase().equals(DBGetData.getTextGetAnswer())
					 && !userInput.toLowerCase().equals(DBGetData.getTextQuit()) )
				{
					view.printMessage( DBGetData.getMessageNotCorrectAnswer() );
					view.printMessage( "¬ведено: \"" + userInput + "\"");
					view.printMessage( DBGetData.getMessageRepeatInputOrGetAnswerOrQuit() );
					userInput = scanner.nextLine();
				}
				
				if ( userInput.equals(patternTask.getAnswer()) ) {
					correctAnswers++;
					pattern = getRandomPatternByVariant( theme, variant );
				} else {
					if ( correctAnswers > 0 )
						correctAnswers--;
					
					if ( userInput.equals(DBGetData.getTextGetAnswer()) )
						view.printMessage( patternTask.getAnswer() );
				}
				
				if ( NUMBER_PROCESS_PATTERNS == correctAnswers )
					view.printMessage( DBGetData.getMessageCompleteWorkWithPattern() );
			}
			
			
		} catch ( Exception exc ) {
			System.out.println( exc );
		}
	}

	private String getRandomPatternByVariant(String theme, String variant) throws FileNotFoundException, IOException {
		String pattern;
		ArrayList<String> listPatterns = model.getListTemplates( theme, variant );
		logger.info( "Get list patterns for theme, variant: " + theme + ", " + variant );
		int randomNumber = 0; //RandomNumber.getRandomNumber(listPatterns.size());
		pattern = listPatterns.get(randomNumber);
		logger.info( "Get pattern (0 for start): " + pattern );
		return pattern;
	}

	private String getVariantByUserInput(String theme) throws FileNotFoundException, IOException {
		ArrayList<String> listVariants = model.getListVariants( theme );
		logger.info( "Get list variants for theme: " + theme );
		view.printList( DBGetData.getMessageListVariants(), listVariants );
		
		int variantNumber = 2; //getNumberFromUser( DBGetData.getMessageGetVariantNumber(),
						//listVariants.size() - 1, DBGetData.getMessageNotCorrectVariantNumber() );
		logger.info( "Get variant number from user: " + variantNumber );

		String variant = listVariants.get( variantNumber );  
		logger.info( "Get variant: " + variant );
		return variant;
	}

	private String getThemeByUserInput() throws FileNotFoundException, IOException {
		ArrayList<String> listThemes = model.getListThemes();
		logger.info( "Get list themes" );
		view.printList( DBGetData.getMessageListThemes(), listThemes );
		
		int themeNumber = 0; //getNumberFromUser( DBGetData.getMessageGetThemeNumber(),
				//model.getThemesNumber() - 1, DBGetData.getMessageNotCorrectThemeNumber() );
		logger.info( "Get theme number: " + themeNumber );

		String theme = listThemes.get(themeNumber); 
		logger.info( "Get theme: " + theme );
		return theme;
	}

	private int getNumberFromUser( String message, int size, String messageNotCorrect ) {
		int userChoise = getIntUserInput( message );
		while ( userChoise < 0 || userChoise > size ) {
			view.printMessage( messageNotCorrect );
			userChoise = getIntUserInput( message );
		}
		
		return userChoise;
	}

	private void processingPattern(String randomPattern) {
		// -1. create ProcessingPattern object
		
		// 1. get task and answer from pattern
		
		
		// 2. output task
		
		// 3. get user input
		
		// 4. check: is answer equal to user input?
		
		// 5. if yes - increase number correct answers
		
		// 6. if no -
		
		// 	6.1. set number correct answers = 0
		
		//	6.2. check: is input == 'a'?
		
		//		6.2.1. if yes - output answer
		
		//		6.2.2. if no - output task and get answer
		
	}


	public int getIntUserInput(String caption ) {
        view.printMessage( caption );
        while( !scanner.hasNextInt() ) {
        	view.printMessage( DBGetData.getMessageNotCorrentInput() );
        	view.printMessage( caption );
        	scanner.next();
        }
        return scanner.nextInt();
	}

}
