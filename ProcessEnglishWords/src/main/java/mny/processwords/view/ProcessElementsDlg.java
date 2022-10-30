package mny.processwords.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class ProcessElementsDlg extends JDialog {
	private JTextArea textArea;
	private JPanel bottomPanel;
	private JButton btnOK;
	private JButton btnCancel;
	private JFrame owner;
	
	public ProcessElementsDlg(JFrame owner) {
		this.owner = owner;
	}
	
	public void initView(ArrayList<String> elements) {
		textArea = new JTextArea();
		for (String element : elements) {
			textArea.append(element + "\n");
		}		
		add(textArea, BorderLayout.NORTH);
		
		btnOK = new JButton("OK");
		btnCancel = new JButton("Cancel");
						
		bottomPanel = new JPanel();
		bottomPanel.add(btnOK);
		bottomPanel.add(btnCancel);
		add(bottomPanel, BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}


}
