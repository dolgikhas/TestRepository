package ua.engexercises;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcessingPattern {
	public static final String HASH_SYMBOL = "#";
	String answerPattern;
	String taskPattern;
	CommonElements commonElements;
	RandomNumber randomNumbers;

	public ProcessingPattern(String answerPattern, String taskPattern, CommonElements commonElements,
			RandomNumber randomNumbers) {
		this.answerPattern = answerPattern;
		this.taskPattern = taskPattern;
		this.commonElements = commonElements;
		this.randomNumbers = randomNumbers;
	}

	public String getTask() throws Exception {
		return ProcessingExpressions.replaceVariablesByCommonElements(
							taskPattern, commonElements );
	}

	public String getAnswer() throws Exception {
		return ProcessingExpressions.replaceVariablesByCommonElements(
				answerPattern, commonElements );
	}
}
