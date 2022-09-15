package mny.designpatterns.mvc.caveofprogramming;

import javax.swing.SwingUtilities;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {
				runApplication();
			}
		});

	}
	
	public static void runApplication() {
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller( model, view );
		
	}

}
