package ua.engexercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ua.engexercises.model.ProcessingExpressions;

class ProcessingExpressionsTest {
	ProcessingPatternsTestData testData = new ProcessingPatternsTestData();
	
	@Test
	void testProcessingExpressionsReplaceVariable() throws Exception {
		String result = "see #" + testData.listWhoKey +
						"#/#" + testData.listWhomKey + "#";
		result = ProcessingExpressions.replaceVariable( result, "" );
		assertEquals( "see /#" + testData.listWhomKey + "#",
						result );		
		result = ProcessingExpressions.replaceVariable( result, "" );
		assertEquals( "see /", result );		
	}

	@Test
	void testProcessingExpressionsWithTaskSeePatternAndLishWhoKey() throws Exception {
		String result = "see #" + testData.listWhoKey +
						"#/#" + testData.listWhomKey + "#";
		String variable = ProcessingExpressions.getNextVariable(result);
		assertEquals( testData.listWhoKey, variable );
		result = ProcessingExpressions.replaceVariable( result, "" );
		assertEquals( testData.listWhomKey,
						ProcessingExpressions.getNextVariable(result) );		
	}

	@Test
	void testProcessingExpressionsGetNextVariableAndReplaceVariable() throws Exception {
		String result = "see #" + testData.listWhoKey +
					"#/#" + testData.listWhomKey + "#";
		String variable = ProcessingExpressions.getNextVariable(result);
		assertEquals( testData.listWhoKey, variable );
		result = ProcessingExpressions.replaceVariable( result, "" );
		assertEquals( testData.listWhomKey,
				ProcessingExpressions.getNextVariable(result) );		
	}

	@Test
	void testProcessingExpressionsGetNextVariable() throws Exception {
		assertEquals( testData.listWhoKey,
			ProcessingExpressions.getNextVariable(testData.patternTaskSeeSimple) );
		assertEquals( testData.listWhoKey,
			ProcessingExpressions.getNextVariable(testData.patternAnswerSeeSimple) );
	}
	
}
