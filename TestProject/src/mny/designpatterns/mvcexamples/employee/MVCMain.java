package mny.designpatterns.mvcexamples.employee;

public class MVCMain {

	public static void main( String[] args ) {
		Employee model = retrieveFromDB();
		View view = new View();
		Controller controller = new Controller( model, view );
		controller.updateView();
		
		controller.setEmployeeName( "Nirnay" );
		controller.setEmployeeId( "12" );
		controller.setEmployeeDepartment( "Security" );
		controller.updateView();
	}

	private static Employee retrieveFromDB() {
		return new Employee.Build()
				.setName( "Ann" )
				.setId( "11" )
				.setDepartment( "Salesforce" )
				.build();
	}
}
