package ua.engexercises.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import ua.engexercises.model.DBGetData;
import ua.engexercises.model.ExercisesModel;
import ua.engexercises.view.ExercisesFrame;

public class ExercisesSwingController {
	ExercisesModel model;
	ExercisesFrame view;
	Logger logger;
	
	public ExercisesSwingController(ExercisesModel model, ExercisesFrame view, Logger logger) {
		this.model = model;
		this.view = view;
		this.logger = logger;
	}

	public void initSwingController() throws FileNotFoundException, IOException {
		view.initView();
		logger.info("view.initView() complete");
		
		createComboBoxThemes();
		
		createComboBoxVariantsWithListener();		
		createComboBoxPatternsWithListener();
		
		createButtonSetCheckPatternModeWithListener();
		createButtonStopCheckPatternModeWithListener();

		view.createLabelPatternTask(DBGetData.getMessageDefaultTask());
		view.createTextFieldAnswer();
		
		view.completeInitView();
	}

	private void createButtonStopCheckPatternModeWithListener() {		
		view.createButtonStopCheckPatternMode(DBGetData.getLblStopCheckPatternMode());
		view.getBtnStopCheckPatternMode().addActionListener(event -> {
			view.enableGetPatternPanelAndDisableTextField();
		});
	}

	private void createButtonSetCheckPatternModeWithListener() {
		view.createButtonSetCheckPatternMode(DBGetData.getLblSetCheckPatternMode());
		view.getBtnSetCheckPatternMode().addActionListener(event -> {
			view.disableGetPatternPanelAndEnableTextField();
			
//			view.setTask();
		});
	}

	private void createComboBoxThemes() throws FileNotFoundException, IOException {
		ArrayList<String> themes = model.getListThemes();
		view.createComboBoxThemes(DBGetData.getLabelTheme(), themes);
		
		view.getComboBoxThemes().addActionListener( event -> {
			try {
				String theme = view.getCurrentTheme();
				view.refreshComboBoxVariants(model.getListVariants(theme));
				view.refreshComboBoxPatterns(
						model.getListPatterns(theme, view.getCurrentVariant()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void createComboBoxPatternsWithListener() throws FileNotFoundException, IOException {
		ArrayList<String> patterns = model.getListPatterns(view.getCurrentTheme(),
				view.getCurrentVariant());
		view.createComboBoxPatterns(DBGetData.getLabelPattern(), patterns);
	}

	private void createComboBoxVariantsWithListener() throws FileNotFoundException, IOException {
		String currentTheme = view.getCurrentTheme();
		ArrayList<String> variants = model.getListVariants(currentTheme);
		view.createComboBoxVariants(DBGetData.getLabelVariant(), variants);
		
		view.getComboBoxVariants().addActionListener(event -> {
			try {
				view.refreshComboBoxPatterns(
						model.getListPatterns(view.getCurrentTheme(), view.getCurrentVariant()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
	}
}
