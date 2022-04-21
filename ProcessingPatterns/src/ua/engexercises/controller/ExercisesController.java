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

import ua.engexercises.view.ExercisesView;

public class ExercisesController {
	ExercisesModel model;
	ExercisesView view;
	Scanner scanner;
	public static final int OPTION_PROCESS_THEME = 1;
	public static final int OPTION_PROCESS_ALL = 2;
	private final static Logger logger = Logger.getLogger("Test");

	public static void initLoggerFileHandler() throws SecurityException, IOException {
		FileHandler fh = new FileHandler( "log.txt" );
		logger.addHandler( fh );
		
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter( formatter );
		
		logger.info( "Logger initialized" );
	}

	public ExercisesController(ExercisesModel model, ExercisesView view) {
		this.model = model;
		this.view = view;
		scanner = new Scanner(System.in);
	}

	public void processingUser() throws FileNotFoundException, IOException {
		try {
			initLoggerFileHandler();
		} catch ( Exception exc ) {
			logger.log( Level.WARNING, "Exception in initLoggerFileHandler: ", exc );
		}

		int appOption = getAppOptionFromUser();
		logger.info( "Get option of application: " + appOption );
		
		ArrayList<String> listThemes = model.getListThemes();
		
		if ( OPTION_PROCESS_THEME == appOption ) {
			logger.info( "=== Processing one theme ===" );

			view.printListThemes( listThemes );
			
			int themeNumber = getThemeNumber();
			logger.info( "Get theme number: " + themeNumber );
		
			logger.info( "Get theme: " + model.getThemeByIndex(themeNumber) );
		} else if ( OPTION_PROCESS_ALL == appOption ) {
			logger.info( "=== processing all themes ===" );
			
			String randomTheme = model.getRandomTheme();
			logger.info( "Get theme: " + randomTheme );
		}
/*
		System.out.println( "before get list files: " );
		File dir = new File( "THEMES\\Present Simple - to be" ); //path указывает на директорию
		File[] arrFiles = dir.listFiles();
		for ( File file : arrFiles ) {
			System.out.println( file );
		}
		System.out.println( "after get list files" );
*/
	}

	private void processingPattern(String randomPattern) {
		// TODO Auto-generated method stub
		
	}

	private int getThemeNumber() {
		int themeNumber = getIntUserInput( DBGetData.getThemeNumber() );
		while ( themeNumber < 0 || themeNumber > model.getThemesNumber() ) {
			view.printMessage( DBGetData.getMessageNotCorrectThemeNumber() );
	 		themeNumber = getIntUserInput( DBGetData.getThemeNumber() );
		}
		
		return themeNumber;
	}

	public int getAppOptionFromUser() {
		int appOption = getIntUserInput( DBGetData.getListAppOptions() );
		while ( appOption < OPTION_PROCESS_THEME ||
				appOption > OPTION_PROCESS_ALL ) {
			view.printMessage( DBGetData.getMessageNotCorrectAppOption() );
	 		appOption = getIntUserInput( DBGetData.getListAppOptions() );
		}
		
		return appOption;
	}

	public int getIntUserInput(String caption ) {
        view.printMessage( caption );
        while( !scanner.hasNextInt() ) {
//        	view.printMessage( ProcessingPatternsConstants.MESSAGE_NOT_CORRECT_INPUT );
        	view.printMessage( DBGetData.getMessageNotCorrentInput() );
        	view.printMessage( caption );
        	scanner.next();
        }
        return scanner.nextInt();
	}

}
