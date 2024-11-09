package mny.checkwords;

import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import mny.checkwords.model.*;
import mny.checkwords.controller.*;
import mny.checkwords.view.*;
import mny.checkwords.processwords.*;


import java.io.*;
import java.io.*;
import javazoom.jl.player.advanced.*;
import javazoom.jl.player.*;

public class CheckWordsMain {
	private final static Logger logger = Logger.getLogger("CheckWords");

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		
		Controller controller = new Controller(model, view, logger);
		controller.processUser();				
	}

	private static void processWordsFiles() {
		ProcessWordsFiles processWordsFiles = new ProcessWordsFiles("D:\\ENGLISH. FULL\\WORDS\\words\\");
		
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_new.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_new.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_processed.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_processed.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_checked.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_checked.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_doubt.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_doubt.txt");
	}

}
