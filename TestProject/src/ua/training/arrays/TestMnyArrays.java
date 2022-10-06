package ua.training.arrays;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestMnyArrays {
	
	@ParameterizedTest
	@CsvSource(value = {
		"2, 1, 1, 0, 0, 0, 0, 0",
		"2, 1, 2, 0, 0, 0, 0, 0",
		"2, 2, 2, 0, 0, 0, 0, 0",		
		"3, 1, 2, 3, 0, 0, 0, 0",
		"3, 3, 2, 1, 0, 0, 0, 0",
		"4, 1, 2, 3, 4, 0, 0, 0",		
		"4, 1, 3, 2, 4, 0, 0, 0",		
		"4, 4, 1, 3, 2, 0, 0, 0",		
		"4, 1, 4, 3, 2, 0, 0, 0"		
	})
	void testGetIntArrayFromStringForDifferentNumberElements(int amount, int num1, int num2, int num3, int num4, int num5, int num6, int num7) {
		int start[] = {num1, num2, num3, num4, num5, num6, num7};
		int array[] = new int[amount];
		String text = "" + num1;
		
		for (int i = 1; i < amount; i++) {
			array[i] = start[i];
			text +=  ", " + start[i];
		}
		
		assertEquals(0, Arrays.compare(array, MnyArrays.getIntArrayFromString(text)));
	}
	
	
	@ParameterizedTest
	@CsvSource(value = {
		"2, '1, 2'",
		"2, '1, 2, 1'",
		"3, '3, 1, 2'",
		"4, '1, 2, 3, 4'",
		"4, '4, 2, 3, 4'",
		"4, '1, 4, 2, 4, 2'",
		"4, '4, 4, 4, 4'",
		"5, '5, 2, 3, 4, 3, 3, 5'",
		"6, '1, 6, 3, 4, 2, 5'",
		"7, '1, 3, 5, 7, 2, 4, 6'",
		"8, '2, 4, 6, 1, 3, 5, 7, 8'"
	})
	void testGetBiggestNumberForDifferentArrays(int result, String text) {
		int[] array = MnyArrays.getIntArrayFromString(text);
		
		assertEquals(result, MnyArrays.getBiggestNumber(array));
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"1, '1, 2'",
		"1, '1, 2, 1'",
		"1, '3, 1, 2'",
		"1, '1, 2, 3, 4'",
		"2, '4, 2, 3, 4'",
		"1, '1, 4, 2, 4, 2'",
		"4, '4, 4, 4, 4'",
		"2, '5, 2, 3, 4, 3, 3, 5'",
		"1, '1, 6, 3, 4, 2, 5'",
		"1, '1, 3, 5, 7, 2, 4, 6'",
		"1, '2, 4, 6, 1, 3, 5, 7, 8'"
	})
	void testGetSmallestNumberForDifferentArrays(int result, String text) {
		int[] array = MnyArrays.getIntArrayFromString(text);
		
		assertEquals(result, MnyArrays.getSmallestNumber(array));
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"2, '1, 1'",
		"3, '1, 1, 1'",
		"3, '1, 2'",
		"4, '1, 1, 1, 1'",
		"4, '1, 1, 2'",
		"5, '1, 1, 1, 1, 1'",
		"6, '1, 2, 3'",
		"7, '1, 1, 2, 3'",
	})
	void testGetSumElementsOfArray(int result, String strArray) {
		int[] array = MnyArrays.getIntArrayFromString(strArray);
		
		assertEquals(result, MnyArrays.getSumElements(array));
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"1.0, '1, 1'",
		"1.0, '1, 1, 1'",
		"1.5, '1, 2'",
		"1.0, '1, 1, 1, 1'",
		"1.0, '1, 1, 1, 1, 1'",
		"2.0, '1, 2, 3'",
		"2.0, '2, 2, 2'",
		"3.0, '3, 3, 3'"
	})
	void testGetAverage(double result, String strArray) {
		int[] array = MnyArrays.getIntArrayFromString(strArray);
		
		assertEquals(result, MnyArrays.getAverage(array));
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"'1, 2, 3', '3, 1, 2'",
		"'1, 2, 3', '1, 3, 2'",
		"'1, 2, 3', '3, 2, 1'",
		"'1, 1, 2, 3', '1, 3, 1, 2'",
		"'1, 1, 2, 3', '3, 1, 2, 1'"		
	})
	void testBubbleSort(String resultArray, String sourceArray) {
		int result[] = MnyArrays.getIntArrayFromString(resultArray);
		int source[] = MnyArrays.getIntArrayFromString(sourceArray);
		assertTrue(Arrays.equals(result, MnyArrays.bubbleSort(source)));
	}
	
	@ParameterizedTest
	@CsvSource(value = {
		"2, '1, 2, 3, 2, 5', '1, 3, 5'",
		"2, '1, 2, 2, 1, 3', '1, 1, 3'",
		"1, '1, 2, 1, 3, 2, 1', '2, 3, 2'",
		"1, '1, 1, 1, 2', '2'"
	})
	void testDeleteElementsFromArray(int delete, String strSourceArray, String strResultArray) {
		int sourceArray[] = MnyArrays.getIntArrayFromString(strSourceArray);
		int resultArray[] = MnyArrays.getIntArrayFromString(strResultArray);
		
		assertTrue(Arrays.equals(resultArray, MnyArrays.deleteElements(sourceArray, delete)));
	}

}
