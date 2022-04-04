package ua.engexercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommonElementsTest {
	ProcessingPatternsTestData testData = new ProcessingPatternsTestData();
	
	@Test
	void testCommonElementsGetValue() throws Exception {		
		assertEquals( "I", testData.commonElements.getRandomValue(
				testData.listWhoKey) );
		assertEquals( "you", testData.commonElements.getRandomValue(
				testData.listWhomKey) );
	}

}
