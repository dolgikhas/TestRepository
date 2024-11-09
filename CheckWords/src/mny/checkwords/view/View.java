package mny.checkwords.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import mny.checkwords.model.Model;

public class View extends JFrame {
	private Model model;
	
	private JPanel pnlSelectOptionWordsForCheckAndWord;
	private JLabel lblSelectOption;
	private ButtonGroup optVisibleGroup;
	private JRadioButton optVisible;
	private JLabel lblEmpty;
	private JRadioButton optNotVisible;
	private JLabel lblSelectWordsForCheck;
	private JComboBox combWordsForCheck;
	private JLabel lblWord;
	private JTextField txtWord;
	private JLabel lblTranscription;
	private JTextField txtTranscription;
	private JLabel lblTranslation;
	private JTextField txtTranslation;
	private JLabel lblExamples;
	private JButton btnCheckWords;
	private JButton btnStopCheckWords;
	private JButton btnRepeatPlayExample;
	private JButton btnPlayNextExample;
	private JButton btnShowWord;
	private JLabel lblEmpty2;

	private JPanel pnlWordExamples;	
	private JTextArea txtExamples;
	
	private JPanel pnlResult;
	private JButton btnShowTranslation;
	private JButton btnPlus;
	private JButton btnAsterisk;
	private JButton btnMinus;
	private JButton btnMinus4;
	private JButton btnPreviousWord;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void initView() {
		pnlSelectOptionWordsForCheckAndWord = new JPanel();
		pnlSelectOptionWordsForCheckAndWord.setLayout(new GridLayout(10, 2));
		pnlWordExamples = new JPanel();
		pnlResult = new JPanel();
	}

	public void createControls() {
		createControlsSelectOptionVisibility();
		
		createControlsSelectWordsForCheck();

		createButtonsForCheckWords();

		createControlsWithWordAttributes();
		
		createControlExamplesArea();

		createControlsResultsShowAndPreviousWord();
	}

	private void createControlExamplesArea() {
		txtExamples = createTextArea(pnlWordExamples);
		txtExamples.setEditable(false);
	}

	private void createControlsResultsShowAndPreviousWord() {
		btnShowTranslation = createButton("Показать перевод(-ы)", pnlResult);
		btnPlus = createButton("+", pnlResult);
		btnAsterisk = createButton("*", pnlResult);
		btnMinus = createButton("-", pnlResult);
		btnMinus4 = createButton("-4", pnlResult);
		btnPreviousWord = createButton("Предыдущее", pnlResult);
		
		disableControlsResults();
	}

	public void disableControlsResults() {
		btnShowTranslation.setEnabled(false);
		btnPlus.setEnabled(false);
		btnAsterisk.setEnabled(false);
		btnMinus.setEnabled(false);
		btnMinus4.setEnabled(false);
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
	}

	private void createButtonsForCheckWords() {
		btnCheckWords = createButton("Поехали!", pnlSelectOptionWordsForCheckAndWord);
		btnStopCheckWords = createButton("Остановить проверку", pnlSelectOptionWordsForCheckAndWord);
		btnRepeatPlayExample = createButton("Повторить пример", pnlSelectOptionWordsForCheckAndWord);
		btnPlayNextExample = createButton("Следующий пример", pnlSelectOptionWordsForCheckAndWord);
		btnShowWord = createButton("Показать слово", pnlSelectOptionWordsForCheckAndWord);
		lblEmpty2 = createLabel("", pnlSelectOptionWordsForCheckAndWord);
		
		disableButtonsForCheckWords();
	}

	private void createControlsSelectWordsForCheck() {
		lblSelectWordsForCheck = createLabel("Выбери что проверяем", pnlSelectOptionWordsForCheckAndWord);
		combWordsForCheck = createComboBox(model.getListWordFiles(), pnlSelectOptionWordsForCheckAndWord);
	}

	private void createControlsSelectOptionVisibility() {
		lblSelectOption = createLabel("Выбери опцию отображения слов", pnlSelectOptionWordsForCheckAndWord);		
		optVisibleGroup = new ButtonGroup();
		optVisible = initRadioButton("Показывать слова", optVisibleGroup, pnlSelectOptionWordsForCheckAndWord);
		optVisible.addActionListener(event -> btnShowWord.setEnabled(false));
		lblEmpty = createLabel("", pnlSelectOptionWordsForCheckAndWord);
		optNotVisible = initRadioButton("Не показывать слова", optVisibleGroup, pnlSelectOptionWordsForCheckAndWord);		
		optNotVisible.addActionListener(event -> btnShowWord.setEnabled(true));
		optNotVisible.setSelected(true);		
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
		return btnCheckWords;
	}

	public String getFileWordsToCheck() {
		return (String)combWordsForCheck.getSelectedItem();
	}

	public void disableButtonAndListForStartCheckWords() {
		btnCheckWords.setEnabled(false);
		combWordsForCheck.setEnabled(false);
	}

	public JButton getButtonStopCheckWords() {
		return btnStopCheckWords;
	}

	public void enableButtonsForCheckWords() {
		btnStopCheckWords.setEnabled(true);
		btnRepeatPlayExample.setEnabled(true);
		btnPlayNextExample.setEnabled(true);
		
		if (optNotVisible.isSelected()) {
			btnShowWord.setEnabled(true);
		}
		
		btnShowTranslation.setEnabled(true);
		btnPlus.setEnabled(true);
		btnAsterisk.setEnabled(true);
		btnMinus.setEnabled(true);
		btnMinus4.setEnabled(true);
		btnPreviousWord.setEnabled(true);
	}

	public void disableButtonsForCheckWords() {
		btnStopCheckWords.setEnabled(false);
		btnRepeatPlayExample.setEnabled(false);
		btnPlayNextExample.setEnabled(false);
		btnShowWord.setEnabled(false);
	}

	public void enableButtonAndListForStartCheckWords() {
		btnCheckWords.setEnabled(true);
		combWordsForCheck.setEnabled(true);
	}

	public boolean isOptionShowWordSelected() {
		return optVisible.isSelected();
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

	public JButton getButtonPreviousWord() {
		return btnPreviousWord;
	}

	public JTextArea getTextAreaExamples() {
		return txtExamples;
	}

	public void disableButtonPlayNextExample() {
		btnPlayNextExample.setEnabled(false);
	}
}
