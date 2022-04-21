package mny.designpatterns.mvcexamples.employee;

public class View {
	public void printEmployeeDetails( String name, String id,
					String department ) {
		System.out.println( "Employee Details:" );
		System.out.println( "Employee name: " + name );
		System.out.println( "Employee id: " + id );
		System.out.println( "Employee department: " + department );
		System.out.println();		
	}
}
