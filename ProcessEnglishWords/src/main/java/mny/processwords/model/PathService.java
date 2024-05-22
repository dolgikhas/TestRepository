package mny.processwords.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import mny.processwords.view.ProcessWordsFrame;

public class PathService {
	private final static String filePaths = "PATHS.txt";
	private static String paths;

	public static void setPaths(Paths pathValues) {
		String arrayPaths[] = paths.split("\n");
		
		for (String path : arrayPaths) {
			if (path.startsWith("EXAMPLES: ")) {
				String strExamples[] = path.split(": ");
				pathValues.setExamples(strExamples[1]);
			} else if (path.startsWith("CHECK_WORDS: ")) {
				String strCheckWords[] = path.split(": ");
				pathValues.setCheckWords(strCheckWords[1]);
			}
		}
	}

	public static int checkCurrentPaths(ProcessWordsFrame view) throws FileNotFoundException, IOException {
		paths = getCurrentPaths();
		
		return JOptionPane.showConfirmDialog(view, "Сейчас заданы следующие:\n" + paths,
				"Использовать существующие пути?", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
	}

	public static String getCurrentPaths() throws FileNotFoundException, IOException {
		String paths = "";
		try (FileInputStream fis = new FileInputStream(filePaths);
		       InputStreamReader isr = new InputStreamReader(fis);
		       BufferedReader reader = new BufferedReader(isr)) {
				
			String line;
			while ((line = reader.readLine()) != null) {
				paths += line + "\n";
			}
		}
		return paths;
	}

}
