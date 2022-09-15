package ua.training.mvc.swing.jatt41wala;

import java.awt.BorderLayout;

import javax.swing.*;

public class View extends JFrame {
	private JLabel ldlFirstName;
	private JTextField txtFirstName;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JButton btnSave;
	
	public JLabel getLdlFirstName() {
		return ldlFirstName;
	}
	
	public void setLdlFirstName(JLabel ldlFirstName) {
		this.ldlFirstName = ldlFirstName;
	}
	
	public JTextField getTxtFirstName() {
		return txtFirstName;
	}
	
	public void setTxtFirstName(JTextField txtFirstName) {
		this.txtFirstName = txtFirstName;
	}
	
	public JLabel getLblLastName() {
		return lblLastName;
	}
	
	public void setLblLastName(JLabel lblLastName) {
		this.lblLastName = lblLastName;
	}
	
	public JTextField getTxtLastName() {
		return txtLastName;
	}
	
	public void setTxtLastName(JTextField txtLastName) {
		this.txtLastName = txtLastName;
	}
	
	public JButton getBtnSave() {
		return btnSave;
	}
	
	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}
	
	public void initView() {
		ldlFirstName = new JLabel("first name: ");
		txtFirstName = new JTextField();
		lblLastName = new JLabel("last name: ");
		txtLastName = new JTextField();
		btnSave = new JButton("Save");

		setSize(400, 300);
		
		add(ldlFirstName, BorderLayout.NORTH);
		add(txtFirstName, BorderLayout.NORTH);
		add(lblLastName, BorderLayout.CENTER);
		add(txtLastName, BorderLayout.CENTER);
		add(btnSave, BorderLayout.SOUTH);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}
}
