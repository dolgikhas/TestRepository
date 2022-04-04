package ua.engexercises.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcessingPattern {
	public static final String HASH_SYMBOL = "#";
	PatternTask patternTask;
	CommonElements commonElements;
	RandomNumber randomNumbers;
	
	private ProcessingPattern( Builder builder ) {
		this.patternTask = new PatternTask.Builder()
								.setAnswer(builder.answer)
								.setTask(builder.task)
								.build();
		
		this.commonElements = builder.elements;
		this.randomNumbers = builder.numbers;
	}
	
	public static class Builder {
		private String answer;
		private String task;
		private CommonElements elements;
		private RandomNumber numbers;
		
		public Builder setAnswer(String answer) {
			this.answer = answer;
			return this;
		}

		public Builder setTask(String task) {
			this.task = task;
			return this;
		}

		public Builder setElements(CommonElements elements) {
			this.elements = elements;
			return this;
		}

		public Builder setRandomNumbers(RandomNumber randomNumber) {
			this.numbers = randomNumber;
			return this;
		}
		
		public ProcessingPattern build() {
			return new ProcessingPattern( this );
		}

	}

	public PatternTask getTaskAndAnswer() throws Exception {
		return ProcessingExpressions.replaceVariablesByCommonElements(
					patternTask, commonElements );
	}

}
