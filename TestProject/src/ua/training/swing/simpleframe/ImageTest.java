package ua.training.swing.simpleframe;

import java.awt.*;

import javax.swing.JFrame;

public class ImageTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ImageFrame frame = new ImageFrame();
			frame.init();
			frame.setTitle("ImageTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
