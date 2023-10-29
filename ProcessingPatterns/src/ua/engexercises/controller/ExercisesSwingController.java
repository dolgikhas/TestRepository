package ua.engexercises.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import ua.engexercises.model.DBGetData;
import ua.engexercises.model.ExercisesModel;
import ua.engexercises.model.PatternTask;
import ua.engexercises.view.ExercisesFrame;

enum OptionWork{RANDOM_THEME, CONRETE_THEME};

public class ExercisesSwingController {
	ExercisesModel model;
	ExercisesFrame view;
	Logger logger;
	PatternTask patternTask;
	private int numberCorrectAnswers;
	private OptionWork optionWork;
	public final int AMOUNT_CORRECT_ANSWERS = 3;
	
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
		
		createOptionWork();
		
		createComboBoxThemes();
		
		createComboBoxVariantsWithListener();		
		createComboBoxPatternsWithListener();
		
		createButtonSetCheckPatternModeWithListener();
		createButtonStopCheckPatternModeWithListener();

		createPatternTaskAndButtonShowAnswer();
		
		createTextFieldAnswerWithListener();
		
		view.completeInitView();
		logger.info("view.completeInitView()");
	}

	private void createPatternTaskAndButtonShowAnswer() {
		view.createLabelPatternTask(DBGetData.getMessageDefaultTask());
		logger.info("view.createLabelPatternTask(DBGetData.getMessageDefaultTask())");
		
		view.createBtnShowAnswer();
		logger.info("view.createBtnShowAnswer()");
		view.getBtnShowAnswer().addActionListener(event -> {
			JOptionPane.showMessageDialog(null, model.getAnswer());

		});
		logger.info("view.getBtnShowAnswer().addActionListener(...)");
	}

	private void createOptionWork() {
		view.createOptionWork();
		view.getBtnSetOptionWork().addActionListener(event -> {
			if (view.getRadBtnRandomTheme().isSelected()) {
				optionWork = OptionWork.RANDOM_THEME;
				view.disableElementsForOptionRandomThemes();
				setRandomThemePatternAndForm();
				view.disableGetPatternPanelAndEnableTextField();			
				setTaskFromSelectedPattern();
				logger.info("view.getBtnSetOptionWork().addActionListener() for random theme");
			} else {
				optionWork = OptionWork.CONRETE_THEME;
				view.enableGetPatternPanelAndDisableTextField();
				logger.info("view.getBtnSetOptionWork().addActionListener() for concrete theme");
			}
		});
		logger.info("createOptionWork()");

	}

	private void setRandomThemePatternAndForm() {
		view.getComboBoxThemes().setSelectedItem((int)(Math.random() * 100 %
				view.getComboBoxThemes().getItemCount()));
		view.getComboBoxVariants().setSelectedIndex((int)(Math.random() * 100 %
				view.getComboBoxVariants().getItemCount()));
		view.getComboBoxPatterns().setSelectedIndex((int)(Math.random() * 100 %
				view.getComboBoxPatterns().getItemCount()));
		logger.info("controller.setRandomThemePatternAndForm()");
	}

	public void createTextFieldAnswerWithListener() {
		view.createTextFieldAnswer();
		view.getTxtAnswerField().addActionListener(event -> {
			if (model.checkIsUserInputEqualToAnswer(view.getUserInput())) {
				if (optionWork == OptionWork.RANDOM_THEME &&
						AMOUNT_CORRECT_ANSWERS <= numberCorrectAnswers) {
					setRandomThemePatternAndForm();
					setTaskFromSelectedPattern();
					view.setAnswerFieldValue("");
					view.setStatistic("Выбрана новая тема!");
					return;
				}
				
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
			if (optionWork == OptionWork.RANDOM_THEME) {
				setRandomThemePatternAndForm();
				setTaskFromSelectedPattern();
				view.setAnswerFieldValue("");
				view.setStatistic("Выбрана новая тема!");
			} else {
				view.enableGetPatternPanelAndDisableTextField();
			}
		});
		logger.info("createButtonStopCheckPatternModeWithListener()");
	}

	private void createButtonSetCheckPatternModeWithListener() {
		view.createButtonSetCheckPatternMode(DBGetData.getLblSetCheckPatternMode());
		view.getBtnSetCheckPatternMode().addActionListener(event -> {
			view.disableGetPatternPanelAndEnableTextField();			
			setTaskFromSelectedPattern();
		});
		logger.info("createButtonSetCheckPatternModeWithListener()");
	}

	public void setTaskFromSelectedPattern() {
		numberCorrectAnswers = 0;
		
		try {
			model.setProcessingPatternObject(view.getCurrentTheme(), view.getCurrentVariant(),
					view.getCurrentPattern());
			logger.info( "model.setProcessingPatternObject()" );
			
			patternTask = model.getPatternTask();
			logger.info( "model.getPatternTask()" );
			
			view.setTask(patternTask.getTask());
			logger.info( "view.setTask(patternTask.getTask())" );

		} catch (Exception e) {
			e.printStackTrace();
		}
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
