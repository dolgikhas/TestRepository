package ua.training.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Car {
	String number;
	int year;

	public Car(String number, int year) {
		this.number = number;
		this.year = year;
	}

	public String getNumber() {
		return number;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "Car [number=" + number + ", year=" + year + "]";
	}
	
}

public class TestClass {
	
	public static void main(String[] args) {
List<String> names = Arrays.asList("John", "Arya", "Sansa");
Set<String> upperCaseNames = names.stream()
	.map(String::toUpperCase)
	.collect(Collectors.toSet());

System.out.println(upperCaseNames); // [ARYA, JOHN, SANSA]

	}
}
