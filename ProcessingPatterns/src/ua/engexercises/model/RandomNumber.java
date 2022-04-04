 package ua.engexercises.model;

import java.util.Random;

public class RandomNumber {
	static Random generator = new Random();
	static int previous = 0;

	public int getRandomNumber( int maxNumber ) {
		int number = Math.abs(generator.nextInt()) % maxNumber;
		while ( previous == number ) {
			System.out.println( "\t\tget random number equal to previous: " + number );
			number = Math.abs(generator.nextInt()) % maxNumber;
		}
		previous = number;
		return number;
	}
}
