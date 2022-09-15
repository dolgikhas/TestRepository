package mny.designpatterns.builder.philipstarritt;

public class Account {
	private int id;
	private String email;
	private Name name;
	private Address address;
	
	private Account( Builder builder ) {
		id = builder.id;
		email = builder.email;
		name = builder.name;
		address = builder.address;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", name=" + name + ", address=" + address + "]";
	}

	public static class Builder {
		private int id;
		private String email;
		private Name name;
		private Address address;
		
		public Builder setId(final int id) {
			this.id = id;
			return this;
		}
		public Builder setEmail(final String email) {
			this.email = email;
			return this;
		}
		public Builder setName(final Name name) {
			this.name = name;
			return this;
		}
		public Builder setAddress(final Address address) {
			this.address = address;
			return this;
		}
		
		public Account build() {
			return new Account( this );
		}
		
		
		
	}
}
