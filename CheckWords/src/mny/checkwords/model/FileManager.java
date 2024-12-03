package mny.checkwords.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.Random;

class MnyRandom {
	Random random = new Random();
	public int getRandomNumber(final int size) {
		return random.nextInt() % size;
	}
}

class WordFilter implements FilenameFilter
{
	String word;	
	
	public WordFilter(String word) {
		this.word = word;
	}

	@Override
	public boolean accept(File dir, String fileName)
	{
		return fileName.startsWith(word);
	}
}

public class FileManager {
	private ArrayList<Word> listWords;

	private final static Logger logger = Logger.getLogger("FileManager");

	private BufferedWriter doubtWordsWriter = null;
	private BufferedWriter fileBufferWriter = null;
	private BufferedWriter repeatFileWriter = null;
	private static MnyRandom mnyRandom = new MnyRandom();

	public FileManager(ArrayList<Word> listWords) {
		this.listWords = listWords;
	}
	
	public void initializeFileBufferWriter(String fileWords) throws IOException {
		FileWriter fileWriter;
		
		if (fileWords.equals(Constants.FILE_CHECKED_WORDS)) {
			fileWriter = new FileWriter(Constants.PATH_LISTS_WORDS + Constants.FILE_CHECKED_WORDS, true);
			logger.info("open file " + Constants.FILE_CHECKED_WORDS + " for adding words");
		} else {
			fileWriter = new FileWriter(Constants.PATH_LISTS_WORDS + fileWords);
			logger.info("create new file " + fileWords);
		}
		
		fileBufferWriter = new BufferedWriter(fileWriter);
	}

	public void writeWordToFileDoubtWords(String fileWords, Word word)
			throws IOException {
		if (!fileWords.equals(Constants.FILE_DOUBT_WORDS)) {
			writeWordAndRepeatNumberToFileDoubtWords(word, ResultCheck.Minus);
		}
	}

	public void moveCurrentFileWordsToArchive(String fileWords) throws IOException {
		String filelWordsWithDataAndTime = DateAndTime.getFileWordsWithDateAndTime(fileWords);
		
		File sourceFile = new File(Constants.PATH_LISTS_WORDS + fileWords);
		File destinationFile = new File(Constants.PATH_ARCHIVE + filelWordsWithDataAndTime);
		
		if (fileWords.equals(Constants.FILE_CHECKED_WORDS)) {
			Files.copy(sourceFile.toPath(), destinationFile.toPath());
		} else {
			Files.move(sourceFile.toPath(), destinationFile.toPath());
		}
	}
	
	public void writeWordAndRepeatNumberToFileDoubtWords(Word word, ResultCheck option) throws IOException {
		String repeatNumber = "0";
		doubtWordsWriter.write(word.getWord() + "\t" + repeatNumber + "\n");
	}

	
	public void writeWordAndRepeatNumberToFile(Word word, ResultCheck option) throws IOException {
		String repeatNumber = Model.getRepeatNumberByOption(word, option);
		fileBufferWriter.write(word.getWord() + "\t" + repeatNumber + "\n");
	}

	public void initializeDoubtWordsWriter() throws IOException {
		if (null != doubtWordsWriter)
			return;
		
		FileWriter fileDoubtWriter = new FileWriter(Constants.PATH_LISTS_WORDS + Constants.FILE_DOUBT_WORDS, true);
		doubtWordsWriter = new BufferedWriter(fileDoubtWriter);
	}
	
	public void readListWords(String fileWords) throws FileNotFoundException, IOException {
		try (FileInputStream fileInputStream = new FileInputStream(Constants.PATH_LISTS_WORDS + fileWords);
			 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			 BufferedReader reader = new BufferedReader(inputStreamReader)) {
				
			String strLine;
			while ((strLine = reader.readLine()) != null) {
				String[] array = strLine.split("\t");
				String word = array[0];
				String repeatNumber = array[1];
				readWordData(Constants.PATH_WORDS_DATA + word + ".txt", repeatNumber);
			}
		}
	}

	private void readWordData(String pathFileWordData, String repeatNumber) throws FileNotFoundException, IOException {
		try (FileInputStream fileInputStream = new FileInputStream(pathFileWordData);
			 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_16);
			 BufferedReader reader = new BufferedReader(inputStreamReader)) {
					
			String word = reader.readLine();
			String transcription = reader.readLine();
			String translation = reader.readLine();
					
			listWords.add(new Word.WordBuilder()
								  .setWord(word)
								  .setTranscription(transcription)
								  .setTranslation(translation)
								  .setRepeat(repeatNumber)
								  .build());
		}
	}
	public void initializeRepeatWordsFile(String repeatFile) throws IOException {
		FileWriter fileWriter = new FileWriter(Constants.PATH_LISTS_WORDS + repeatFile, true);
		repeatFileWriter = new BufferedWriter(fileWriter);
	}

	public void writeWordAndRepeatNumberToRepeatFile(Word word, ResultCheck minus) throws IOException {
		String repeatNumber = "0";
		repeatFileWriter.write(word.getWord() + "\t" + repeatNumber + "\n");
	}

	public void closeFileBufferWriter() throws IOException {
		fileBufferWriter.close();
		fileBufferWriter = null;
	}

	public void closeDoubtWordsWriter() throws IOException {
		doubtWordsWriter.close();
		doubtWordsWriter = null;
	}

	public void closeRepeatWordsWriter() throws IOException {
		if (null != repeatFileWriter) {
			repeatFileWriter.close();
		}
	}

	public static ArrayList<String> getExamplesForWordOrExpression(String word) throws FileNotFoundException, IOException {
		ArrayList<String> examples = new ArrayList<>();
		
		if (!isWordContainExamples(word)) {
			examples.add(word + " example");
			return examples;
		}
		
		ArrayList<String> filesExamples = getFilesExamples(word);
		examples = getRandomExamplesFromFilesExamples(filesExamples);
		
		return examples;
	}

	private static ArrayList<String> getRandomExamplesFromFilesExamples(ArrayList<String> filesExamples) throws FileNotFoundException, IOException {
		ArrayList<String> examples = new ArrayList<>();
		
		for (String fileExamples : filesExamples) {
			String example = getRandomExampleFromFileExamples(fileExamples); 
			examples.add(example);
		}
		
		return examples;
	}

	private static String getRandomExampleFromFileExamples(String fileExamples) throws FileNotFoundException, IOException {
		ArrayList<String> examples = new ArrayList<>();
		try (FileInputStream fileInputStream = new FileInputStream(Constants.PATH_EXAMPLES + fileExamples);
			 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			 BufferedReader reader = new BufferedReader(inputStreamReader)) {					
			String strLine;
			while ((strLine = reader.readLine()) != null) {
				examples.add(strLine);
			}
		}
		int size = examples.size();
		int randomNumber = Math.abs(mnyRandom.getRandomNumber(size));
		return examples.get(randomNumber);
	}

	private static ArrayList<String> getFilesExamples(String word) {
		File dir = new File(Constants.PATH_EXAMPLES);

		String[] filesWithPoints = dir.list(new WordFilter(word + "."));
		ArrayList<String> filesNames = new ArrayList<>();
		for (String file : filesWithPoints) {
			filesNames.add(file);
		}
		
		String[] filesWithLines = dir.list(new WordFilter(word + "_"));
		for (String file : filesWithLines) {
			filesNames.add(file);
		}
		
		return filesNames;
	}

	private static boolean isWordContainExamples(String word) {
		String exampleWordPath = Constants.PATH_EXAMPLES + word + ".txt"; 
		File file = new File(exampleWordPath);
		if (file.exists()) {
			return true;
		}
		
		exampleWordPath = Constants.PATH_EXAMPLES + word + "_1.txt"; 
		file = new File(exampleWordPath);
		if (file.exists()) {
			return true;
		}
		
		return false;
	}

	public void writeWordToFileProcessedWords(Word word) throws IOException {
		FileWriter fileWriter = new FileWriter(Constants.PATH_LISTS_WORDS + Constants.FILE_PROCESSED_WORDS, true);
		BufferedWriter fileBufferWriter = new BufferedWriter(fileWriter);
		String repeatNumber = "0";
		fileBufferWriter.write(word.getWord() + "\t" + repeatNumber + "\n");
		fileBufferWriter.close();
	}

	public void writeWordToFileNewWords(Word word, ResultCheck result) throws IOException {
		FileWriter fileWriter = new FileWriter(Constants.PATH_LISTS_WORDS + Constants.FILE_NEW_WORDS, true);
		BufferedWriter fileBufferWriter = new BufferedWriter(fileWriter);
		
		String repeatNumber = "0";
		if (ResultCheck.Minus4 == result) {
			repeatNumber = Integer.toString(Constants.REPEAT_NUMBER_NEW_WORDS - 4);
		}
		
		fileBufferWriter.write(word.getWord() + "\t" + repeatNumber + "\n");
		fileBufferWriter.close();
	}
	
}
