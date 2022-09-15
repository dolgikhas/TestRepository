package ua.training.swing.simpleframe;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class ImageComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private Image image;
	
	public ImageComponent() {
		image = new ImageIcon("icon.gif").getImage();
	}
	
	public void paintComponent(Graphics g) {
		if (image == null) return;
		
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		
		g.drawImage(image, 0, 0, null);
	}
	
}
