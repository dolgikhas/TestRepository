package mny.tdd.org.demos.junit.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class TemplateTest {
	
	private String thePattern;
	@Before
	public void setUp() throws Exception {
		thePattern = "your test score was: ";
		
	}

	@Test
	public void canInstantiateATemplate() {
		Template template = new Template();
		
		assertNotNull( template );
	}
	
	@Test
	public void canInstantiateTemplateWithAString() {
		Template template = new Template( thePattern );		
		assertNotNull( template );
	}
	
	@Test
	public void renderMethodReturnsAString() {
 		Template template = new Template( thePattern );	
		String returnedStr = template.render(0);
		assertNotNull( returnedStr );
	}
	
	@Test
	public void renderMethodReturnsProperMessageGivenScore() {
		int score = 90;
		Template template = new Template( thePattern );	
		assertNotNull( template.render(score) );
		String expectedMsg = thePattern + score;
		assertEquals( expectedMsg, template.render(score) );
		
	}
	
	@Test//(expected=Exception.class)
	public void renderThrowsExceptionWithNullTemplateText() {
		Template template = new Template( null );
		int score = 5;
		String expectedMsg = thePattern + score;
		assertEquals( expectedMsg, template.render(score) );
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

}
