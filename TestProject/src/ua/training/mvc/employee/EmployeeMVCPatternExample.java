package ua.training.mvc.employee;

public class EmployeeMVCPatternExample {

	public static void main(String[] args) {
		EmployeeModel model = new EmployeeModel( 10, "John Smith", "Analyst" );
		EmployeeView view = new EmployeeView();
		Controller controller = new Controller( model, view );
		controller.updateView();
		
		controller.setEmployeeId( 200 );
		controller.setModelJob( "Programmer" );
		controller.setModelName( "Dan Travolta" );
		controller.updateView();

	}

}
