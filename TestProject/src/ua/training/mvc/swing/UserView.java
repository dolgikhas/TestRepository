package ua.training.mvc.swing;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.*;

public class UserView extends JFrame {
	private JTextArea text;
	private JButton sendButton;
	private JLabel info;
	private Controller controller;
	
	public UserView(Controller controller) throws HeadlessException {
		this.controller = controller;
	}

	public void init() {
		setSize(400, 300);
		text = new JTextArea();
		sendButton = new JButton("Send");
		info = new JLabel("info");
		
		add(text, BorderLayout.CENTER);
		add(sendButton, BorderLayout.SOUTH);
		add(info, BorderLayout.NORTH);
		
		sendButton.addActionListener(e -> {
			String textPin = this.text.getText();
			text.setText("");
			int pin = Integer.parseInt(textPin);
			info.setText("Got it: " + pin + " waiting...");
			
			if (controller.checkPin(pin)) {
				info.setText("Right");
			} else {
				info.setText("Wrong try again!");
			}
		});
		
		setVisible(true);
	}
}
