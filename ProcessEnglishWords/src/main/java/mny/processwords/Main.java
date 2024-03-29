package mny.processwords;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import mny.processwords.sitedata.SiteKeys;

public class Main {
	static Document document;

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
	
	public static void main(String[] args) {
//		getDataFromSite();
//		
//		String path = "D:\\MNY\\�������\\ENGLISH\\check_words\\";
//		File dir = new File(path); //path ��������� �� ����������
//		File[] arrFiles = dir.listFiles();
//		List<File> lst = Arrays.asList(arrFiles);
//		for (File file : lst) {
//			System.out.println(file);
//		}
		
        Document document;
		try {
			System.out.println("before get document");
			
			document = Jsoup.connect("https://daso.archives.gov.ua/wp-content/earchives/metrika1.php").get();
			
			System.out.println("before get elements by attribute value");
			
			ArrayList<String> elements = (ArrayList<String>) document.getElementsByAttribute("li").eachText();
			for (int i = 0; i < elements.size(); i++) {
				System.out.println(elements.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n\n\nend application");
	}

	public static void getDataFromSite() {
		try {
/*
			ArrayList<String> words = getListItems("words.txt");
			for (String word : words) {
				String transcription = "";
				ArrayList<String> listExamplesFull = new ArrayList<String>();
				for (SiteKeys siteKey : listSiteKeys) {
					GetDataFromSite getDataFromSite = new GetDataFromSite(siteKey);
					if (transcription.isEmpty())
						transcription = getDataFromSite.getTranscription(word);

					ArrayList<String> examples = getDataFromSite.getListExamples(word);
					listExamplesFull.addAll(examples);
				}
				
//				System.out.println(word + "\t" + transcription);
//				for (String example : listExamplesFull) {
//					System.out.println(example);
//				}
//				System.out.println("\n\n\n");
				outputExamplesToFile(word, listExamplesFull);				
				System.out.println(word + "\t" + transcription + "\tprocessed!");
			}
*/
//			String word = "awkward";
//			document = Jsoup.connect("https://context.reverso.net/%D0%BF%D0%B5%D1%80%D0%B5%D0%B2%D0%BE%D0%B4/" +
//						"%D0%B0%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9-%D1%80%D1%83%D1%81%D1%" +
//						"81%D0%BA%D0%B8%D0%B9/" + word).get();
			
			// get all document data
//			System.out.println(document.toString());
			// get transcription by key
//			String transcription = document.getElementsByAttributeValue("class", "transcription").first().text();
//			System.out.println("transcription: " + transcription);
			// get elements by attribute
//			Elements elements = document.getElementsByAttribute("span");
//			for (Element element : elements)
//				System.out.println(element);
			// get elements by attribute (head)
//			Elements elements = document.select("li");
//			for (Element element : elements)
//				System.out.println(element.text());
		
		// keysCambridgeDictionary
		// keysExamplum
		// keysEnglishlib		
		// keysContextReverso

		String word = "sob";
		SiteKeys siteKeys = keysCambridgeDictionary;
		System.out.println("Cambridge Dictionary");
		outputValuesByAttribute(word, siteKeys, siteKeys.getKeyTranslation());

		siteKeys = keysExamplum;
		System.out.println("\nExamplum");
		outputValuesByAttribute(word, siteKeys, siteKeys.getKeyTranslation());

		siteKeys = keysEnglishlib;
		System.out.println("\nEnglishlib");
		outputValuesByAttribute(word, siteKeys, siteKeys.getKeyTranslation());

		siteKeys = keysContextReverso;
		System.out.println("\nContextReverso");
		outputValuesByAttribute(word, siteKeys, siteKeys.getKeyTranslation());
		
		
////			System.out.println("Title: " + document.selectFirst("title").text());
//			ArrayList<String> examples = (ArrayList<String>) document.getElementsByAttributeValue("class", "res-example__src").eachText();
//			ArrayList<String> translations = (ArrayList<String>) document.getElementsByAttributeValue("class", "res-example__dest").eachText();
//			for (int i = 0; i < examples.size(); i++) {
//				System.out.println(examples.get(i) + "\n\t" + translations.get(i));
//			}
//			String word = "awkward";
//			GetDataFromSite examplumSite = new GetDataFromSite(keysContextReverso);
//			System.out.println(word + "\t" + examplumSite.getTranscription(word));
//			ArrayList<String> examples = examplumSite.getListExamples(word);
//			// output for test
//			for (String example : examples) {
//				System.out.println(example);
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void outputValuesByAttribute(String word, SiteKeys siteKeys, String key) throws IOException {
		System.out.println(siteKeys.getAddress().replace("WORD", word));
		document = Jsoup.connect(siteKeys.getAddress().replace("WORD", word)).get();

//		// get list elements and its data for attribute and value
		Elements elements = document.getElementsByAttributeValue("class", key);
		System.out.println("get elements by attibute value");
		for (Element element : elements)
			System.out.println(element.text());
		System.out.println("\nend output elements");
	}
	
	public static ArrayList<String> getListItems(String fileName) throws FileNotFoundException, IOException {
		ArrayList<String> listItems = new ArrayList<String>();
		
		try (BufferedReader br	= new BufferedReader(
				new FileReader( fileName ) ) ) {
			String strLine;
			while ( ( strLine = br.readLine() ) != null )
				listItems.add( strLine );			
		}
		
		return listItems;
	}
	
	public static void outputExamplesToFile(String word, ArrayList<String> examples) throws FileNotFoundException, IOException {
		ArrayList<String> listItems = new ArrayList<String>();
		String fileName = "examples\\" + word + ".txt";
		
		try (BufferedWriter bw	= new BufferedWriter(
				new FileWriter(fileName) ) ) {
			String strLine;
			for (String example : examples) {
				bw.write(example);
				bw.newLine();
			}
		}
	}

}
