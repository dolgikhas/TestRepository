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

		String theme, variant, pattern;
		
		try {
			theme = getThemeByUserInput();
			variant = getVariantByUserInput( theme );
//			pattern = getRandomPatternByVariant( theme, variant );
			pattern = getPatternByUserInput( theme, variant );
			
			model.getCommonLists();
			model.setProcessingPatternObject( theme, variant, pattern );
	
			String userInput = scanner.nextLine();	// app output not correct input without input %)
			int correctAnswers = 0;
			while ( (correctAnswers < NUMBER_PROCESS_PATTERNS) &&
					!isUserInputQuit(userInput) )
			{
				logger.info( "before get pattern task" );
				PatternTask patternTask = model.getPatternTask();
				logger.info( "after get pattern task" );
				view.printMessage( DBGetData.getMessageInputAnswerOrQuit() );
				view.printMessage( patternTask.getTask() + ": " );
				
				userInput = scanner.nextLine();
				
				while ( !model.checkIsUserInputEqualToAnswer(userInput) &&
						!isUserInputGetAnswer(userInput) &&
						!isUserInputQuit(userInput) ) {
					view.printMessage( DBGetData.getMessageNotCorrectAnswer() );
					view.printMessage( DBGetData.getMessageRepeatInputOrGetAnswerOrQuit() );
					userInput = scanner.nextLine();
				}
				
				if ( model.checkIsUserInputEqualToAnswer(userInput) ) {
					correctAnswers++;
//					pattern = getRandomPatternByVariant( theme, variant );
					model.setProcessingPatternObject( theme, variant, pattern );
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

	private boolean isUserInputGetAnswer(String userInput) {
		return userInput.toLowerCase().equals(DBGetData.getTextGetAnswer());
	}

	private boolean isUserInputQuit(String userInput) {
		return userInput.toLowerCase().equals( DBGetData.getTextQuit() );
	}

	private String getPatternByUserInput(String theme, String variant) throws FileNotFoundException, IOException {
		ArrayList<String> listPatterns = model.getListTemplates( theme, variant );
		logger.info( "Get list patterns for theme, variant: " + theme + ", " + variant );

		view.printList( DBGetData.getMessageListPatterns(), listPatterns );
		String pattern;
		if ( 1 == listPatterns.size() ) {
			pattern = listPatterns.get(0);
			logger.info( "Get first pattern. It is only on in list." );
		} else {
			int patternNumber = getNumberFromUser( DBGetData.getMessageGetPatternNumber(),
					listPatterns.size() - 1, DBGetData.getMessageNotCorrectPatternNumber() );
			logger.info( "Get pattern number from user: " + patternNumber );
			pattern = listPatterns.get(patternNumber);
		}
		logger.info( "Get pattern: " + pattern );
		return pattern;
	}

	private String getRandomPatternByVariant(String theme, String variant) throws FileNotFoundException, IOException {
		String pattern;
		ArrayList<String> listPatterns = model.getListTemplates( theme, variant );
		logger.info( "Get list patterns for theme, variant: " + theme + ", " + variant );
		int randomNumber = RandomNumber.getRandomNumber(listPatterns.size());
		pattern = listPatterns.get(randomNumber);
		logger.info( "Get pattern (0 for start): " + pattern );
		return pattern;
	}

	private String getVariantByUserInput(String theme) throws FileNotFoundException, IOException {
		ArrayList<String> listVariants = model.getListVariants( theme );
		logger.info( "Get list variants for theme: " + theme );
		view.printList( DBGetData.getMessageListVariants(), listVariants );
		
		int variantNumber = getNumberFromUser( DBGetData.getMessageGetVariantNumber(),
						listVariants.size() - 1, DBGetData.getMessageNotCorrectVariantNumber() );
		logger.info( "Get variant number from user: " + variantNumber );

		String variant = listVariants.get( variantNumber );  
		logger.info( "Get variant: " + variant );
		return variant;
	}

	private String getThemeByUserInput() throws FileNotFoundException, IOException {
		ArrayList<String> listThemes = model.getListThemes();
		logger.info( "Get list themes" );
		view.printList( DBGetData.getMessageListThemes(), listThemes );
		
		int themeNumber = 1; //getNumberFromUser( DBGetData.getMessageGetThemeNumber(),
				//model.getThemesNumber() - 1, DBGetData.getMessageNotCorrectThemeNumber() );
		logger.info( "Get theme number: " + themeNumber );

		String theme = listThemes.get(themeNumber); 
		logger.info( "Get theme: " + theme );
		return theme;
	}

	private int getNumberFromUser( String message, int size, String messageNotCorrect ) {
      view.printMessage( message );
		int userChoise = scanner.nextInt();
		while ( userChoise < 0 || userChoise > size ) {
			view.printMessage( messageNotCorrect );
			userChoise = Integer.parseInt(scanner.nextLine());
//			userChoise = scanner.nextInt();
		}
		
		return userChoise;
	}
}
