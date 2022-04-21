package ua.engexercises.controller;

import java.io.IOException;
import java.util.Scanner;

import ua.engexercises.model.ExercisesModel;
import ua.engexercises.view.ExercisesView;

public class ExercisesMVCMain {

	public static void main(String[] args) {

		ExercisesView view = new ExercisesView();
		ExercisesModel model = new ExercisesModel();
		ExercisesController controller = new ExercisesController( model, view );
		try {
			controller.processingUser();
		} catch ( IOException exc ) {
			System.out.println( exc );			
		}

	}
	


}
