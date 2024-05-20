package mny.processwords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import mny.processwords.worddata.Word;

public class CreateListWordsFromExamples {
	static HashSet<String> listAllWords = new HashSet<String>();

	
//	public static void main(String[] args) {		
//		createListWordsFromExamples();
//	}
	
	public static void getListAllWords(String filePath) throws FileNotFoundException, IOException {
		try (FileInputStream fis = new FileInputStream(filePath);
			       InputStreamReader isr = new InputStreamReader(fis);
			       BufferedReader reader = new BufferedReader(isr)) {
				
				String line;
				while ((line = reader.readLine()) != null) {
					listAllWords.add(line);	
				}
		}
	}

	public static boolean isSentenceContainCheckedWords(String sentence) {
		ArrayList<String> words = getWordsFromLine(sentence);
		for (String word : words) {
			if (!listAllWords.contains(word))
				return false;
		}
		return true;
	}

	public static void createListWordsFromExamples(String pathExamples) {
		File dir = new File(pathExamples);
		File[] arrFiles = dir.listFiles();
		List<File> lst = Arrays.asList(arrFiles);
		try {
			for (File file : lst) {
				
				if (!file.getName().contains(".txt"))
					continue;
				
				processingFile(pathExamples, file);
			}
				
			writeWordsToFile("all_words.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void processingFile(String path, File file) throws IOException, FileNotFoundException {
		try (FileInputStream fis = new FileInputStream(path + file.getName());
			       InputStreamReader isr = new InputStreamReader(fis);
			       BufferedReader reader = new BufferedReader(isr)) {
				
				String line;
				while ( ( line = reader.readLine() ) != null ) {
					ArrayList<String> listWords = getWordsFromLine(line);
					for (String word : listWords) {
						listAllWords.add(word);								
					}							
				}
		}
	}

	private static ArrayList<String> getWordsFromLine(String line) {
		ArrayList<String> listWords = new ArrayList<String>();
		String newLine = replaceNotCorrectSymbols(line);

		String[] words = newLine.split(" ");
		for (String word : words) {
			if (!word.isEmpty() && (2 < word.length()))
				listWords.add(word.toLowerCase());
		}
		
		return listWords;
	}


	private static String replaceNotCorrectSymbols(String line) {
		String newLine = line.replace('.', ' ');
		newLine = newLine.replace(',', ' ');
		newLine = newLine.replace('?', ' ');
		newLine = newLine.replace('!', ' ');
		newLine = newLine.replace('"', ' ');
		newLine = newLine.replace('%', ' ');
		newLine = newLine.replace('+', ' ');
		newLine = newLine.replace('-', ' ');
		newLine = newLine.replace('/', ' ');
		newLine = newLine.replace('(', ' ');
		newLine = newLine.replace(')', ' ');
		newLine = newLine.replace('=', ' ');
		newLine = newLine.replace('%', ' ');																						
		newLine = newLine.replace('”', ' ');
		newLine = newLine.replace(';', ' ');
		newLine = newLine.replace('`', ' ');
		newLine = newLine.replace(':', ' ');
		newLine = newLine.replace('“', ' ');
		newLine = newLine.replace('$', ' ');
		newLine = newLine.replace('#', ' ');
		newLine = newLine.replace('0', ' ');
		newLine = newLine.replace('1', ' ');
		newLine = newLine.replace('2', ' ');
		newLine = newLine.replace('3', ' ');
		newLine = newLine.replace('4', ' ');
		newLine = newLine.replace('5', ' ');
		newLine = newLine.replace('6', ' ');
		newLine = newLine.replace('7', ' ');
		newLine = newLine.replace('8', ' ');
		newLine = newLine.replace('9', ' ');
		newLine = newLine.replace('…', ' ');
		newLine = newLine.replace('\t', ' ');
		
		return newLine;
	}
	
	public static void writeWordsToFile(String filePath) throws IOException {
		try (BufferedWriter bw	= new BufferedWriter(
				new FileWriter(filePath) ) ) {
			for (String word : listAllWords) {
				bw.write(word + "\n");
			}
		}

	}

}
