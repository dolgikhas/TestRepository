package mny.processwords.sitedata;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetDataFromSite {
	SiteKeys siteKeys;
	
	public GetDataFromSite(SiteKeys siteKeys) {
		this.siteKeys = siteKeys;
	}
	
	public String getTranscription(String word) throws IOException {
		if (null == siteKeys.getKeyTranscription()) {
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
		Document document = Jsoup.connect(siteKeys.getAddress().replaceAll("WORD", word)).get();
		Elements elements = document.getElementsByAttributeValue("class", siteKeys.getKeyTranscription());
		return "[" + elements.first().text() + "]";
	}
	
	public ArrayList<String> getListExamples(String word) throws IOException {
		ArrayList<String> examples = new ArrayList<String>();
		getListValuesByAttributeValue(examples, word, siteKeys.getKeyExamples());
		
		if (null != siteKeys.getKeyExamplesExtra()) {
			getListValuesByAttributeValue(examples, word, siteKeys.getKeyExamplesExtra());
		}
		
		if (null != siteKeys.getKeyExamplesTranslation()) {
			
		}

		return examples;
	}
	
	public void getListValuesByAttributeValue(ArrayList<String> values, String word, String attribute) throws IOException {
		Document document = Jsoup.connect(siteKeys.getAddress().replaceAll("WORD", word)).get();
		Elements elements = document.getElementsByAttributeValue("class", attribute);
		if (null == siteKeys.getKeyExamplesTranslation()) {
			for (Element element : elements) {
				values.add(element.text());
			}
		} else {
			Elements translations = document.getElementsByAttributeValue("class", siteKeys.getKeyExamplesTranslation());
			for (int i = 0; i < elements.size(); i++) {
				values.add(elements.get(i).text() + "\t" + translations.get(i).text());
			}

		}
		
	}

	public String getTranslation(String word) throws IOException {
		ArrayList<String> listTranslations = new ArrayList<String>();
		getListValuesByAttributeValue(listTranslations, word, siteKeys.getKeyTranslation());
		
		String translations = "";
		for (String value : listTranslations) {
			translations += value + "; ";
		}

		return translations;
	}
}