package ua.training.mvc.swing;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);		
		UserView userView = new UserView(controller);
		SwingUtilities.invokeLater(() -> {
			userView.init();			
		});
	}

}
