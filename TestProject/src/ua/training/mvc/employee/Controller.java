package ua.training.mvc.employee;

public class Controller {
	EmployeeModel model;
	EmployeeView view;
	
	public String getModelName() {
		return model.getName();
	}

	public void setModelName(String name) {
		model.setName( name );
	}

	public String getModelJob() {
		return model.getJob();
	}

	public void setModelJob(String job) {
		model.setJob( job );
	}

	public Controller(EmployeeModel model, EmployeeView view) {
		super();
		this.model = model;
		this.view = view;
	}

	public void updateView() {
		view.printEmployeeInformation(model);
	}
	
	public void setEmployeeId( int id ) {
		model.setId( id );
	}
	
	public int getEmployeeId() {
		return model.getId();
	}

	
}
