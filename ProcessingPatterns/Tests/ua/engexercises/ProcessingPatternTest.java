package ua.engexercises;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.engexercises.model.CommonElements;
import ua.engexercises.model.DBGetData;
import ua.engexercises.model.PatternTask;
import ua.engexercises.model.ProcessingExpressions;
import ua.engexercises.model.ProcessingPattern;
import ua.engexercises.model.RandomNumber;

class TestRandomNumber extends RandomNumber {
	public static int getRandomNumber( int maxNumber ) {
		return 0;
	}
}

public class ProcessingPatternTest {
	ProcessingPatternsTestData testData = new ProcessingPatternsTestData();
	
	@Test
	void testProcessingPatternGetTask() throws Exception {
		PatternTask patternData = new ProcessingPattern.Builder()
				.setAnswer(testData.patternAnswerSeeSimple)
				.setTask(testData.patternTaskSeeSimple)
				.setElements(testData.commonElements)
				.build()
				.getTaskAndAnswer();
		assertEquals( "(I) to see (you)", patternData.getTask() );
		assertEquals( "I see you.", patternData.getAnswer() );
	}
}
