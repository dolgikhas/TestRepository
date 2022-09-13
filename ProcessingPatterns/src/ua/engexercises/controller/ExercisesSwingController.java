package ua.engexercises.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import ua.engexercises.model.DBGetData;
import ua.engexercises.model.ExercisesModel;
import ua.engexercises.model.PatternTask;
import ua.engexercises.view.ExercisesFrame;

public class ExercisesSwingController {
	ExercisesModel model;
	ExercisesFrame view;
	Logger logger;
	PatternTask patternTask;
	private int numberCorrectAnswers;
	
	public ExercisesSwingController(ExercisesModel model, ExercisesFrame view, Logger logger) {
		this.model = model;
		this.view = view;
		this.logger = logger;
		numberCorrectAnswers = 0;
	}

	public void initSwingController() throws FileNotFoundException, IOException {
		model.getCommonLists();

		view.initView();
		logger.info("view.initView() complete");
		
		createComboBoxThemes();
		
		createComboBoxVariantsWithListener();		
		createComboBoxPatternsWithListener();
		
		createButtonSetCheckPatternModeWithListener();
		createButtonStopCheckPatternModeWithListener();

		view.createLabelPatternTask(DBGetData.getMessageDefaultTask());
		logger.info("view.createLabelPatternTask(DBGetData.getMessageDefaultTask())");
		
		createTextFieldAnswerWithListener();
		
		view.completeInitView();
		logger.info("view.completeInitView()");
	}

	public void createTextFieldAnswerWithListener() {
		view.createTextFieldAnswer();
		view.getTxtAnswerField().addActionListener(event -> {
			if (model.checkIsUserInputEqualToAnswer(view.getUserInput())) {
				view.setStatistic(DBGetData.getMessageCorectAnswer() + " " +
						DBGetData.getMessageNumberCorrectAnswers() + " " +
						++numberCorrectAnswers);
				
				try {
					model.setProcessingPatternObject(view.getCurrentTheme(), view.getCurrentVariant(),
							view.getCurrentPattern());

					patternTask = model.getPatternTask();
					logger.info( "model.getPatternTask()" );
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				view.setTask(patternTask.getTask());
				logger.info( "view.setTask(patternTask.getTask())" );
				
				view.setAnswerFieldValue("");
				logger.info( "view.setAnswerFieldValue(\"\")" );

			} else {
				view.setStatistic(DBGetData.getMessageNotCorrectAnswer());
			}
		});
		logger.info("view.createTextFieldAnswer()");
	}

	private void createButtonStopCheckPatternModeWithListener() {		
		view.createButtonStopCheckPatternMode(DBGetData.getLblStopCheckPatternMode());
		view.getBtnStopCheckPatternMode().addActionListener(event -> {
			view.enableGetPatternPanelAndDisableTextField();
		});
		logger.info("createButtonStopCheckPatternModeWithListener()");
	}

	private void createButtonSetCheckPatternModeWithListener() {
		view.createButtonSetCheckPatternMode(DBGetData.getLblSetCheckPatternMode());
		view.getBtnSetCheckPatternMode().addActionListener(event -> {
			view.disableGetPatternPanelAndEnableTextField();
			
			numberCorrectAnswers = 0;
			
			try {
				model.setProcessingPatternObject(view.getCurrentTheme(), view.getCurrentVariant(),
						view.getCurrentPattern());
				
				patternTask = model.getPatternTask();
				logger.info( "model.getPatternTask()" );
				
				view.setTask(patternTask.getTask());
				logger.info( "view.setTask(patternTask.getTask())" );

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		logger.info("createButtonSetCheckPatternModeWithListener()");
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
		logger.info("createComboBoxThemes()");
	}

	private void createComboBoxPatternsWithListener() throws FileNotFoundException, IOException {
		ArrayList<String> patterns = model.getListPatterns(view.getCurrentTheme(),
				view.getCurrentVariant());
		view.createComboBoxPatterns(DBGetData.getLabelPattern(), patterns);
		logger.info("createComboBoxPatternsWithListener()");
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
		logger.info("createComboBoxVariantsWithListener()");
	}
}
