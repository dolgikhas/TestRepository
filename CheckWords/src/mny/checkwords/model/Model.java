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
	private ArrayList<Word> listWords;
	private FileManager fileManager;
	private int currentWordIndex = 0;
	
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
		return listWords.get(0);
	}

	public boolean isNextWord() {
		return (currentWordIndex + 1) < listWords.size();
	}

	public Word getNextWord() {
		return listWords.get(++currentWordIndex);
	}

	public boolean isPreviousWord() {
		return 0 != currentWordIndex;
	}

	public Word getPreviousWord() {
		return listWords.get(--currentWordIndex);
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
		ArrayList<String> listWordsRepeat = new ArrayList<>();
		
		for (Word word : listWords) {
			ResultCheck result = word.getResultCheck();
			
			if (ResultCheck.Plus == result) {
				writeWordToFileOrRepeatFile(word, fileWords);
				continue;				
			} else if (ResultCheck.Equal == result) {
				fileManager.writeWordAndRepeatNumberToFile(word, ResultCheck.Equal);
				continue;				
			} else if (ResultCheck.Doubt == result) {
//				fileManager.writeWordAndRepeatNumberToFile(word, ResultCheck.Equal);
				writeWordToFileOrRepeatFile(word, fileWords);
				fileManager.writeWordAndRepeatNumberToFileDoubtWords(word, ResultCheck.Minus);
				continue;				
			}
			
			fileManager.writeWordToFileDoubtWords(fileWords, word);

			if (ResultCheck.Asterisk == result) {
				listWordsRepeat.add(word.getWord());
			}
			
			if (fileWords.equals(Constants.FILE_CHECKED_WORDS)) {
				fileManager.writeWordToFileNewWords(word, result);
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
			
			if (fileWords.equals(Constants.FILE_NEW_WORDS)) {
				fileManager.writeWordToFileProcessedWords(word);
			}
		} else {
			fileManager.writeWordAndRepeatNumberToFile(word, ResultCheck.Plus);
		}
	}

	public static String getRepeatNumberByOption(Word word, ResultCheck option) {
		if (ResultCheck.Minus == option) {
			return "0";
		} else if (ResultCheck.Plus == option) {
			return Integer.toString(word.getRepeatNumber() + 1);
		} else if (ResultCheck.Minus4 == option) {
			if (word.getRepeatNumber() > 4)
				return Integer.toString(word.getRepeatNumber() - 4);
			else
				return "0";
		} else if (ResultCheck.Equal == option) {
			return Integer.toString(word.getRepeatNumber());
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
		} else if (Constants.FILE_CHECKED_WORDS.equals(fileWords)) {
			return Constants.FILE_CHECKED_WORDS.replaceAll(".txt", "_2.txt");
		}
		
		throw new Exception("Get not correct file name for get repeatFile");
	}

	public boolean isWordCompletedRepeat(Word word, String fileWords) {
		final int maxNumber = getMaxNumberRepeat(fileWords);
		
		if (maxNumber <= word.getRepeatNumber()) {
			return true;
		}
		
		return false;
	}

	public int getMaxNumberRepeat(String fileWords) {
		
		if (Constants.FILE_NEW_WORDS.equals(fileWords)) {
			return Constants.REPEAT_NUMBER_NEW_WORDS;
		} else if (Constants.FILE_DOUBT_WORDS.equals(fileWords)) {
			return Constants.REPEAT_NUMBER_DOUBT_WORDS;
		}
		return Constants.REPEAT_NUMBER_REPEAT_WORDS;
	}

	public void writeWordToRepeatFileIfNeed(Word word, String fileWords) throws Exception {
		if (!isNeedToWriteFileToRepeatFile(fileWords)) {
			return;
		}
		
		String repeatFile = getRepeatFileName(fileWords);
		
		fileManager.writeWordAndRepeatNumberToRepeatFile(word, ResultCheck.Minus);
	}


	public void readListWords(String fileWordsToCheck) throws FileNotFoundException, IOException {
		fileManager.readListWords(fileWordsToCheck);
	}

	public ArrayList<String> getExamples(Word word) throws FileNotFoundException, IOException {
		return FileManager.getExamplesForWordOrExpression(word.getWord());
	}

	public int getNumberWords() {
		return listWords.size();
	}

	public int getCurrentWordIndex() {
		return currentWordIndex;
	}

	public void saveCheckWordResultsWithWordsWithoutResults(String fileWords) throws Exception {
		if (fileWords.equals(Constants.FILE_PROCESSED_WORDS)) {
			int indexWordsWithResults = --currentWordIndex; 
			ArrayList<Word> listNotCheckedWords = new ArrayList<>();
			ArrayList<Word> listCheckedWords = new ArrayList<>();
			getListNotCheckedWords(listNotCheckedWords);		
			getListCheckedWords(indexWordsWithResults, listCheckedWords);

			listWords = listNotCheckedWords;
			saveCheckWordResults(Constants.FILE_PROCESSED_WORDS);
			listWords = listCheckedWords;
			saveCheckWordResults(Constants.FILE_CHECKED_WORDS);
			
			return;
		}
			
			
		createListWordsWithoutResultsThenWordsWithResults();
		
		saveCheckWordResults(fileWords);
	}

	private void createListWordsWithoutResultsThenWordsWithResults() {
		int indexWordsWithResults = --currentWordIndex; 
		ArrayList<Word> resultListWords = new ArrayList<>();

		getListNotCheckedWords(resultListWords);		
		getListCheckedWords(indexWordsWithResults, resultListWords);
		
		listWords = resultListWords;
	}

	public void getListCheckedWords(int indexWordsWithResults, ArrayList<Word> resultListWords) {
		for (int index = 0; index <= indexWordsWithResults; index++) {
			checkIfResultAsteriskChangeToMinus4(index);
			
			resultListWords.add(listWords.get(index));
		}
	}

	public void getListNotCheckedWords(ArrayList<Word> resultListWords) {
		while (isNextWord()) {
			Word word = getNextWord();
			word.setResultCheck(ResultCheck.Equal);
			resultListWords.add(word);
		}
	}

	private void checkIfResultAsteriskChangeToMinus4(int index) {
		Word word = listWords.get(index);

		if (ResultCheck.Asterisk == word.getResultCheck()) {
			word.setResultCheck(ResultCheck.Minus4);
		}
	}

	public void createWordsList() {
		listWords = new ArrayList<>();
		fileManager = new FileManager(listWords);
		currentWordIndex = 0;
	}
}
