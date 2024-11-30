package mny.checkwords.processwords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import mny.checkwords.model.Word;

public class ProcessWordsFiles {
	private final static Logger logger = Logger.getLogger("ProcessWordsFiles");
	private final String pathWords;
	private HashSet<String> words = new HashSet<>();

	public ProcessWordsFiles(String pathWords) {
		this.pathWords = pathWords;
	}

	public void createWordsFiles(String pathFileOldWords, String pathFileNewWords) {
		try (FileInputStream fileInputString = new FileInputStream(pathFileOldWords);
			 InputStreamReader inputStringReader = new InputStreamReader(fileInputString, StandardCharsets.UTF_16);
			 BufferedReader reader = new BufferedReader(inputStringReader);
			 BufferedWriter newWordsFile = new BufferedWriter(new FileWriter(pathFileNewWords))) {

			logger.info("file " + pathFileOldWords + " opened successfully!");
			String strLine;
			while ( ( strLine = reader.readLine() ) != null ) {
//				logger.info("\t\t" + strLine);

				String[] array = strLine.split("\t");
				String wordName = array[0];
				
				if (words.contains(wordName)) {
					System.out.println(wordName + " exists!");
				} else {
					words.add(wordName);
				}
				
				Word word = createWordObjectFromArray(array);
//				logger.info("\tcreate word object " + word.getWord() + " " + word.getTranslation());
				createWordFile(word);
				addWordDataToWordsFile(newWordsFile, word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addWordDataToWordsFile(BufferedWriter newWordsFile, Word word) throws IOException {
		newWordsFile.write(word.getWord() + "\t" + word.getRepeatNumber() + "\n");

	}

	private Word createWordObjectFromArray(String[] array) {
		return new Word.WordBuilder()
						.setWord(array[0])
						.setTranscription(array[1])
						.setTranslation(array[2])
						.setRepeat(array[3])
						.build();
	}

	private void createWordFile(final Word word) throws IOException {
		try (BufferedWriter bw	= new BufferedWriter(
				new FileWriter(pathWords + word.getWord() + ".txt", StandardCharsets.UTF_16) ) ) {
			bw.write(word.getWord() + "\n" +
					 word.getTranscription() + "\n" +
					 word.getTranslation() + "\n");
		}
	}
}
