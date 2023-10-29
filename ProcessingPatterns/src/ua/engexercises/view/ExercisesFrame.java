package ua.engexercises.view;

import java.util.ArrayList;
import java.util.logging.Logger;

import java.awt.*;
import javax.swing.*;

import ua.engexercises.model.*;


public class ExercisesFrame extends JFrame {
	private JPanel getOptionWorkPanel;
	private ButtonGroup optionWorkButtonGroup;
	private JRadioButton optionRandomTheme;
	private JRadioButton optionDebugTheme;
	private JButton btnSetOptionWork;
	private JPanel getPatternPanel;
	private JComboBox<String> comboBoxThemes;
	private JComboBox<String> comboBoxVariants;
	private JComboBox<String> comboBoxPatterns;
	private JPanel checkPatternPanel;
	private JButton btnSetCheckPatternMode;
	private JButton btnStopCheckPatternMode;
	private JLabel patternTask;
	private JTextField txtAnswerField;
	private JLabel lblStatistic;
	private JButton btnShowAnswer;

	public void initView() {
		getOptionWorkPanel = new JPanel();
		getOptionWorkPanel.setLayout(new GridLayout(3, 1));
		
		getPatternPanel = new JPanel();
		getPatternPanel.setLayout(new GridLayout(4,2));

		checkPatternPanel = new JPanel();
		checkPatternPanel.setLayout(new GridLayout(4, 1));
	}
	
	public void completeInitView() {
		add(getOptionWorkPanel, BorderLayout.NORTH);
		add(getPatternPanel);
		add(checkPatternPanel, BorderLayout.SOUTH);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void createTextFieldAnswer() {
		txtAnswerField = new JTextField("");
		txtAnswerField.setEnabled(false);
		checkPatternPanel.add(txtAnswerField);
		lblStatistic = new JLabel("statistic", JLabel.CENTER);
		checkPatternPanel.add(lblStatistic);
	}

	public void createLabelPatternTask(String label) {
		patternTask = new JLabel(label);
		checkPatternPanel.add(patternTask);
	}

	public JButton getBtnSetCheckPatternMode() {
		return btnSetCheckPatternMode;
	}

	public JButton getBtnStopCheckPatternMode() {
		return btnStopCheckPatternMode;
	}

	public void createButtonStopCheckPatternMode(String label) {
		btnStopCheckPatternMode = new JButton(label);
		getPatternPanel.add(btnStopCheckPatternMode);
		btnStopCheckPatternMode.setEnabled(false);
	}

	public void createButtonSetCheckPatternMode(String label) {
		btnSetCheckPatternMode = new JButton(label);
		getPatternPanel.add(btnSetCheckPatternMode);
		btnSetCheckPatternMode.setEnabled(false);
	}
	
	public JComboBox<String> getComboBoxThemes() {
		return comboBoxThemes;
	}

	public JComboBox<String> getComboBoxVariants() {
		return comboBoxVariants;
	}

	public JComboBox<String> getComboBoxPatterns() {
		return comboBoxPatterns;
	}

	public void createComboBox(JComboBox<String> comboBox, String labelTheme, ArrayList<String> items) {
		getPatternPanel.add(new JLabel(labelTheme, JLabel.RIGHT));
		getPatternPanel.add(comboBox);
		for (String item : items) {
			comboBox.addItem(item);
		}
	}

	public String getCurrentTheme() {
		return comboBoxThemes.getItemAt(comboBoxThemes.getSelectedIndex());
	}

	public String getCurrentVariant() {
		return comboBoxVariants.getItemAt(comboBoxVariants.getSelectedIndex());
	}

	public void createComboBoxThemes(String labelTheme, ArrayList<String> themes) {
		comboBoxThemes = new JComboBox<>();		
		createComboBox(comboBoxThemes, labelTheme, themes);
		comboBoxThemes.setEnabled(false);
	}

	public void createComboBoxVariants(String labelVariant, ArrayList<String> variants) {
		comboBoxVariants = new JComboBox<>();		
		createComboBox(comboBoxVariants, labelVariant, variants);
		comboBoxVariants.setEnabled(false);
	}

	public void createComboBoxPatterns(String labelPattern, ArrayList<String> patterns) {
		comboBoxPatterns = new JComboBox<>();	
		createComboBox(comboBoxPatterns, labelPattern, patterns);
		comboBoxPatterns.setEnabled(false);
	}

	public void refreshComboBoxVariants(ArrayList<String> listVariants) {
		refreshComboBox(comboBoxVariants, listVariants);
	}
	
	public void refreshComboBoxPatterns(ArrayList<String> listPatterns) {
		refreshComboBox(comboBoxPatterns, listPatterns);
	}
	
	public void refreshComboBox(JComboBox<String> comboBox, ArrayList<String> items) {
		comboBox.removeAllItems();
		for (String item : items) {
			comboBox.addItem(item);
		}
	}

	public void disableGetPatternPanelAndEnableTextField() {
		comboBoxThemes.setEnabled(false);
		comboBoxVariants.setEnabled(false);
		comboBoxPatterns.setEnabled(false);
		btnSetCheckPatternMode.setEnabled(false);
		txtAnswerField.setEnabled(true);
		btnStopCheckPatternMode.setEnabled(true);
	}
	
	public void enableGetPatternPanelAndDisableTextField() {
		comboBoxThemes.setEnabled(true);
		comboBoxVariants.setEnabled(true);
		comboBoxPatterns.setEnabled(true);
		btnSetCheckPatternMode.setEnabled(true);
		txtAnswerField.setEnabled(false);		
		btnStopCheckPatternMode.setEnabled(false);
	}

	public String getCurrentPattern() {
		return comboBoxPatterns.getItemAt(comboBoxPatterns.getSelectedIndex());
	}

	public void setTask(String task) {
		patternTask.setText(task);
	}
	
	public void setStatistic(String text) {
		lblStatistic.setText(text);
	}

	public String getUserInput() {
		return txtAnswerField.getText();
	}
	
	public JTextField getTxtAnswerField() {
		return txtAnswerField;
	}

	public void setAnswerFieldValue(String text) {
		txtAnswerField.setText(text);
	}

	public void createOptionWork() {
		optionWorkButtonGroup = new ButtonGroup();
		optionRandomTheme = new JRadioButton("Режим произвольной темы");
		optionRandomTheme.setSelected(true);
		optionWorkButtonGroup.add(optionRandomTheme);
		getOptionWorkPanel.add(optionRandomTheme);
		optionDebugTheme = new JRadioButton("Режим выбора темы");
		getOptionWorkPanel.add(optionDebugTheme);
		optionWorkButtonGroup.add(optionDebugTheme);
		btnSetOptionWork = new JButton("Задать режим работы программы");
		getOptionWorkPanel.add(btnSetOptionWork);
	}

	public JButton getBtnSetOptionWork() {
		return btnSetOptionWork;
	}

	public JRadioButton getRadBtnRandomTheme() {
		return optionRandomTheme;
	}

	public void disableElementsForOptionRandomThemes() {
		comboBoxThemes.setEnabled(false);
		comboBoxVariants.setEnabled(false);
		comboBoxPatterns.setEnabled(false);
		btnSetCheckPatternMode.setEnabled(false);
		btnStopCheckPatternMode.setEnabled(true);
	}

	public void createBtnShowAnswer() {
		btnShowAnswer = new JButton("Показать ответ");
		checkPatternPanel.add(btnShowAnswer);
	}

	public AbstractButton getBtnShowAnswer() {
		return btnShowAnswer;
	}

}
