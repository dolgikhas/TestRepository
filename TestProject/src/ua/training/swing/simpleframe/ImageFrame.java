package ua.training.swing.simpleframe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ImageFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	
	public ImageFrame() {
		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");
		
		yellowButton.addActionListener(new MessageAction("Yellow"));
		blueButton.addActionListener(new MessageAction("Blue"));
		redButton.addActionListener(new MessageAction("Red"));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(yellowButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);
//		pack();
		
		add(buttonPanel);
	}
	
	private class MessageAction implements ActionListener {
		private String message;
		
		public MessageAction(String m) {
			message = m;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "clicked " + message, "info",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void init() {
		setSize(300, 200);
		setLocation(200, 200);
		
		Image img = new ImageIcon("icon.gif").getImage();
		setIconImage(img);

	}
}
