package ua.engexercises.controller;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ua.engexercises.model.DBGetData;
import ua.engexercises.model.ExercisesModel;
import ua.engexercises.model.RandomNumber;
import ua.engexercises.view.ExercisesView;

public class ExercisesMVCMain {
	private final static Logger logger = Logger.getLogger("Test");

	public static void initLoggerFileHandler() throws SecurityException, IOException {
		FileHandler fh = new FileHandler( "log.txt" );
		logger.addHandler( fh );
		
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter( formatter );
		
		logger.info( "Logger initialized" );
	}

	public static void main(String[] args) {
		try {
			initLoggerFileHandler();
		} catch ( Exception exc ) {
			logger.log( Level.WARNING, "Exception in initLoggerFileHandler: ", exc );
		}

		ExercisesView view = new ExercisesView();
		ExercisesModel model = new ExercisesModel( logger );
		ExercisesController controller = new ExercisesController( model, view, logger );
		try {
			controller.processingUser();
		} catch ( IOException exc ) {
			System.out.println( exc );			
		}		
	}

}
