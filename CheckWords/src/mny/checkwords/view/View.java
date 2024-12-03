package mny.checkwords.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import mny.checkwords.model.Model;

public class View extends JFrame {
	private Model model;
	
	private JPanel pnlSelectOptionWordsForCheckAndWord;
	private JLabel lblSelectWordOptionVisible;
	private ButtonGroup optWordVisibleGroup;
	private JRadioButton optWordVisible;
	private JLabel lblWordEmptyOptionVisible;
	private JRadioButton optWordNotVisible;

	private JLabel lblSelectTranslationOptionVisible;
	private ButtonGroup optTranslationVisibleGroup;
	private JRadioButton optTranslationVisible;
	private JLabel lblTranslationEmptyOptionVisible;
	private JRadioButton optTranslationNotVisible;

	private JLabel lblSelectWordsForCheck;
	private JComboBox combWordsForCheck;
	private JButton btnStartCheckWords;
	private JButton btnStopCheckWords;
	private JButton btnRepeatPlayExample;
	private JButton btnPlayNextExample;
	private JButton btnShowWord;
	private JButton btnStopCheckWordsAndSaveResult;
	private JLabel lblWord;
	private JTextField txtWord;
	private JLabel lblTranscription;
	private JTextField txtTranscription;
	private JLabel lblTranslation;
	private JTextField txtTranslation;
	private JLabel lblExamples;
	private JLabel lblStatistics;

	private JPanel pnlWordExamples;	
	private JTextArea txtExamples;
	
	private JPanel pnlResult;
	private JButton btnShowTranslation;
	private JButton btnPlus;
	private JButton btnAsterisk;
	private JButton btnMinus;
	private JButton btnMinus4;
	private JButton btnPutWordToDoubtFile;
	private JButton btnPreviousWord;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void initView() {
		pnlSelectOptionWordsForCheckAndWord = new JPanel();
		pnlSelectOptionWordsForCheckAndWord.setLayout(new GridLayout(12, 2));
		pnlWordExamples = new JPanel();
		pnlResult = new JPanel();
	}

	public void createControls() {
		createControlsSelectWordOptionVisibility();
		createControlsSelectTranslationOptionVisibility();
		createControlsSelectWordsForCheck();

		createButtonsForCheckWords();

		createControlsWithWordAttributes();
		
		createControlExamplesArea();

		createControlsResultsShowAndPreviousWord();
	}

	private void createControlsSelectTranslationOptionVisibility() {
		lblSelectTranslationOptionVisible = createLabel("Выбери опцию отображения перевода", pnlSelectOptionWordsForCheckAndWord);		
		optTranslationVisibleGroup = new ButtonGroup();
		optTranslationVisible = initRadioButton("Показывать перевод", optTranslationVisibleGroup, pnlSelectOptionWordsForCheckAndWord);
		optTranslationVisible.addActionListener(event -> btnShowTranslation.setEnabled(false));
		lblTranslationEmptyOptionVisible = createLabel("", pnlSelectOptionWordsForCheckAndWord);
		optTranslationNotVisible = initRadioButton("Не показывать перевод", optTranslationVisibleGroup, pnlSelectOptionWordsForCheckAndWord);		
		optTranslationNotVisible.addActionListener(event -> btnShowTranslation.setEnabled(true));
		optTranslationNotVisible.setSelected(true);
	}

	private void createControlExamplesArea() {
		txtExamples = createTextArea(pnlWordExamples);
		txtExamples.setEditable(false);
	}

	private void createControlsResultsShowAndPreviousWord() {
		btnShowTranslation = createButton("Перевод (-ы)", pnlResult);
		btnPlus = createButton("+", pnlResult);
		btnAsterisk = createButton("*", pnlResult);
		btnMinus = createButton("-", pnlResult);
		btnMinus4 = createButton("-4", pnlResult);
		btnPutWordToDoubtFile = createButton("Сомнит-е", pnlResult);
		btnPreviousWord = createButton("Предыдущее", pnlResult);
		
		disableControlsResults();
	}

	public void disableControlsResults() {
		btnShowTranslation.setEnabled(false);
		btnPlus.setEnabled(false);
		btnAsterisk.setEnabled(false);
		btnMinus.setEnabled(false);
		btnMinus4.setEnabled(false);
		btnPutWordToDoubtFile.setEnabled(false);
		btnPreviousWord.setEnabled(false);
	}

	private void createControlsWithWordAttributes() {
		lblWord = createLabel("Слово", pnlSelectOptionWordsForCheckAndWord);
		txtWord = createTextFieldNotEditable(pnlSelectOptionWordsForCheckAndWord);		
		lblTranscription = createLabel("Транскрипция", pnlSelectOptionWordsForCheckAndWord);
		txtTranscription = createTextFieldNotEditable(pnlSelectOptionWordsForCheckAndWord);
		lblTranslation = createLabel("Перевод", pnlSelectOptionWordsForCheckAndWord);
		txtTranslation = createTextFieldNotEditable(pnlSelectOptionWordsForCheckAndWord);
		lblExamples = createLabel("Примеры", pnlSelectOptionWordsForCheckAndWord);
		lblStatistics = createLabel("", pnlSelectOptionWordsForCheckAndWord);
	}

	private void createButtonsForCheckWords() {
		btnStartCheckWords = createButton("Поехали!", pnlSelectOptionWordsForCheckAndWord);
		btnStopCheckWords = createButton("Остановить проверку", pnlSelectOptionWordsForCheckAndWord);
		btnRepeatPlayExample = createButton("Повторить пример", pnlSelectOptionWordsForCheckAndWord);
		btnPlayNextExample = createButton("Следующий пример", pnlSelectOptionWordsForCheckAndWord);
		btnShowWord = createButton("Показать слово", pnlSelectOptionWordsForCheckAndWord);
		btnStopCheckWordsAndSaveResult = createButton("Остановить и сохранить результаты", pnlSelectOptionWordsForCheckAndWord);
		
		disableButtonsForCheckWords();
	}

	private void createControlsSelectWordsForCheck() {
		lblSelectWordsForCheck = createLabel("Выбери что проверяем", pnlSelectOptionWordsForCheckAndWord);
		combWordsForCheck = createComboBox(model.getListWordFiles(), pnlSelectOptionWordsForCheckAndWord);
	}

	private void createControlsSelectWordOptionVisibility() {
		lblSelectWordOptionVisible = createLabel("Выбери опцию отображения слов", pnlSelectOptionWordsForCheckAndWord);		
		optWordVisibleGroup = new ButtonGroup();
		optWordVisible = initRadioButton("Показывать слова", optWordVisibleGroup, pnlSelectOptionWordsForCheckAndWord);
		optWordVisible.addActionListener(event -> btnShowWord.setEnabled(false));
		lblWordEmptyOptionVisible = createLabel("", pnlSelectOptionWordsForCheckAndWord);
		optWordNotVisible = initRadioButton("Не показывать слова", optWordVisibleGroup, pnlSelectOptionWordsForCheckAndWord);		
		optWordNotVisible.addActionListener(event -> btnShowWord.setEnabled(true));
		optWordNotVisible.setSelected(true);		
	}

	private JComboBox createComboBox(ArrayList<String> list, JPanel panel) {
		JComboBox comboBox = new JComboBox();
		
		for (String item : list) {
			comboBox.addItem(item);
		}
		panel.add(comboBox);
		
		return comboBox;
	}

	private JButton createButton(String caption, JPanel panel) {
		JButton button = new JButton(caption);
		panel.add(button);
		
		return button;
	}

	private JTextArea createTextArea(JPanel panel) {
		JTextArea textArea = new JTextArea(4, 40);
		panel.add(textArea);
		
		return textArea;
	}

	private JTextField createTextFieldNotEditable(JPanel panel) {
		JTextField textField = new JTextField();
		textField.setEditable(false);
		panel.add(textField);
		
		return textField;
	}

	private JRadioButton initRadioButton(String caption, ButtonGroup group, JPanel panel) {
		JRadioButton radioButton = new JRadioButton(caption);
		group.add(radioButton);
		panel.add(radioButton);
		return radioButton;
	}

	private JLabel createLabel(String caption, JPanel panel) {
		JLabel label = new JLabel(caption);
		panel.add(label);
		return label;
	}

	public void completeInitView() {
		add(pnlSelectOptionWordsForCheckAndWord, BorderLayout.NORTH);
		add(pnlWordExamples, BorderLayout.CENTER);
		add(pnlResult, BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}

	public JButton getButtonCheckWords() {
		return btnStartCheckWords;
	}

	public String getFileWordsToCheck() {
		return (String)combWordsForCheck.getSelectedItem();
	}

	public void disableButtonAndListForStartCheckWords() {
		btnStartCheckWords.setEnabled(false);
		combWordsForCheck.setEnabled(false);
	}

	public JButton getButtonStopCheckWords() {
		return btnStopCheckWords;
	}

	public void enableButtonsForCheckWords() {
		btnStopCheckWords.setEnabled(true);
		btnStopCheckWordsAndSaveResult.setEnabled(true);
		btnRepeatPlayExample.setEnabled(true);
		btnPlayNextExample.setEnabled(true);
		
		if (optWordNotVisible.isSelected()) {
			btnShowWord.setEnabled(true);
		}
		
		btnShowTranslation.setEnabled(true);
		btnPlus.setEnabled(true);
		btnAsterisk.setEnabled(true);
		btnMinus.setEnabled(true);
		btnMinus4.setEnabled(true);
		btnPutWordToDoubtFile.setEnabled(true);
		btnPreviousWord.setEnabled(true);
	}

	public void disableButtonsForCheckWords() {
		btnStopCheckWords.setEnabled(false);
		btnStopCheckWordsAndSaveResult.setEnabled(false);
		btnRepeatPlayExample.setEnabled(false);
		btnPlayNextExample.setEnabled(false);
		btnShowWord.setEnabled(false);
	}

	public void enableButtonAndListForStartCheckWords() {
		btnStartCheckWords.setEnabled(true);
		combWordsForCheck.setEnabled(true);
	}

	public boolean isOptionShowWordSelected() {
		return optWordVisible.isSelected();
	}

	public void displayWord(String word) {
		txtWord.setText(word);
	}

	public void displayTranscription(String transcription) {
		txtTranscription.setText(transcription);
	}

	public JButton getButtonShowTranslation() {
		return btnShowTranslation;
	}

	public void displayTranslation(String translation) {
		txtTranslation.setText(translation);
	}

	public JButton getButtonShowWord() {
		return btnShowWord;
	}

	public JButton getButtonRepeatExample() {
		return btnRepeatPlayExample;
	}

	public JButton getButtonNextExample() {
		return btnPlayNextExample;
	}

	public JButton getButtonPlus() {
		return btnPlus;
	}

	public JButton getButtonAsterisk() {
		return btnAsterisk;
	}

	public JButton getButtonMinus() {
		return btnMinus;
	}

	public JButton getButtonMinus4() {
		return btnMinus4;
	}
	
	public JButton getButtonPutWordToDoubtFile() {
		return btnPutWordToDoubtFile;
	}

	public JButton getButtonPreviousWord() {
		return btnPreviousWord;
	}

	public JTextArea getTextAreaExamples() {
		return txtExamples;
	}

	public void disableButtonPlayNextExample() {
		btnPlayNextExample.setEnabled(false);
	}

	public boolean isOptionShowTranslationSelected() {
		return optTranslationVisible.isSelected();
	}

	public void disableButtonPreviousWord() {
		btnPreviousWord.setEnabled(false);
	}

	public void enableButtonPreviousWord() {
		btnPreviousWord.setEnabled(true);
	}

	public void enableButtonPlayNextExample() {
		btnPlayNextExample.setEnabled(true);
	}

	public void disableButtonShowTranslation() {
		btnShowTranslation.setEnabled(false);
	}

	public void enableButtonShowTranslation() {
		btnShowTranslation.setEnabled(true);
	}
	
	public JButton getButtonStopCheckWordsAndSaveResult() {
		return btnStopCheckWordsAndSaveResult;
	}
	
	public void displayStatistics(String text) {
		lblStatistics.setText(text);
	}

	public void disableButtonPutWordToDoubtFile() {
		btnPutWordToDoubtFile.setEnabled(false);
	}

	public void setEmptyTextFields() {
		txtWord.setText("");
		txtTranscription.setText("");
		txtTranslation.setText("");
		lblStatistics.setText("");;
		txtExamples.setText("");
	}
}
