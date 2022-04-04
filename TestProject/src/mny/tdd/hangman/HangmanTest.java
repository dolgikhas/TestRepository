package mny.tdd.hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HangmanTest {

	Hangman game = new Hangman( "sofa" );
		
	@Test
	void youSeeMaskedWord() {
		assertEquals( "____", game.getWord() );
	}
	
	@Test
	public void gameIsNotLostInTheBeginning() {
		assertFalse( game.isLost() );		
		assertEquals( 0, game.FailuresCount() );
	}
	
	@Test
	public void youMustGuessLetters() {
		game.guessLetter( 'o' );
		assertEquals( "_o__", game.getWord() );
		assertEquals( 0, game.FailuresCount() );
	}
	
	@Test
	public void youHave6Guesses() {
		game.guessLetter( 'x' );
		game.guessLetter( 'y' );
		game.guessLetter( 'z' );
		game.guessLetter( 'u' );
		game.guessLetter( 't' );
		assertFalse( game.isLost() );
		assertEquals( 5, game.FailuresCount() );
		
		game.guessLetter( 'i' );
		assertTrue( game.isLost() );
		assertEquals( 6, game.FailuresCount() );
	}
	
	@Test
	public void youWinWhenYouGuessesAllLetters() {
		game.guessLetter( 'f' );
		game.guessLetter( 's' );
		game.guessLetter( 'o' );
		game.guessLetter( 'a' );
		assertTrue( game.isWon() );
	}
	
	@Test
	public void multipleClicksOneLetter() {
		game.guessLetter( 'q' );
		game.guessLetter( 'q' );
		game.guessLetter( 'q' );
		game.guessLetter( 'q' );
		game.guessLetter( 'q' );
		game.guessLetter( 'q' );
		game.guessLetter( 'q' );
		game.guessLetter( 'q' );
		assertEquals( 1, game.FailuresCount() );
		assertFalse( game.isLost() );
	}

}
