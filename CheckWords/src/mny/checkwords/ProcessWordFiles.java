package mny.checkwords;

import mny.checkwords.processwords.*;

public class ProcessWordFiles {

	public static void main(String[] args) {
		ProcessWordsFiles processWordsFiles = new ProcessWordsFiles("D:\\ENGLISH. FULL\\WORDS\\words\\");
		
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_new.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_new.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_processed.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_processed.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_checked.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_checked.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_doubt.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_doubt.txt");
		
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_new_repeat.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_new_repeat.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_processed_repeat.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_processed_repeat.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_doubt_repeat.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_doubt_repeat.txt");

		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_new_repeat_2.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_new_repeat_2.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_processed_repeat_2.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_processed_repeat_2.txt");
		processWordsFiles.createWordsFiles("D:\\ENGLISH. FULL\\WORDS\\check_words\\words_doubt_repeat_2.txt",
				"D:\\ENGLISH. FULL\\WORDS\\words_doubt_repeat_2.txt");	}

}
