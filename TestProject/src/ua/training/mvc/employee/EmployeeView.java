package ua.training.mvc.employee;

public class EmployeeView {
	public void printEmployeeInformation( EmployeeModel employee ) {
		System.out.println( "Employee [ID=" + employee.getId() +
							" /Name=" + employee.getName() +
							" /Job=" + employee.getJob() + "]" );
	}
	
}
