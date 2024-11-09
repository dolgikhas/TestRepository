package mny.checkwords.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSoundExample {

	@Test
	void processExample1() {
		assertEquals("Is your batch ready", SoundExamples.processExample("Is your batch ready?"));
	}
	@Test
	void processExample2() {
		assertEquals("I’ve got a whole batch of applications",
				SoundExamples.processExample("I’ve got a whole batch of applications."));
	}
	@Test
	void processExample3() {
		assertEquals("Tomorrow, 600 AM, saddle up the troops",
				SoundExamples.processExample("Tomorrow, 6:00 A.M., saddle up the troops."));
	}

}
