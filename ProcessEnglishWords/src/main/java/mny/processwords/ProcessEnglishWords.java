package mny.processwords;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import mny.processwords.controller.ProcessWordsSwingController;
import mny.processwords.model.ProcessWordsModel;
import mny.processwords.view.ProcessWordsFrame;

public class ProcessEnglishWords {
	private final static Logger logger = Logger.getLogger("ProcessEnglishWords");

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
			logger.log(Level.WARNING, "Exception in initLoggerFileHandler: ", exc);
		}
		
		ProcessWordsFrame view = new ProcessWordsFrame();
		ProcessWordsModel model = new ProcessWordsModel(logger);
		ProcessWordsSwingController controller = new ProcessWordsSwingController(model, view, logger);
		
		try {
			controller.initSwingController();
		} catch (IOException exc) {
			System.out.println(exc); 
		}
	}
}
