package mny.exercises;

import java.util.function.BooleanSupplier;

public class MnyCycles {

	public int getSumFrom1ToNumber(int number) {
		int sum = 0;
		for ( int i = 1; i <= number; i++ )
			sum += i;
		
		return sum;
	}

	public int getNumberOfSequencePlusPreviousNumber(int number) {
		int previousNumber = 1;
		int currentNumber = 1;
		
		
		for ( int i = 1; i < number; i++ ) {
			int temporary = currentNumber;
			currentNumber += previousNumber;
			previousNumber = temporary;
		}
		
		return currentNumber;
	}

	public int getNumberOfArithmeticProgression(int firstNumber, int adding, int number) {
		int currentNumber = firstNumber;
		for ( int i = 1; i < number; i++ )
			currentNumber += adding;
		
		return currentNumber;
	}

	public boolean isNumberPrime(int targetNumber) {
		for ( int divider = 2; divider <= targetNumber / 2; divider++ )
			if ( 0 == targetNumber % divider )
				return false;
		
		return true;
	}

}
