package mny.designpatterns.builder.thecodingzoo;

public class Person {
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	
	private Person(int id, String firstName, String middleName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public static class PersonBuilder {
		private int id;
		private String firstName;
		private String middleName;
		private String lastName;
		
		public PersonBuilder(int id, String firstName, String lastName) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public PersonBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public PersonBuilder(int id) {
			this.id = id;
		}

		public PersonBuilder() {
		}
		
		public PersonBuilder setId(int id) {
			this.id = id;
			return this;
		}
		public PersonBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		public PersonBuilder setMiddleName(String middleName) {
			this.middleName = middleName;
			return this;
		}
		public PersonBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Person build() {
			validate();
			return new Person( id, firstName, middleName, lastName );
		}
		
		private void validate() {
			if ( (0 == id) || firstName.isEmpty() ||
					middleName.isEmpty() || lastName.isEmpty() )
				throw new IllegalArgumentException( "Required Parameters missing. " +
					"Person must have id, first name, middle name, last name" );
			
		}
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ "]";
	}
	
	
}
