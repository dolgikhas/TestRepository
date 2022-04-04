package ua.engexercises.model;

public class PatternTask {
	
	public static class Builder {
		String task;
		String answer;

		public String getTask() {
			return task;
		}

		public String getAnswer() {
			return answer;
		}


		public Builder setTask(String task) {
			this.task = task;
			return this;
		}

		public Builder setAnswer(String answer) {
			this.answer = answer;
			return this;
		}

		public PatternTask build() {
			return new PatternTask( this );
		}
	}

	String task;
	String answer;
	
	private PatternTask() {}

	public PatternTask(Builder builder) {
		task = builder.getTask();
		answer = builder.getAnswer();
	}

	public String getTask() {
		return task;
	}

	public String getAnswer() {
		return answer;
	}

}
