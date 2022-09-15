package mny.designpatterns.builder.thecodingzoo;

public class BuilderExample {

	public static void main(String[] args) {
		Person shane = new Person.PersonBuilder()
								 .setId(1)
								 .setFirstName( "Anthony" )
								 .setMiddleName( "Shane" )
								 .setLastName( "Crouch" )
								 .build();
		System.out.println( shane );

		Person jane = new Person.PersonBuilder(2)
				 				.setFirstName( "Jane" )
				 				.setMiddleName( "Sarah" )
				 				.setLastName( "Forest" )
				 				.build();
		System.out.println( jane );
		
		Person monica = new Person.PersonBuilder( "Monica", "Belucchi" )
								  .setId(3)
								  .setMiddleName( "Tania" )
								  .build();
		System.out.println( monica );
	}

}
