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
		String subject = "";
		
		while ( -1 != task.indexOf( HASH_SYMBOL ) ) {
			String variable = ProcessingExpressions.getNextVariable( task );
			String randomElement = commonElements.getRandomValue( variable );
			
			if ( subject.isEmpty() )
				subject = randomElement;
			else if ( isSubjectPersonalPronoun(subject) ) {
				while ( isSubjectPersonalPronounEqualToObject(subject, randomElement)) {
					randomElement = commonElements.getRandomValue( variable );
					System.out.println( "\tsubject is equal to object as personal pronoun" );
				}
			}
			
			task = ProcessingExpressions.replaceVariable( task, randomElement );
			answer = ProcessingExpressions.replaceVariable( answer, randomElement );
		}

		return new PatternTask.Builder()
							  .setAnswer( answer )
							  .setTask( task )
							  .build();
	}

	private static boolean isSubjectPersonalPronounEqualToObject(String subject, String objectStr) {
		if ( subject.equals("I") && objectStr.equals("me") )
			return true;
		if ( subject.equals("I") && objectStr.equals("us") )
			return true;
		if ( subject.equals("you") && objectStr.equals("you") )
			return true;
		if ( subject.equals("he") && objectStr.equals("him") )
			return true;
		if ( subject.equals("she") && objectStr.equals("her") )
			return true;
		if ( subject.equals("it") && objectStr.equals("it") )
			return true;
		if ( subject.equals("we") && objectStr.equals("us") )
			return true;
		if ( subject.equals("we") && objectStr.equals("me") )
			return true;
		if ( subject.equals("they") && objectStr.equals("them") )
			return true;
			
			
		return false;
	}

	private static boolean isSubjectPersonalPronoun(String subject) {
		if ( subject.equals("I") || subject.equals("you") || subject.equals("he")
			|| subject.equals("she") || subject.equals("it") || subject.equals("we")
			|| subject.equals("they") )
			return true;
		return false;
	}
}