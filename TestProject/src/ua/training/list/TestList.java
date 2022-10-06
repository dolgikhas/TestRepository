package ua.training.list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ua.training.arrays.MnyArrays;

class TestList {

	@ParameterizedTest
	@CsvSource(value = {
			"'1, 3, 5, 2, 4, 6', 4",
			"'1, 3, 5, 2, 4, 6', 1"
		})
	void testAddElementsAndHasItemWhichExists(String strArray, int search) {
		MnyList list = fillMnyListByString(strArray);

		assertTrue(list.hasItem(search));
	}

	private MnyList fillMnyListByString(String strArray) {
		int array[] = MnyArrays.getIntArrayFromString(strArray);
		
		MnyList list = new MnyList();
		for( int i : array) {
			list.add(i);
		}
		return list;
	}
	
	@ParameterizedTest
	@CsvSource(value = {
			"'1, 3, 5, 2, 4, 6'",
			"'1, 3, 5, 9, 7, 8, 6, 4, 2'"
		})
	void testGetArrayElements(String strArray) {
		MnyList list = fillMnyListByString(strArray);

		assertEquals(0, Arrays.compare(list.getArrayElements(),
				MnyArrays.getIntArrayFromString(strArray)));
	}
	
	/*
	@ParameterizedTest
	@CsvSource(value = {
			"'1, 3, 5, 2, 4, 6', 4, '1, 3, 5, 2, 6'"
		})
	void testRemoveFirstElement(String strSource, int search, String strResult) {
		int result[] = MnyArrays.getIntArrayFromString(strResult);
		MnyList list = fillMnyListByString(strSource);
		list.remove(search);
		
		assertEquals(0, Arrays.compare(list.getArrayElements(), result));
	}
	*/
	
	@ParameterizedTest
	@CsvSource(value = {
			"'1, 3, 5, 2, 4, 6', 4, 4"
		})
	void testGetItemByIndex(String strSource, int index, int result) {
		MnyList list = fillMnyListByString(strSource);
		assertEquals(result, list.getItemByIndex(index).getValue());
	}
}
