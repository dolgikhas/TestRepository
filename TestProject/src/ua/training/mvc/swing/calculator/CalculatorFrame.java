package ua.training.mvc.swing.calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorFrame extends JFrame {
	private JButton display;
	private JPanel panel;
	private double result;
	private String lastCommand;
	private boolean start;
	
	public CalculatorFrame() {
		setLayout(new BorderLayout());
		result = 0;
		lastCommand = "=";
		start = true;
		
		display = new JButton("0");
		display.setEnabled(false);
		add(display, BorderLayout.NORTH);
		
		ActionListener insert = new InsertAction();
		ActionListener command = new CommandAction();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		
		addButon("7", insert);
		addButon("8", insert);
		addButon("9", insert);
		addButon("/", command);
		
		addButon("4", insert);
		addButon("5", insert);
		addButon("6", insert);
		addButon("*", command);

		addButon("1", insert);
		addButon("2", insert);
		addButon("3", insert);
		addButon("-", command);

		addButon("0", insert);
		addButon(".", insert);
		addButon("=", command);
		addButon("+", command);

		add(panel, BorderLayout.CENTER);
	}

	
	private void addButon(String label, ActionListener listener) {
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel.add(button);
	}

	private class InsertAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String input = event.getActionCommand();
			if (start) {
				display.setText("");
				start = false;
			}
			display.setText(display.getText() + input);
		}
		
	}
	
	private class CommandAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			
			if (start) {
				if (command.equals("-")) {
					display.setText(command);
					start = false;
				} else {
					lastCommand = command;
				}
			} else {
				calculate(Double.parseDouble(display.getText()));
				lastCommand = command;
				start = true;
			}
			
		}
	}
	
	public void calculate(double number) {
		if (lastCommand.equals("+")) result += number;
		else if (lastCommand.equals("-")) result -= number;
		else if (lastCommand.equals("*")) result *= number;
		else if (lastCommand.equals("/")) result /= number;
		else if (lastCommand.equals("=")) result = number;
		display.setText("" + result);
	}
}


