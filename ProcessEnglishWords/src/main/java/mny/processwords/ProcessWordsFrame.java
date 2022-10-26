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
	private JLabel lblWord;
	private JTextField txtWord;
	private JLabel lblTranscription;
	private JTextField txtTranscription;
	private JLabel lblTranslation;
	private JTextField txtTranslation;
	private JButton btnGetData;
	private JButton btnAddWord;
	private JButton btnUpdateWord;
	private JButton btnDeleteWord;

	public void initView() {
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(5, 2));		
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
		btnGetData = new JButton("Загрузить данные");
		topPanel.add(btnGetData);
		btnAddWord = new JButton("Добавить слово");
		topPanel.add(btnAddWord);
		btnUpdateWord = new JButton("Обновить слово");
		topPanel.add(btnUpdateWord);
		btnDeleteWord = new JButton("Удалить слово");
		topPanel.add(btnDeleteWord);
	}

	public void completeInitView() {
		add(topPanel, BorderLayout.NORTH);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

}
