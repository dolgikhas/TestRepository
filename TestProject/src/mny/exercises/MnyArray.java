package mny.exercises;

import java.util.function.BooleanSupplier;

public class MnyArray {
	
	int intArray[];

	public MnyArray(int[] array1) {
		intArray = array1;
	}

	public int getMinimumNumber() {
		int minimum = intArray[ 0 ];
		
		for ( int element : intArray )
			if ( minimum > element )
				minimum = element;
		
		return minimum;
	}

	public int getMaximumNumber() {
		int maximum = intArray[ 0 ];
		
		for ( int element : intArray )
			if ( maximum < element )
				maximum = element;
		
		return maximum;
	}

	public double getAverageNumber() {
		int sum = 0;
		
		for ( int element : intArray )
			sum += element;
		
		return (double)sum / intArray.length;
	}

	public boolean isArrayEqual(int[] arrayForCompare) {
		if ( intArray.length != arrayForCompare.length )
			return false;
		
		for ( int index = 0; index < intArray.length; index++ )
			if ( intArray[ index ] != arrayForCompare[ index ] )
				return false;
		
		return true;
	}

	public void sortArrayByBubbleSorting() {
		for ( int sorting = 0; sorting < intArray.length - 1; sorting++ )
			for ( int index = 0; index < intArray.length - 1; index++ )
				if ( intArray[index] > intArray[index + 1] ) {
					int temp = intArray[index];
					intArray[index] = intArray[index + 1];
					intArray[index + 1] = temp;
				}
		
	}
	
	public String toString() {
		String outString = "[ ";
		
		for ( int element : intArray ) {
			if ( element != intArray[0] )
				outString += ", ";
			outString += element;
		}

		outString += " ]";
		
		return outString;
	}

	public void removeNumberFromArray( int number ) {
		int newIntArray[] = new int[ intArray.length - getAmountNumbers(number)];
		
		int index = 0;
		for ( int element : intArray )
			if ( element != number )
				newIntArray[ index++ ] = element;
		
		intArray = newIntArray;		
	}

	public int getAmountNumbers( int number ) {
		int amount = 0;
		
		for ( int element : intArray )
			if ( element == number )
				amount++;
		
		return amount;
	}

	public Integer getPositionOfFirstNumber(int number) {
		for ( int index = 0; index < intArray.length; index++ );
//			if ( intArray[ index ] == number )
//				return index;
//		
		return -1;
	}

}
