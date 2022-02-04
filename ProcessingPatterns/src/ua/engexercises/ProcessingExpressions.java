package ua.engexercises;

class ProcessingExpressions {
	public static final String HASH_SYMBOL = "#";

	public static String getNextVariable(String expression) {
		int start = expression.indexOf( ProcessingPattern.HASH_SYMBOL );
		int end = expression.indexOf( ProcessingPattern.HASH_SYMBOL, start + 1 );
		return expression.substring( start + 1, end );
	}

	public static String replaceVariable(String expression, String value) {
		return expression.replace( HASH_SYMBOL + getNextVariable( expression ) + HASH_SYMBOL, value );
	}

	public static String replaceVariablesByCommonElements(String expression,
							CommonElements commonElements) throws Exception {
		String result = expression;
		while ( -1 != result.indexOf( HASH_SYMBOL ) ) {
			String variable = getNextVariable( result );
			String randomElement = commonElements.getRandomValue( variable );
			result = replaceVariable( result, randomElement );
		}
		
		return result;
	}
}