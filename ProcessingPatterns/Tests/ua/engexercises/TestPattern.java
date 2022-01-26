package ua.engexercises;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPattern {
	DBGetData dataDB;
	RandomNumber randomNumber;
	CommonElements commonElements;
	String listWhoKey = "LIST_WHO";
	String listWho1stValue = "I";
	String listWhomKey = "LIST_WHOM";
	String listWhom1stValue = "you";
	
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
		randomNumber = new RandomNumber();
		commonElements = new CommonElements( dataDB, randomNumber );
	}

	@Test
	void testCommonElementsGetValue() {		
		assertEquals( "I", commonElements.getRandomValue(listWhoKey) );
		assertEquals( "you", commonElements.getRandomValue(listWhomKey) );
	}
	
	@Test
	void testProcessingPattern() {
		ProcessingPattern processingPattern = new ProcessingPattern(
				"", "", commonElements );
		assertEquals( "see: I/you", processingPattern.getTask() );
		assertEquals( "I see you", processingPattern.getAnswer() );
	}
}
