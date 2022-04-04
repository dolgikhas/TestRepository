package mny.exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MnyStringTest {

	@Test
	void testReverseDogToGod() {
		MnyString mnyString1 = new MnyString( "dog" );
		mnyString1.reverse();
		assertEquals( mnyString1.getString(), "god" );
		
	}

	@Test
	void testReverseAbcdefgToGfedcba() {
		MnyString mnyString1 = new MnyString( "abcdefg" );
		mnyString1.reverse();
		assertEquals( mnyString1.getString(), "gfedcba" );
		
	}
}
