package mny.checkwords.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestModel {
	
	private Model model;
	private Word word;
	
	@BeforeEach
	public void initialise() {
		model = new Model();
		word = new Word.WordBuilder()
					   .setWord("word")
					   .setRepeat("1")
					   .build();		
	}
	
	
	@Test
	public void testGetRepeatNumberByOptionForMinusResultCheck() {
		assertEquals("0", model.getRepeatNumberByOption(word, ResultCheck.Minus));
	}
	
	@Test
	public void testGetRepeatNumberByOptionForMinus4ResultCheck() {
		assertEquals("0", model.getRepeatNumberByOption(word, ResultCheck.Minus4));
	}

	@Test
	public void testGetRepeatNumberByOptionForPlusResultCheck() {
		assertEquals("2", model.getRepeatNumberByOption(word, ResultCheck.Plus));
	}

	@Test
	public void testGetRepeatNumberByOptionForMinus4ResultCheckBigger4() {
		Word word = new Word.WordBuilder()
				   .setWord("word")
				   .setRepeat("6")
				   .build();	
		assertEquals("2", model.getRepeatNumberByOption(word, ResultCheck.Minus4));
	}

	@Test
	public void testGetRepeatNumberByOptionForAsteriskResultCheck() {
		Word word = new Word.WordBuilder()
				   .setWord("word")
				   .setRepeat("6")
				   .build();	
		assertEquals("-1", model.getRepeatNumberByOption(word, ResultCheck.Asterisk));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {Constants.FILE_NEW_WORDS, Constants.FILE_DOUBT_WORDS, Constants.FILE_PROCESSED_WORDS,
			Constants.FILE_NEW_WORDS_REPEAT, Constants.FILE_DOUBT_WORDS_REPEAT, Constants.FILE_PROCESSED_WORDS_REPEAT })
	public void testIsNeedToWriteFileToRepeatFileWithTrueResult(String fileName) {
		assertTrue(model.isNeedToWriteFileToRepeatFile(fileName));		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {Constants.FILE_NEW_WORDS_REPEAT_2, Constants.FILE_DOUBT_WORDS_REPEAT_2, Constants.FILE_PROCESSED_WORDS_REPEAT_2 })
	public void testIsNeedToWriteFileToRepeatFileWithFalseResult(String fileName) {
		assertFalse(model.isNeedToWriteFileToRepeatFile(fileName));		
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		Constants.FILE_NEW_WORDS_REPEAT + ", " + Constants.FILE_NEW_WORDS,
		Constants.FILE_PROCESSED_WORDS_REPEAT + ", " + Constants.FILE_PROCESSED_WORDS,
		Constants.FILE_DOUBT_WORDS_REPEAT + ", " + Constants.FILE_DOUBT_WORDS,
		Constants.FILE_NEW_WORDS_REPEAT_2 + ", " + Constants.FILE_NEW_WORDS_REPEAT,
		Constants.FILE_PROCESSED_WORDS_REPEAT_2 + ", " + Constants.FILE_PROCESSED_WORDS_REPEAT,
		Constants.FILE_DOUBT_WORDS_REPEAT_2 + ", " + Constants.FILE_DOUBT_WORDS_REPEAT,
	})
	public void testGetRepeatFileName(String resultFile, String fileWords) {
		try {
			assertEquals(resultFile, model.getRepeatFileName(fileWords));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {Constants.FILE_NEW_WORDS, Constants.FILE_DOUBT_WORDS, Constants.FILE_PROCESSED_WORDS,
			Constants.FILE_NEW_WORDS_REPEAT, Constants.FILE_DOUBT_WORDS_REPEAT, Constants.FILE_PROCESSED_WORDS_REPEAT })
	public void testIsWordCompletedRepeatWithFalseResult(String fileName) {
		assertFalse(model.isWordCompletedRepeat(word, fileName));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})
	public void testIsWordCompletedRepeatForNewWordsWithFalseResult(int repeatNumber) {
		Word word = new Word.WordBuilder()
				   .setWord("word")
				   .setRepeat(Integer.toString(repeatNumber))
				   .build();	
		assertFalse(model.isWordCompletedRepeat(word, Constants.FILE_NEW_WORDS));
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
	public void testIsWordCompletedRepeatForDoubtWordsWithFalseResult(int repeatNumber) {
		Word word = new Word.WordBuilder()
				   .setWord("word")
				   .setRepeat(Integer.toString(repeatNumber))
				   .build();	
		assertFalse(model.isWordCompletedRepeat(word, Constants.FILE_DOUBT_WORDS));
	}
	
	@Test
	public void testIsWordCompletedRepeatForNewWordsWithTrueResult() {
		Word word = new Word.WordBuilder()
				   .setWord("word")
				   .setRepeat(Integer.toString(Constants.REPEAT_NUMBER_NEW_WORDS))
				   .build();	
		assertTrue(model.isWordCompletedRepeat(word, Constants.FILE_NEW_WORDS));
	}
	
	@Test
	public void testIsWordCompletedRepeatForDoubtWordsWithTrueResult() {
		Word word = new Word.WordBuilder()
				   .setWord("word")
				   .setRepeat(Integer.toString(Constants.REPEAT_NUMBER_DOUBT_WORDS))
				   .build();	
		assertTrue(model.isWordCompletedRepeat(word, Constants.FILE_DOUBT_WORDS));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {Constants.FILE_NEW_WORDS_REPEAT, Constants.FILE_DOUBT_WORDS_REPEAT, Constants.FILE_PROCESSED_WORDS_REPEAT,
			Constants.FILE_NEW_WORDS_REPEAT_2, Constants.FILE_DOUBT_WORDS_REPEAT_2, Constants.FILE_PROCESSED_WORDS_REPEAT_2})
	public void testIsWordCompletedRepeatForOtherWordsWithTrueResult(String fileName) {
		Word word = new Word.WordBuilder()
				   .setWord("word")
				   .setRepeat(Integer.toString(Constants.REPEAT_NUMBER_REPEAT_WORDS))
				   .build();	
		assertTrue(model.isWordCompletedRepeat(word, fileName));
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		Constants.REPEAT_NUMBER_NEW_WORDS + ", " + Constants.FILE_NEW_WORDS,
		Constants.REPEAT_NUMBER_REPEAT_WORDS + ", " + Constants.FILE_PROCESSED_WORDS,
		Constants.REPEAT_NUMBER_DOUBT_WORDS + ", " + Constants.FILE_DOUBT_WORDS,
		Constants.REPEAT_NUMBER_REPEAT_WORDS + ", " + Constants.FILE_NEW_WORDS_REPEAT,
		Constants.REPEAT_NUMBER_REPEAT_WORDS + ", " + Constants.FILE_PROCESSED_WORDS_REPEAT,
		Constants.REPEAT_NUMBER_REPEAT_WORDS + ", " + Constants.FILE_DOUBT_WORDS_REPEAT,
		Constants.REPEAT_NUMBER_REPEAT_WORDS + ", " + Constants.FILE_NEW_WORDS_REPEAT_2,
		Constants.REPEAT_NUMBER_REPEAT_WORDS + ", " + Constants.FILE_PROCESSED_WORDS_REPEAT_2,
		Constants.REPEAT_NUMBER_REPEAT_WORDS + ", " + Constants.FILE_DOUBT_WORDS_REPEAT_2
	})
	public void testGetMaxNumberRepeat(int repeatNumber, String fileName) {
		assertEquals(repeatNumber, model.getMaxNumberRepeat(fileName));		
	}

}
