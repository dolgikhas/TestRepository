package ua.engexercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ua.engexercises.model.PatternTask;

class PatternTaskTest {
	public final String listWhoKey = "LIST_WHO";
	public final String listWho1stValue = "I";
	public final String listWhomKey = "LIST_WHOM";
	public final String listWhom1stValue = "you";
	public final String patternTaskSeeSimple = "see #" + listWhoKey + "#/#" + listWhomKey + "#";
	public final String patternAnswerSeeSimple = "#" + listWhoKey + "# see #" + listWhomKey + "#.";

	@Test
	void patternTaskSeeSimple() {
		PatternTask patternData = new PatternTask.Builder()
				.setAnswer( patternAnswerSeeSimple )
				.setTask( patternTaskSeeSimple )
				.build();
		assertEquals( patternTaskSeeSimple, patternData.getTask() );
		assertEquals( patternAnswerSeeSimple, patternData.getAnswer() );
	}

}
