package ua.engexercises.model;

import java.util.Random;
import java.lang.*;

public class RandomNumber {
	static Random generator = new Random();
	static int previous = 0;
	
	static int abs(int number) {
		if (number <0)
			return number * (-1);
		return number;
	}

	public static int getRandomNumber( int maxNumber ) {
		if (maxNumber == 1)
			return 0;
		
		int number = abs(generator.nextInt() % maxNumber);
		while ( previous == number )
			number = abs(generator.nextInt() % maxNumber);
		previous = number;
		
		return number;
	}
}
