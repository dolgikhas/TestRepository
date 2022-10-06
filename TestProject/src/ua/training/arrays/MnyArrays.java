package ua.training.arrays;

import java.util.Arrays;

public class MnyArrays {

	public static int getBiggestNumber(int[] array) {
		int biggest = array[0];
		
		for (int num : array) {
			if (biggest < num)
				biggest = num;
		}
		return biggest;
	}

	public static int getSmallestNumber(int[] array) {
		int smallest = array[0];
		
		for (int num : array) {
			if (smallest > num)
				smallest = num;
		}
		return smallest;
	}

	public static int getSumElements(int[] array) {
		int sum = 0;
		
		for (int num : array) {
			sum += num;
		}
		return sum;
	}

	public static double getAverage(int[] array) {
		int sum = 0;
		
		for (int num : array) {
			sum += num;
		}
		return (double)sum / array.length;
	}

	public static int[] bubbleSort(int[] array) {
//		System.out.println(Arrays.toString(array));
		
		int start = 0, end = array.length - 1;
		for (int i = 0; i < array.length; i++) {
			for (int j = start; j < array.length - 1; j++) {
				start = 0;
				if (array[j] > array[j + 1]) {
					if (0 == start)
						start = j;
					
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}			
		}
		
//		System.out.println(Arrays.toString(array));
		
		return array;
	}

	public static int[] deleteElements(int[] source, int element) {
		int amount = 0;
		for (int el : source) {
			if (el == element) {
				amount++;
			}
		}
		
		int result[] = new int[source.length - amount];
		int i = 0;
		for (int el : source) {
			if (el != element) {
				result[i++] = el;
			}
		}
		
		return result;
	}

	public static int[] getIntArrayFromString(String text) {
		String[] strArray = text.split(", ");
		int array[] = new int[strArray.length];
		
		for (int i = 0; i < strArray.length; i++) {
			array[i] = Integer.parseInt(strArray[i]);
		}
		return array;
	}
}
