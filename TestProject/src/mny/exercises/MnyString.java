package mny.exercises;

public class MnyString {
	String myString;

	public MnyString(String istring) {
		myString = istring;
	}

	public void reverse() {		
		char[] charNewString = new char[ myString.length() ];
		int index = myString.length() - 1;
		for ( char letter : myString.toCharArray() )
			charNewString[ index-- ] = letter;
		myString = new String( charNewString );
	}

	public String getString() {
		return myString;
	}


}
