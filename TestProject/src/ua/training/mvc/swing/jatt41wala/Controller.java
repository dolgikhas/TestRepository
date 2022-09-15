package ua.training.mvc.swing.jatt41wala;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controller {
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public void initController() {
		view.initView();
		
		view.getBtnSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveName();
			}
					
			});
	}

	private void saveName() {
		model.setFirstName(view.getTxtFirstName().getText());
		model.setLastName(view.getTxtLastName().getText());
		
		JOptionPane.showMessageDialog(null, "first name: " + model.getFirstName() + 
			" and last name: " + model.getLastName() + " are saved", "info",
			JOptionPane.INFORMATION_MESSAGE);
		
	}
	
}
