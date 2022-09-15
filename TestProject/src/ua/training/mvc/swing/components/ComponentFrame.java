package ua.training.mvc.swing.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class ComponentFrame extends JFrame {
	public ComponentFrame() {
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("string 1");
		comboBox.addItem("string 2");
		comboBox.addItem("string 3");
		
		JButton button = new JButton("Send");
		button.addActionListener( event -> {
			String text = comboBox.getItemAt(comboBox.getSelectedIndex());
			
			JOptionPane.showMessageDialog(null, text, "info",
					JOptionPane.INFORMATION_MESSAGE);
		});
		
		add(comboBox, BorderLayout.WEST);
		add(button, BorderLayout.EAST);
		
		pack();
		
	}
}
