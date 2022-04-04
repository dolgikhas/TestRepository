package ua.engexercises;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DBGetDataTest {
	ProcessingPatternsTestData testData = new ProcessingPatternsTestData();

	@Test
	void testDBGetData() throws Exception {
		String key = testData.listWho1stValue;
		ArrayList<String> listWho = testData.dataDB.getListData( testData.listWhoKey);
		assertEquals( listWho.get(0), testData.listWho1stValue );		
		assertTrue( listWho.contains(key) );
		
		int amountElements = listWho.size();
		assertEquals( testData.listWho1stValue, listWho.get(
				testData.randomNumber.getRandomNumber(amountElements)) );
	}

}
