package ua.training.mvc.swing.jatt41wala;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Model model = new Model("firstName", "lastName");
		View view = new View();
		Controller controller = new Controller(model, view);
		
		controller.initController();
	}
}
