package mny.checkwords.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class Model {
	
	private final static Logger logger = Logger.getLogger("Model");
	
	private HashMap<String, Word> mapWordsData = new HashMap<>();
	private ArrayList<String> listWords = new ArrayList<>();
	private FileManager fileManager;
	private int currentWordIndex = 0;
	
	public Model() {
		fileManager = new FileManager(mapWordsData , listWords);
	}	

	String getPathWords() {
		return Constants.PATH_WORDS_DATA;
	}

	public ArrayList<String> getListWordFiles() {
		ArrayList<String> listWordTypes = new ArrayList<>();
		listWordTypes.add(Constants.FILE_NEW_WORDS);
		listWordTypes.add(Constants.FILE_PROCESSED_WORDS);
		listWordTypes.add(Constants.FILE_DOUBT_WORDS);
		listWordTypes.add(Constants.FILE_NEW_WORDS_REPEAT);
		listWordTypes.add(Constants.FILE_PROCESSED_WORDS_REPEAT);
		listWordTypes.add(Constants.FILE_DOUBT_WORDS_REPEAT);
		listWordTypes.add(Constants.FILE_NEW_WORDS_REPEAT_2);
		listWordTypes.add(Constants.FILE_PROCESSED_WORDS_REPEAT_2);
		listWordTypes.add(Constants.FILE_DOUBT_WORDS_REPEAT_2);
		
		return listWordTypes;
	}

	public Word getFirstWord() {
		logger.info("\tbefore getFirstWord(): " + listWords.get(0));
		return mapWordsData.get(listWords.get(0));
	}

	public boolean isNextWord() {
		logger.info("\tbefore isNextWord(): " + (currentWordIndex < listWords.size()));
		return (currentWordIndex + 1) < listWords.size();
	}

	public Word getNextWord() {
		logger.info("\tbefore getNextWord(): " + listWords.get(currentWordIndex + 1));
		return mapWordsData.get(listWords.get(++currentWordIndex));
	}

	public boolean isPreviousWord() {
		logger.info("\tbefore isPreviousWord(): " + (0 != currentWordIndex));
		return 0 != currentWordIndex;
	}

	public Word getPreviousWord() {
		logger.info("\tbefore getPreviousWord(): " + listWords.get(currentWordIndex - 1));
		return mapWordsData.get(listWords.get(--currentWordIndex));
	}

	public boolean isFirstWord() {
		return 0 == currentWordIndex;
	}

	public void saveCheckWordResults(String fileWords) throws Exception {
		fileManager.moveCurrentFileWordsToArchive(fileWords);
		fileManager.initializeRepeatWordsFile(this.getRepeatFileName(fileWords));
		fileManager.initializeFileBufferWriter(fileWords);
		fileManager.initializeDoubtWordsWriter();
		
		processingListWords(fileWords);
		
		fileManager.closeFileBufferWriter();
		fileManager.closeDoubtWordsWriter();
		fileManager.closeRepeatWordsWriter();
	}

	public void processingListWords(String fileWords) throws Exception {
		logger.info("\tinitialize doubt words writer");
		ArrayList<String> listWordsRepeat = new ArrayList<>();
		
		for (String strWord : listWords) {
			Word word = mapWordsData.get(strWord);
			ResultCheck result = word.getResultCheck();
			
			if (ResultCheck.Plus == result) {
				writeWordToFileOrRepeatFile(word, fileWords);
				continue;
			} 

			fileManager.writeWordToFileDoubtWords(fileWords, word);

			if (ResultCheck.Asterisk == result) {
				listWordsRepeat.add(strWord);
			} else {
				fileManager.writeWordAndRepeatNumberToFile(word, result);
			}			
		}
		
		// TODO: дополнительная проверка остаточных слов
//		if (0 < listWordsRepeat.size()) {
//			
//		}
	}

	private void writeWordToFileOrRepeatFile(Word word, String fileWords) throws Exception {
		if (isWordCompletedRepeat(word, fileWords)) {
			writeWordToRepeatFileIfNeed(word, fileWords);
		} else {
			fileManager.writeWordAndRepeatNumberToFile(word, ResultCheck.Plus);
		}
	}

	public static String getRepeatNumberByOption(Word word, ResultCheck option) {
		logger.info("\t\t" + word.getWord() + "\t" + word.getRepeatNumber());
		
		if (ResultCheck.Minus == option) {
			return "0";
		} else if (ResultCheck.Plus == option) {
			return Integer.toString(word.getRepeatNumber() + 1);
		} else if (ResultCheck.Minus4 == option) {
			if (word.getRepeatNumber() > 4)
				return Integer.toString(word.getRepeatNumber() - 4);
			else
				return "0";
		}

		return "-1";
	}

	public boolean isNeedToWriteFileToRepeatFile(String fileWords) {
		if (Constants.FILE_NEW_WORDS_REPEAT_2.equals(fileWords) ||
			Constants.FILE_PROCESSED_WORDS_REPEAT_2.equals(fileWords) ||
			Constants.FILE_DOUBT_WORDS_REPEAT_2.equals(fileWords)) {
			return false;
		}
		return true;
	}

	public String getRepeatFileName(String fileWords) throws Exception {
		if (Constants.FILE_NEW_WORDS.equals(fileWords)) {
			return Constants.FILE_NEW_WORDS_REPEAT;
		} else if (Constants.FILE_PROCESSED_WORDS.equals(fileWords)) {
			return Constants.FILE_PROCESSED_WORDS_REPEAT;
		} else if (Constants.FILE_DOUBT_WORDS.equals(fileWords)) {
			return Constants.FILE_DOUBT_WORDS_REPEAT;
		} else if (Constants.FILE_NEW_WORDS_REPEAT.equals(fileWords)) {
			return Constants.FILE_NEW_WORDS_REPEAT_2;
		} else if (Constants.FILE_PROCESSED_WORDS_REPEAT.equals(fileWords)) {
			return Constants.FILE_PROCESSED_WORDS_REPEAT_2;
		} else if (Constants.FILE_DOUBT_WORDS_REPEAT.equals(fileWords)) {
			return Constants.FILE_DOUBT_WORDS_REPEAT_2;
		}
		
		throw new Exception("Get not correct file name for get repeatFile");
	}

	public boolean isWordCompletedRepeat(Word word, String fileWords) {
		final int maxNumber = getMaxNumberRepeat(fileWords);
		
		if (maxNumber <= word.getRepeatNumber()) {
			logger.info("\tword " + word.getWord() + " completed repeat");
			return true;
		}
		
		logger.info("\tword " + word.getWord() + " continue repeat");
		return false;
	}

	public int getMaxNumberRepeat(String fileWords) {
		logger.info("\tbefore get max number for word: " + fileWords);
		
		if (Constants.FILE_NEW_WORDS.equals(fileWords)) {
			logger.info("\treturn " + Constants.REPEAT_NUMBER_NEW_WORDS);
			return Constants.REPEAT_NUMBER_NEW_WORDS;
		} else if (Constants.FILE_DOUBT_WORDS.equals(fileWords)) {
			logger.info("\treturn " + Constants.REPEAT_NUMBER_DOUBT_WORDS);
			return Constants.REPEAT_NUMBER_DOUBT_WORDS;
		}
		logger.info("\treturn " + Constants.REPEAT_NUMBER_REPEAT_WORDS);
		return Constants.REPEAT_NUMBER_REPEAT_WORDS;
	}

	public void writeWordToRepeatFileIfNeed(Word word, String fileWords) throws Exception {
		if (!isNeedToWriteFileToRepeatFile(fileWords)) {
			logger.info("\tdon't need to write word to repeat file");
			return;
		}
		
		String repeatFile = getRepeatFileName(fileWords);
		
//		fileManager.initializeRepeatFile(repeatFile);
		fileManager.writeWordAndRepeatNumberToRepeatFile(word, ResultCheck.Minus);
	}


	public void readListWords(String fileWordsToCheck) throws FileNotFoundException, IOException {
		fileManager.readListWords(fileWordsToCheck);
	}

	public ArrayList<String> getExamples(Word word) throws FileNotFoundException, IOException {
		return FileManager.getExamplesForWordOrExpression(word.getWord());
	}




}
