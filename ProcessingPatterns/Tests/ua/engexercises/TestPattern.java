package ua.engexercises;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRandomNumber extends RandomNumber {
	public int getRandomNumber( int maxNumber ) {
		return 0;
	}
}

class TestPattern {
	DBGetData dataDB;
	RandomNumber randomNumber;
	CommonElements commonElements;
	public final String listWhoKey = "LIST_WHO";
	public final String listWho1stValue = "I";
	public final String listWhomKey = "LIST_WHOM";
	public final String listWhom1stValue = "you";
	public final String patternTaskSeeSimple = "see #" + listWhoKey + "#/#" + listWhomKey + "#";
	public final String patternAnswerSeeSimple = "#" + listWhoKey + "# see #" + listWhomKey + "#.";
	
	@BeforeEach
	public void InitializeVariables() {
		HashMap<String, ArrayList<String>> mapListCommonElements =
				new HashMap<>();
		ArrayList<String> listWho = new ArrayList<>();
		listWho.add( listWho1stValue );
		mapListCommonElements.put( listWhoKey, listWho );
		ArrayList<String> listWhom = new ArrayList<>();
		listWhom.add( listWhom1stValue );
		mapListCommonElements.put( listWhomKey, listWhom );
		
		dataDB = new DBGetData( mapListCommonElements );
		randomNumber = new TestRandomNumber();
		commonElements = new CommonElements( dataDB, randomNumber );
	}
	
	@Test
	void testDBGetData() throws Exception {
		String key = listWho1stValue;
		ArrayList<String> listWho = dataDB.getListData(listWhoKey);
		assertEquals( listWho.get(0), listWho1stValue );		
		assertTrue( listWho.contains(key) );
		
		int amountElements = listWho.size();
		assertEquals( listWho1stValue, listWho.get(
				randomNumber.getRandomNumber(amountElements)) );
	}

	@Test
	void testCommonElementsGetValue() throws Exception {		
		assertEquals( "I", commonElements.getRandomValue(listWhoKey) );
		assertEquals( "you", commonElements.getRandomValue(listWhomKey) );
	}

	@Test
	void testProcessingExpressionsReplaceVariable() throws Exception {
		String result = "see #" + listWhoKey + "#/#" + listWhomKey + "#";
		result = ProcessingExpressions.replaceVariable( result, "" );
		assertEquals( "see /#" + listWhomKey + "#", result );		
		result = ProcessingExpressions.replaceVariable( result, "" );
		assertEquals( "see /", result );		
	}

	@Test
	void testProcessingExpressionsWithTaskSeePatternAndLishWhoKey() throws Exception {
		String result = "see #" + listWhoKey + "#/#" + listWhomKey + "#";
		String variable = ProcessingExpressions.getNextVariable(result);
		assertEquals( listWhoKey, variable );
		result = ProcessingExpressions.replaceVariable( result, "" );
		assertEquals( listWhomKey, ProcessingExpressions.getNextVariable(result) );		
	}

	@Test
	void testProcessingExpressionsGetNextVariableAndReplaceVariable() throws Exception {
		String result = "see #" + listWhoKey + "#/#" + listWhomKey + "#";
		String variable = ProcessingExpressions.getNextVariable(result);
		assertEquals( listWhoKey, variable );
		result = ProcessingExpressions.replaceVariable( result, "" );
		assertEquals( listWhomKey, ProcessingExpressions.getNextVariable(result) );		
	}

	@Test
	void testProcessingExpressionsGetNextVariable() throws Exception {
		assertEquals( listWhoKey,
			ProcessingExpressions.getNextVariable(patternTaskSeeSimple) );
		assertEquals( listWhoKey,
			ProcessingExpressions.getNextVariable(patternAnswerSeeSimple) );
	}
	
	@Test
	void testProcessingPatternGetTask() throws Exception {
		ProcessingPattern processingPattern = new ProcessingPattern(
				patternAnswerSeeSimple, patternTaskSeeSimple, 
				commonElements, randomNumber );
		assertEquals( "see I/you", processingPattern.getTask() );
	}
	
	@Test
	void testProcessingPatternGetAnswer() throws Exception {
		ProcessingPattern processingPattern = new ProcessingPattern(
				patternAnswerSeeSimple, patternTaskSeeSimple, 
				commonElements, randomNumber );
		assertEquals( "I see you.", processingPattern.getAnswer() );
	}
}
