package mny.processwords;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProcessWordsFrame extends JFrame {
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JLabel lblWord;
	private JTextField txtWord;
	private JLabel lblTranscription;
	private JTextField txtTranscription;
	private JLabel lblTranslation;
	private JTextField txtTranslation;
	private JButton btnLoadWordData;
	private JButton btnAddWord;
	private JButton btnUpdateWord;
	private JButton btnDeleteWord;
	private JLabel lblStatistics;

	public void initView() {
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(6, 1));
		
		middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(2, 2));
		
		bottomPanel = new JPanel();
	}
	
	public void createWordControls() {
		lblWord = new JLabel("Слово");
		topPanel.add(lblWord);
		txtWord = new JTextField("");
		topPanel.add(txtWord);
		lblTranscription = new JLabel("Транскрипция");
		topPanel.add(lblTranscription);
		txtTranscription = new JTextField("");;
		topPanel.add(txtTranscription);
		lblTranslation = new JLabel("Перевод");
		topPanel.add(lblTranslation);
		txtTranslation = new JTextField("");;
		topPanel.add(txtTranslation);
		
		btnLoadWordData = new JButton("Загрузить данные");
		middlePanel.add(btnLoadWordData);
		btnAddWord = new JButton("Добавить слово");
		middlePanel.add(btnAddWord);
		btnUpdateWord = new JButton("Обновить слово");
		middlePanel.add(btnUpdateWord);
		btnDeleteWord = new JButton("Удалить слово");
		middlePanel.add(btnDeleteWord);
		
		lblStatistics = new JLabel("...Статистика...");
		bottomPanel.add(lblStatistics);
	}

	public void completeInitView() {
		add(topPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JTextField getWordTextEditor() {
		return txtWord;
	}

	public void setStatistics(String text) {
		lblStatistics.setText(text);
	}

	public String getWord() {
		return txtWord.getText();
	}

	public void setTranscription(String transcription) {
		txtTranscription.setText(transcription);		
	}

	public void setTranslation(String translation) {
		txtTranslation.setText(translation);		
	}

	public JButton getBtnLoadWordsData() {
		return btnLoadWordData;
	}
}
