package ua.training;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

/*
class GetDataFromCambridgeDictionary {
	private static final String address = "https://dictionary.cambridge.org/ru/%D1%81%D0%BB%D0%BE%D0%B2%D0%B0%D1%80%D1%8C/%D0%B0%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9/";
	
	static String getTranscription(String word) throws IOException {
		String transcription = getTranscriptionByAttributeValue(word);
		
		if (transcription.contains("/")) {
			if (!word.contains(" ")) {
				transcription = "";
			} else {
				String[] subWords = word.split(" ");
				transcription = "";
				for(String sub : subWords) {
					String subTranscription = getTranscriptionByAttributeValue(word);
					if (!subTranscription.contains("/")) {
						transcription += subTranscription; 
					}						
				}
			}
		}		
		return transcription;
	}
	
	static String getTranscriptionByAttributeValue(String word) throws IOException {
		Document document = Jsoup.connect(address + word).get();
		Elements elements = document.getElementsByAttributeValue("class", "ipa dipa lpr-2 lpl-1");
		return "[" + elements.first().text() + "]";
	}
	
	public static ArrayList<String> getListExamples(String word) throws IOException {
		Document document = Jsoup.connect(address + "plastic").get();
		ArrayList<String> examples = new ArrayList<String>();
		Elements elements = document.getElementsByAttributeValue("class", "eg deg");
		for (Element element : elements) {
			examples.add(element.text());
		}
		elements = document.getElementsByAttributeValue("class", "eg dexamp hax");
		for (Element element : elements) {
			examples.add(element.text());
		}

		return examples;
	}
}
*/

class GetDataFromExamplum {
	String address = "https://examplum.com/%D0%BF%D0%B5%D1%80%D0%B5%D0%B2%D0%BE%D0%B4/%D0%B0%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9-%D1%80%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B9/";
}

public class Main {
	static Document document;
	
	static SiteKeys keysCambridgeDictionary = new SiteKeys.Builder()
				.setAddress("https://dictionary.cambridge.org/ru/%D1%81%D0%BB%D0%BE%D0%B2%D0%B0%D1%80%D1%8C/%D0%B0%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9/")
				.setKeyTranscription("ipa dipa lpr-2 lpl-1")
				.setKeyExamples("eg deg")
				.setKeyExamplesExtra("eg dexamp hax")
				.setKeyTranslation(null)
				.build();

	static SiteKeys keysExamplum = new SiteKeys.Builder()
			.setAddress("https://examplum.com/%D0%BF%D0%B5%D1%80%D0%B5%D0%B2%D0%BE%D0%B4/%D0%B0%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9-%D1%80%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B9/")
			.setKeyTranscription(null)
			.setKeyExamples("res-example__src")
			.setKeyExamplesExtra(null)
			.setKeyTranslation("res-example__dest")
			.build();
	
	public static void main(String[] args) {
		try {

//			ArrayList<String> words = getListItems("words.txt");
			String word = "plastic";
			//document = Jsoup.connect("https://dictionary.cambridge.org/ru/%D1%81%D0%BB%D0%BE%D0%B2%D0%B0%D1%80%D1%8C/%D0%B0%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9/" + word).get();
			document = Jsoup.connect("https://englishlib.org/dictionary/en-ru/" + word + ".html").get();
			
			// получить весь текст сайта
//			System.out.println(document.toString());
			// получить 1-е значение по атрибуту
//			Element element = document.getElementsByAttributeValue("class", "transcription").first();
//			System.out.println(element.text());
			// получить список элементов по атрибуту
//			Elements elements = document.getElementsByAttribute("span");
//			for (Element element : elements)
//				System.out.println(element);
			// получить список элементов по тегу
			Elements elements = document.select("ul");
			for (Element element : elements)
				System.out.println(element.text());
/*
//			System.out.println("Title: " + document.selectFirst("title").text());
			ArrayList<String> examples = (ArrayList<String>) document.getElementsByAttributeValue("class", "res-example__src").eachText();
			ArrayList<String> translations = (ArrayList<String>) document.getElementsByAttributeValue("class", "res-example__dest").eachText();
			for (int i = 0; i < examples.size(); i++) {
				System.out.println(examples.get(i) + "\n\t" + translations.get(i));
			}
*/
			
/*
			String word = "plastic";
			GetDataFromSite examplumSite = new GetDataFromSite(keysExamplum);
			System.out.println(word + "\t" + examplumSite.getTranscription(word));
			ArrayList<String> examples = examplumSite.getListExamples(word);
			
			// output for test
			for (String example : examples) {
				System.out.println(example);
			}
*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

/*
	public static void getAndPrintTranscriptionAndListExamplesForWord() throws IOException {
		String word = "plastic";
		String transcription = GetDataFromCambridgeDictionary.getTranscription(word);
		System.out.println(word + "\t" + transcription);
		ArrayList<String> examples = GetDataFromCambridgeDictionary.getListExamples(word);
		System.out.println("\tlist examples: ");
		for(String example : examples) {
			System.out.println(example);
		}
	}
*/
	public static ArrayList<String> getListItems(String fileName) throws FileNotFoundException, IOException {
		ArrayList<String> listItems = new ArrayList<>();
		
		try ( BufferedReader br	= new BufferedReader(
				new FileReader( fileName ) ) ) {
			String strLine;
			while ( ( strLine = br.readLine() ) != null )
				listItems.add( strLine );			
		}
		
		return listItems;
	}

}
