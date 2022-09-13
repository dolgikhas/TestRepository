package ua.training.dmdev;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class Calculator {
	public static int add( int num1, int num2 ) {
		return num1 + num2;
	}
}

class UserServiceTest {
	@DisplayName("Operation test")
	@RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
	void addNumber(TestInfo testInfo) {
	    Assertions.assertEquals(2, Calculator .add(1, 1), "1 + 1 should equal 2");
	}

	@DisplayName("Operation test")
	@RepeatedTest(value = 5, name = RepeatedTest.SHORT_DISPLAY_NAME)
	void addNumber2(TestInfo testInfo) {
	    Assertions.assertEquals(2, Calculator .add(1, 1), "1 + 1 should equal 2");
	}
}
