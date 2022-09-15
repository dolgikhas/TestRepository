package mny.designpatterns.builder.philipstarritt;

public class App {

	public static void main(String[] args) {
		Name name1 = new Name.Builder()
							 .setFirstName( "Philip" )
							 .setMiddleName( "Peter" )
							 .setLastName( "Starritt" )
							 .build();
		System.out.println( name1 );
		
		Address address1 = new Address.Builder()
								.setCity( "Belfast" )
								.setHouseNumber( 111 )
								.setZipCode( "123" )
								.setStreet( "Belfast Street" )
								.build();
		System.out.println( address1 );
		
		Account account = new Account.Builder()
								.setId(111)
								.setEmail( "philip@gmail.com" )
								.setName(name1)
								.setAddress(address1)
								.build();
		System.out.println( account );
	}

}
