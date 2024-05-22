package mny.processwords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import mny.processwords.controller.ProcessWordsSwingController;
import mny.processwords.model.PathService;
import mny.processwords.model.Paths;
import mny.processwords.model.ProcessWordsModel;
import mny.processwords.view.ProcessWordsFrame;

public class ProcessEnglishWords {
	private final static Logger logger = Logger.getLogger("ProcessEnglishWords");
	private static Paths pathValues = new Paths();

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
			int choise = PathService.checkCurrentPaths(view);
			
			if (choise == JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(view, "Тогда исправляй пути!");
			} else {
				PathService.setPaths(pathValues);
				controller.setPathValues(pathValues);
				controller.initSwingController();
			}
		} catch (IOException exc) {
			System.out.println(exc); 
		}
	}
}
