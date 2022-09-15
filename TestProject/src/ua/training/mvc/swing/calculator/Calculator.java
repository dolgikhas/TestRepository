package ua.training.mvc.swing.calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Calculator {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			CalculatorFrame frame = new CalculatorFrame();
			frame.pack();
			frame.setTitle("Calculator");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
