package mny.designpatterns.mvcexamples.student;

public class MVCPatternExample1 {
	public static void main( String[] args ) {
		Student model = retrieveStudentFromDatabase();
		StudentView view = new StudentView();
		StudentController controller=  new StudentController( model, view );
		controller.updateView();
		controller.setStudentName( "John" );
		controller.updateView();		
	}

	private static Student retrieveStudentFromDatabase() {
		return new Student( "Robert", "10" );
	}
}
