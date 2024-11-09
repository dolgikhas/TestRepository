package mny.checkwords.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mny.checkwords.model.DateAndTime;

class TestDateAndTime {

	@Test
	void testCurrentDate() {
		assertEquals("241023", DateAndTime.getCurrentDate());
	}

}
