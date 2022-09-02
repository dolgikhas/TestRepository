package ua.engexercises.view;

import java.util.ArrayList;
import java.util.logging.Logger;

import java.awt.*;
import javax.swing.*;

import ua.engexercises.model.*;


public class ExercisesFrame extends JFrame {
	private JPanel getPatternPanel;
	private JComboBox<String> comboBoxThemes;
	private JComboBox<String> comboBoxVariants;
	private JComboBox<String> comboBoxPatterns;
	private JPanel checkPatternPanel;
	private JButton btnSetCheckPatternMode;
	private JButton btnStopCheckPatternMode;
	private JLabel patternTask;
	private JTextField answer;

	public void initView() {
		getPatternPanel = new JPanel();
		getPatternPanel.setLayout(new GridLayout(4,2));

		checkPatternPanel = new JPanel();
		checkPatternPanel.setLayout(new GridLayout(2, 2));
		
		add(new JLabel("message"), BorderLayout.SOUTH);
	}
	
	public void completeInitView() {
		add(getPatternPanel, BorderLayout.NORTH);
		add(checkPatternPanel);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void createTextFieldAnswer() {
		answer = new JTextField("");
		answer.setEnabled(false);
		checkPatternPanel.add(answer);
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
	}

	public void createComboBoxVariants(String labelVariant, ArrayList<String> variants) {
		comboBoxVariants = new JComboBox<>();		
		createComboBox(comboBoxVariants, labelVariant, variants);
	}

	public void createComboBoxPatterns(String labelPattern, ArrayList<String> patterns) {
		comboBoxPatterns = new JComboBox<>();	
		createComboBox(comboBoxPatterns, labelPattern, patterns);
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
		answer.setEnabled(true);
		btnStopCheckPatternMode.setEnabled(true);
	}
	
	public void enableGetPatternPanelAndDisableTextField() {
		comboBoxThemes.setEnabled(true);
		comboBoxVariants.setEnabled(true);
		comboBoxPatterns.setEnabled(true);
		btnSetCheckPatternMode.setEnabled(true);
		answer.setEnabled(false);		
		btnStopCheckPatternMode.setEnabled(false);
	}

}
