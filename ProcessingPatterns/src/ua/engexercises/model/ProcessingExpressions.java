package ua.engexercises.model;

public class ProcessingExpressions {
	public static final String HASH_SYMBOL = "#";

	public static String getNextVariable(String expression) {
		int start = expression.indexOf( ProcessingPattern.HASH_SYMBOL );
		int end = expression.indexOf( ProcessingPattern.HASH_SYMBOL, start + 1 );
		return expression.substring( start + 1, end );
	}

	public static String replaceVariable(String expression, String value) {
		return expression.replace( HASH_SYMBOL + getNextVariable( expression )
				+ HASH_SYMBOL, value );
	}

	public static PatternTask replaceVariablesByCommonElements(PatternTask patternData,
							CommonElements commonElements) throws Exception {
		String task = patternData.getTask();
		String answer = patternData.getAnswer();
		
		while ( -1 != task.indexOf( HASH_SYMBOL ) ) {
			String variable = ProcessingExpressions.getNextVariable( task );
			String randomElement = commonElements.getRandomValue( variable );
			task = ProcessingExpressions.replaceVariable( task, randomElement );
			answer = ProcessingExpressions.replaceVariable( answer, randomElement );
		}

		return new PatternTask.Builder()
				.setAnswer( answer )
				.setTask( task )
				.build();
	}
}