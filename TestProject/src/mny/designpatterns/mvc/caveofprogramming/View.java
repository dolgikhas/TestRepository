package mny.designpatterns.mvc.caveofprogramming;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class View extends JFrame {
	private Model model;

	public View(Model model) throws HeadlessException {
		this.model = model;
	};
}
