package mny.exercises;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MnyArrayTest {
	

	@Test
	void testMnyArrayGetMinimumNumber1From1234() {
		int array1[] = { 4, 3, 1, 2 };
		MnyArray mnyArray = new MnyArray( array1 ); 
		assertEquals( mnyArray.getMinimumNumber(), 1 );
	}
	
	@Test
	void testMnyArrayGetMinimumNumber2From2345() {
		int array1[] = { 4, 3, 5, 2 };
		MnyArray mnyArray = new MnyArray( array1 ); 
		assertEquals( mnyArray.getMinimumNumber(), 2 );
	}
	
	@Test
	void testMnyArrayGetMaximumNumber4From1234() {
		int array1[] = { 4, 3, 1, 2 };
		MnyArray mnyArray = new MnyArray( array1 ); 
		assertEquals( mnyArray.getMaximumNumber(), 4 );
	}

	@Test
	void testMnyArrayGetMaximumNumber5From2345() {
		int array1[] = { 4, 3, 5, 2 };
		MnyArray mnyArray = new MnyArray( array1 ); 
		assertEquals( mnyArray.getMaximumNumber(), 5 );
	}
	
	@Test
	void testMnyArrayGetAverageNumberFrom123() {
		int array1[] = { 1, 2, 3 };
		MnyArray mnyArray = new MnyArray( array1 ); 
		assertEquals( mnyArray.getAverageNumber(), 2.0 );		
	}
	
	@Test
	void testMnyArrayGetAverageNumberFrom2468() {
		int array1[] = { 2, 4, 6, 8 };
		MnyArray mnyArray = new MnyArray( array1 ); 
		assertEquals( mnyArray.getAverageNumber(), 5.0 );		
	}
	
	@Test
	void testMnyArrayIsArraysEqual() {
		int array1[] = { 1, 3, 5, 7 };
		int array2[] = { 2, 4, 6, 8 };
		int array3[] = { 1, 3, 5, 7 };
		MnyArray mnyArray = new MnyArray( array1 ); 
		assertTrue( mnyArray.isArrayEqual(array3) );		
		assertFalse( mnyArray.isArrayEqual(array2) );		
	}
	
	@Test
	void testMnyArrayBubbleSortArray42531() {
		int arraySorted[] = { 1, 2, 3, 4, 5 };
		int arrayNotSorted[] = { 4, 2, 5, 3, 1 };
		MnyArray mnyArray = new MnyArray( arrayNotSorted );
		mnyArray.sortArrayByBubbleSorting();
		assertTrue( mnyArray.isArrayEqual(arraySorted) );
	}
	
	@Test
	void testMnyArrayGetAmountNumbers1From54321() {
		int array1[] = { 5, 4, 3, 2, 1 };
		MnyArray mnyArray = new MnyArray( array1 );
		assertEquals( 1, mnyArray.getAmountNumbers(1) );		
	}
	
	@Test
	void testMnyArrayGetAmountNumbers1From5143121() {
		int array1[] = { 5, 1, 4, 3, 1, 2, 1 };
		MnyArray mnyArray = new MnyArray( array1 );
		assertEquals( 3, mnyArray.getAmountNumbers(1) );		
	}
	
	@Test
	void testMnyArrayRemoveNumbers1FromArray51243121() {
		int array1[] = { 5, 1, 2, 4, 3, 1, 2, 1 };
		int arrayWithout1[] = { 5, 2, 4, 3, 2 };
		int arrayWithout12[] = { 5, 4, 3 };
		MnyArray mnyArray = new MnyArray( array1 );
		mnyArray.removeNumberFromArray( 1 );		
		assertTrue( mnyArray.isArrayEqual(arrayWithout1) );		
		mnyArray.removeNumberFromArray( 2 );		
		assertTrue( mnyArray.isArrayEqual(arrayWithout12) );		
	}
	
	@Test
	void testMnyArrayGetPositionOfFirstNumber1FromArray51243121() {
		int array1[] = { 5, 1, 2, 4, 3, 1, 2, 1 };
		MnyArray mnyArray = new MnyArray( array1 );
		assertEquals( 1, mnyArray.getPositionOfFirstNumber(1) );		
	}

}
