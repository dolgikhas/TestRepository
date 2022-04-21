package mny.designpatterns.mvcexamples.employee;

public class Controller {
	Employee model;
	View view;

	public Controller(Employee model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public void updateView() {
		view.printEmployeeDetails( model.getName(), model.getId(), model.getDepartment());
	}

	public void setEmployeeName(String name) {
		model.setName( name );
	}

	public void setEmployeeId(String id) {
		model.setId( id );
	}

	public void setEmployeeDepartment(String department) {
		model.setDepartment(department);
	}
	
	

}
