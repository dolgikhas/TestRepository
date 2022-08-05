package ua.engexercises.view;

import java.util.ArrayList;

import ua.engexercises.model.DBGetData;

public class ExercisesView {

	public void printList( String message, ArrayList<String> items) {
		System.out.println( message );
		
		for ( int index = 0; index < items.size(); index++ )
			System.out.println( index + ": " + items.get(index) );
		
	}

	public void printMessage(String message) {
		System.out.println( message );
	}

}
