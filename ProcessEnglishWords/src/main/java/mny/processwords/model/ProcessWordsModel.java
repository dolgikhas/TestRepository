package mny.processwords.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import mny.processwords.sitedata.GetDataFromSite;
import mny.processwords.sitedata.SiteKeys;
import mny.processwords.worddata.Word;

public class ProcessWordsModel {
	public Logger logger;
	private HashMap<String, Word> mapWordsData;
	private ArrayList<String> newWords;
	private ArrayList<String> doubtWords;
	private ArrayList<String> processedWords;
	private ArrayList<String> checkedWords;
	private HashSet<String> listAllWords;

	static SiteKeys keysCambridgeDictionary = new SiteKeys.Builder()
			.setAddress("https://dictionary.cambridge.org/ru/%D1%81%D0%BB%D0%BE%D0%B2%D0%B0%D1%80%D1%8C/" +
					"%D0%B0%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9/WORD")
			.setKeyTranscription("ipa dipa lpr-2 lpl-1")
			.setKeyTranslation("trans dtrans dtrans-se ")
			.setKeyExamples("eg deg")
			.setKeyExamplesExtra("eg dexamp hax")
			.setKeyExamplesTranslation(null)
			.build();

	static SiteKeys keysExamplum = new SiteKeys.Builder()
			.setAddress("https://examplum.com/%D0%BF%D0%B5%D1%80%D0%B5%D0%B2%D0%BE%D0%B4/%D0%B0%D0%" +
					"BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9-%D1%80%D1%83%D1%81%D1%81%D0%" +
					"BA%D0%B8%D0%B9/WORD")
			.setKeyTranscription(null)
			.setKeyTranslation("res-word__answers")
			.setKeyExamples("res-example__src")
			.setKeyExamplesExtra(null)
			.setKeyExamplesTranslation("res-example__dest")
			.build();

	static SiteKeys keysEnglishlib = new SiteKeys.Builder()
			.setAddress("https://englishlib.org/dictionary/en-ru/WORD.html")
			.setKeyTranscription(null)
			.setKeyTranslation("else-trans")
			.setKeyExamples("act_td")
			.setKeyExamplesExtra(null)
			.setKeyExamplesTranslation(null)
			.build();
	
	static SiteKeys keysContextReverso = new SiteKeys.Builder()
			.setAddress("https://context.reverso.net/%D0%BF%D0%B5%D1%80%D0%B5%D0%B2%D0%BE%D0%B4/" +
					"%D0%B0%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9-%D1%80%D1%83%D1%81%D1%" +
					"81%D0%BA%D0%B8%D0%B9/WORD")
			.setKeyTranscription(null)
			.setKeyTranslation("display-term")
			.setKeyExamples("src ltr")
			.setKeyExamplesExtra(null)
			.setKeyExamplesTranslation(null)
			.build();
	
	static SiteKeys[] listSiteKeys = {keysCambridgeDictionary, keysExamplum, keysEnglishlib, keysContextReverso}; 
	
	public ProcessWordsModel(Logger logger) {
		this.logger = logger;
		listAllWords = new HashSet<String>();
	}

	public boolean isWordExists(String word) {
		return mapWordsData.containsKey(word);
	}

	public Word getWord(String word) {
		return mapWordsData.get(word);
	}
	
	public void loadWordsData() throws FileNotFoundException, IOException {
		logger.info("ProcessWordsModel.loadWordsData() >>");
		mapWordsData = new HashMap<String, Word>();
		newWords = loadWordsDataFromFile("D:\\mny\\оепемня\\ENGLISH\\check_words\\words_new.txt");
		logger.info("  loadWordsDataFromFile() for words_new");
		doubtWords = loadWordsDataFromFile("D:\\mny\\оепемня\\ENGLISH\\check_words\\words_doubt.txt");
		logger.info("  loadWordsDataFromFile() for words_doubt");
		processedWords = loadWordsDataFromFile("D:\\mny\\оепемня\\ENGLISH\\check_words\\words_processed.txt");
		logger.info("  loadWordsDataFromFile() for words_processed");
		checkedWords = loadWordsDataFromFile("D:\\mny\\оепемня\\ENGLISH\\check_words\\words_checked.txt");
		logger.info("  loadWordsDataFromFile() for words_checked");
		getListAllWords("all_words.txt");
		logger.info("  getListAllWords() processed");
		logger.info("ProcessWordsModel.loadWordsData() <<");		
	}
	
	private void getListAllWords(String filePath) throws FileNotFoundException, IOException {
		try (FileInputStream fis = new FileInputStream(filePath);
			 InputStreamReader isr = new InputStreamReader(fis);
			 BufferedReader reader = new BufferedReader(isr)) {
				
				String line;
				while ((line = reader.readLine()) != null) {
					listAllWords.add(line);	
				}
		}
	}

	
	public ArrayList<String> loadWordsDataFromFile(String filePath) throws FileNotFoundException, IOException {
		logger.info("  ProcessWordsModel.loadWordsDataFromFile() >>");
		ArrayList<String> words = new ArrayList<String>();
		
		try (FileInputStream fis = new FileInputStream(filePath);
		       InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_16);
		       BufferedReader reader = new BufferedReader(isr)) {
			
			logger.info("    file " + filePath + " opened successfully!");
			String strLine;
			while ( ( strLine = reader.readLine() ) != null ) {
//				logger.info("    read line " + strLine);
				String[] array = strLine.split("\t");
				String word = array[0];
				if (!mapWordsData.containsKey(word)) {
//					logger.info("    create word " + word + " " + array[1] + " " + array[2] + " " + array[3]);
					mapWordsData.put(word, new Word.WordBuilder()
								.setWord(word)
								.setTranscription(array[1])
								.setTranslation(array[2])
								.setRepeat(array[3])
								.build());
				}
				words.add(word);
			}
		}
		logger.info("  ProcessWordsModel.loadWordsDataFromFile() <<");
		return words;
	}

	public String getTranscription(String word) throws IOException {
		String transcription = "";
		for (SiteKeys siteKey : listSiteKeys) {
			GetDataFromSite getDataFromSite = new GetDataFromSite(siteKey);
			transcription = getDataFromSite.getTranscription(word);
			if (!transcription.isEmpty())
				return transcription;
		}

		return "";
	}

	public ArrayList<String> getTranslation(String word) throws IOException {
		logger.info("  ProcessWordsModel.getTranslation() >>");
		ArrayList<String> translations = new ArrayList<String>();
		for (SiteKeys siteKey : listSiteKeys) {
			GetDataFromSite getDataFromSite = new GetDataFromSite(siteKey);
			translations.add(getDataFromSite.getTranslation(word));
		}

		logger.info("  ProcessWordsModel.getTranslation() <<");
		return translations;
	}

	public void addNewWord(Word word) throws IOException {
		addWordToUnicodeFile("D:\\mny\\оепемня\\ENGLISH\\check_words\\words_new.txt", word);
		addWordToUnicodeFile("D:\\mny\\оепемня\\ENGLISH\\check_words\\words_doubt.txt", word);

	}

	private void addWordToUnicodeFile(String filePath, Word word) throws IOException {
		try (BufferedWriter bw	= new BufferedWriter(
				new FileWriter(filePath, StandardCharsets.UTF_16LE, true) ) ) {
			bw.write(word.getWord() + "\t" +
					 word.getTranscription() + "\t" +
					 word.getTranslation() + "\t" +
					 word.getRepeatNumber());
			bw.newLine();
		}
	}

	public ArrayList<String> getExamples(String word) throws IOException {
		logger.info("  ProcessWordsModel.getExamples() >>");
		ArrayList<String> examples = new ArrayList<String>();
		for (SiteKeys siteKey : listSiteKeys) {
			GetDataFromSite getDataFromSite = new GetDataFromSite(siteKey);
			examples.addAll(getDataFromSite.getListExamples(word));
		}

		logger.info("  ProcessWordsModel.getExamples() <<");
		return examples;
	}
	
	public void outputExamplesToFile(String word, String text) throws FileNotFoundException, IOException {
		String fileName = "D:\\ENGLISH. FULL\\WORDS\\examples\\" + word + ".txt";
		
		try (BufferedWriter bw	= new BufferedWriter(
				new FileWriter(fileName) ) ) {
			bw.write(text);
		}
	}

	public ArrayList<String> processIsWordsKnown(ArrayList<String> listExamples, String word) {
		listAllWords.add(word);
		
		ArrayList<String> wordsKnown = new ArrayList<String>();
		ArrayList<String> wordsUnKnown = new ArrayList<String>();
		
		for (String example : listExamples) {
			if (isSentenceContainCheckedWords(example))
				wordsKnown.add(example);
			else
				wordsUnKnown.add(example);
		}
		
		ArrayList<String> allExamples = new ArrayList<String>();
		allExamples.add("=== EXAMPLES WITH KNOWN WORDS ===");
		allExamples.addAll(wordsKnown);
		allExamples.add("\n=== EXAMPLES WITH UNKNOWN WORDS ===");
		allExamples.addAll(wordsUnKnown);		

		return allExamples;
	}
	
	public boolean isSentenceContainCheckedWords(String sentence) {
		ArrayList<String> words = getWordsFromLine(sentence);
		for (String word : words) {
			if (!listAllWords.contains(word))
				return false;
		}
		return true;
	}
	
	private ArrayList<String> getWordsFromLine(String line) {
		ArrayList<String> listWords = new ArrayList<String>();
		String newLine = replaceNotCorrectSymbols(line);

		String[] words = newLine.split(" ");
		for (String word : words) {
			if (!word.isEmpty() && (2 < word.length()))
				listWords.add(word.toLowerCase());
		}
		
		return listWords;
	}
	
	private String replaceNotCorrectSymbols(String line) {
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
		newLine = newLine.replace('■', ' ');
		newLine = newLine.replace(';', ' ');
		newLine = newLine.replace('`', ' ');
		newLine = newLine.replace(':', ' ');
		newLine = newLine.replace('⌠', ' ');
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
		newLine = newLine.replace('┘', ' ');
		newLine = newLine.replace('\t', ' ');
		
		return newLine;
	}


}
