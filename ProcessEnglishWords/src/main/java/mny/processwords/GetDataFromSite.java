package mny.processwords;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetDataFromSite {
	private final String address;
	private final String keyTranscription;
	private final String keyExamples;
	private final String keyExamplesExtra;
	private final String keyTranslation;
	
	GetDataFromSite(SiteKeys siteKeys) {
		address = siteKeys.getAddress();
		keyTranscription = siteKeys.getKeyTranscription();
		keyExamples = siteKeys.getKeyExamples();
		keyExamplesExtra = siteKeys.getKeyExamplesExtra();
		keyTranslation = siteKeys.getKeyTranslation();
	}
	
	String getTranscription(String word) throws IOException {
		if (null == keyTranscription) {
			return "";
		}
		
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
	
	String getTranscriptionByAttributeValue(String word) throws IOException {
		Document document = Jsoup.connect(address.replaceAll("WORD", word)).get();
		Elements elements = document.getElementsByAttributeValue("class", keyTranscription);
		return "[" + elements.first().text() + "]";
	}
	
	public ArrayList<String> getListExamples(String word) throws IOException {
		ArrayList<String> examples = new ArrayList<String>();
		getListExamplesByAttributeValue(examples, word, keyExamples);
		
		if (null != keyExamplesExtra) {
			getListExamplesByAttributeValue(examples, word, keyExamplesExtra);
		}

		return examples;
	}
	
	public void getListExamplesByAttributeValue(ArrayList<String> examples, String word, String attribute) throws IOException {
		Document document = Jsoup.connect(address.replaceAll("WORD", word)).get();
		Elements elements = document.getElementsByAttributeValue("class", keyExamples);
		if (null == keyTranslation) {
			for (Element element : elements) {
				examples.add(element.text());
			}
		} else {
			Elements translations = document.getElementsByAttributeValue("class", keyTranslation);
			for (int i = 0; i < elements.size(); i++) {
				examples.add(elements.get(i).text() + "\t" + translations.get(i).text());
			}

		}
		
	}
}