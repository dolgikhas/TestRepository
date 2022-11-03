package mny.processwords.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;

public class ProcessElementsDlg extends JDialog {
	private JButton btnOK;
	private JTextArea textArea;

	public ProcessElementsDlg(ProcessWordsFrame owner, ArrayList<String> elements) {
		super(owner, "Process elements dialog", true);
		
		textArea = new JTextArea(20, 200);
		for (String element : elements) {
			textArea.append(element + "\n");
		}
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel();
		
		btnOK = new JButton("OK");
		bottomPanel.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(event -> {
			setVisible(false);
		});
		bottomPanel.add(btnCancel);
		
		add(bottomPanel, BorderLayout.SOUTH);
		setSize(400, 500);
	}
	
	public JButton getBtnOK() {
		return btnOK;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
}
