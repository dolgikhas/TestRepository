package ua.engexercises.view;

import java.util.ArrayList;

import ua.engexercises.model.DBGetData;

public class ExercisesView {

	public void printListThemes(ArrayList<String> listThemes) {
		System.out.println( DBGetData.getMessageListThemes() );
		
		for ( int index = 0; index < listThemes.size(); index++ )
			System.out.println( index + ": " + listThemes.get(index) );
		
	}

	public void printApplicationOptions() {
		System.out.println( DBGetData.getListAppOptions() );
	}

	public void printMessage(String message) {
		System.out.println( message );
	}

}
