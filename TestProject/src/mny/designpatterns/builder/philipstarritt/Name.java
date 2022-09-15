package mny.designpatterns.builder.philipstarritt;

public class Name {
	public String firstName;
	public String middleName;
	public String lastName;

	private Name( Builder builder ) {
		firstName = builder.firstName;
		middleName = builder.middleName;
		lastName = builder.lastName;
	}
	
	@Override
	public String toString() {
		return "Name [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + "]";
	}

	public static class Builder {
		public String firstName;
		public String middleName;
		public String lastName;
		
		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		public Builder setMiddleName(String middleName) {
			this.middleName = middleName;
			return this;
		}
		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		public Name build() {
			return new Name( this );
		}
	

	}

}