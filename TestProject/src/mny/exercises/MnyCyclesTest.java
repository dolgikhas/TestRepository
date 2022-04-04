package mny.exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mny.tdd.hangman.Hangman;

public class MnyCyclesTest {
	
	MnyCycles cycles = new MnyCycles();

	@Test
	void testMnyCyclesSumFrom1To3() {
		int sum = cycles.getSumFrom1ToNumber( 3 );
		assertEquals( sum, 6 );
	}
	
	@Test
	void testMnyCyclesSumFrom1To4() {
		int sum = cycles.getSumFrom1ToNumber( 4 );
		assertEquals( sum, 10 );
	}
	
	@Test
	void testMnyCyclesGetThirdNumberOfSequencePlusPreviousNumber() {
		int thirdNumberOfSequence = cycles.getNumberOfSequencePlusPreviousNumber( 3 );
		assertEquals( thirdNumberOfSequence, 3 );
	}

	@Test
	void testMnyCyclesGetFourthNumberOfSequencePlusPreviousNumber() {
		int fourthNumberOfSequence = cycles.getNumberOfSequencePlusPreviousNumber( 4 );
		assertEquals( fourthNumberOfSequence, 5 );
	}
	
	@Test
	void testMnyCyclesGetThirdNumberArithmeticProgression1Plus1() {
		int thirdNumberArithmeticProgression1Plus1 = cycles.getNumberOfArithmeticProgression( 1, 1, 3 );
		assertEquals( thirdNumberArithmeticProgression1Plus1, 3 );
	}
	
	@Test
	void testMnyCyclesGetThirdNumberArithmeticProgression1Plus2() {
		int thirdNumberArithmeticProgression1Plus2 = cycles.getNumberOfArithmeticProgression( 1, 2, 3 );
		assertEquals( thirdNumberArithmeticProgression1Plus2, 5 );
	}
	
	@Test
	void testMnyCyclesIsNumberPrimeFrom1To10() {
		assertTrue( cycles.isNumberPrime(1) );
		assertTrue( cycles.isNumberPrime(2) );
		assertTrue( cycles.isNumberPrime(3) );
		assertFalse( cycles.isNumberPrime(4) );
		assertTrue( cycles.isNumberPrime(5) );
		assertFalse( cycles.isNumberPrime(6) );
		assertTrue( cycles.isNumberPrime(7) );
		assertFalse( cycles.isNumberPrime(8) );
		assertFalse( cycles.isNumberPrime(9) );
	}
}
