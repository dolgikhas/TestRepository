package mny.checkwords.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import mny.checkwords.model.Constants;

public class SoundExamples {
	private ArrayList<String> examples;
	private String word;
	private int currentExample = 0;
	
	public SoundExamples(String word, ArrayList<String> examples) {
		this.word = word;
		this.examples = examples;
	}	
	
	private void playExample(String sourceExample) throws FileNotFoundException, JavaLayerException {
		String processedExample = processExample(sourceExample);
		String soundPath = Constants.PATH_SOUNDS + word + "_" + processedExample + ".mp3";
		if (processedExample.equals(word + " example")) {
			soundPath = Constants.PATH_SOUNDS + processedExample + ".mp3";
		}
		
		FileInputStream fileInputStream = new FileInputStream(soundPath);
		Player playMP3 = new Player(fileInputStream);
		playMP3.play();
	}
	
	public static String processExample(String example) {		
		example = correctExampleForSubstring(example, ":");
		example = correctExampleForSubstring(example, "\"");
		example = correctExampleForSubstring(example, "?");
		example = correctExampleForSubstring(example, ".");

		return example;
	}

	private static String correctExampleForSubstring(String example, String subSting) {
		while (-1 != example.indexOf(subSting)) {
			example = example.replace(subSting, "");
		}
		
		return example;
	}

	public String getProcessedFirstExample() {
		return processExample(examples.get(0));
	}

	public void playCurrentExample() throws FileNotFoundException, JavaLayerException {
		playExample(examples.get(currentExample));
	}

	public void playNextExample() throws FileNotFoundException, JavaLayerException {
		playExample(examples.get(++currentExample));
	}

	public boolean isNextExample() {
		return (currentExample + 1) < examples.size();
	}

	public void playFirstExample() throws FileNotFoundException, JavaLayerException {
		playExample(examples.get(currentExample));
	}
}
