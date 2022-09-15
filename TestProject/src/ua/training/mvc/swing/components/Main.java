package ua.training.mvc.swing.components;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ComponentFrame frame = new ComponentFrame();
//			frame.pack();
			frame.setTitle("Components");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
