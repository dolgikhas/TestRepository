 package ua.engexercises.model;

import java.util.Random;

public class RandomNumber {
	static Random generator = new Random();
	static int previous = 0;

	public static int getRandomNumber( int maxNumber ) {
		int number = Math.abs(generator.nextInt()) % maxNumber;
		while ( previous == number )
			number = Math.abs(generator.nextInt()) % maxNumber;
		previous = number;
		return number;
	}
}
