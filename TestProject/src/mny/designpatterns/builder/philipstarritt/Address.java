package mny.designpatterns.builder.philipstarritt;

public class Address {
	public int houseNumber;
	public String street;
	public String zipCode;
	public String city;

	private Address( Builder builder ) {
		houseNumber = builder.houseNumber;
		street = builder.street;
		zipCode = builder.zipCode;
		city = builder.city;		
	}
	
	@Override
	public String toString() {
		return "Address [houseNumber=" + houseNumber + ", street=" + street + ", zipCode=" + zipCode + ", city=" + city
				+ "]";
	}

	public static class Builder {
		public int houseNumber;
		public String street;
		public String zipCode;
		public String city;
		
		public Builder setHouseNumber(int houseNumber) {
			this.houseNumber = houseNumber;
			return this;
		}
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}
		public Builder setZipCode(String zipCode) {
			this.zipCode = zipCode;
			return this;
		}
		public Builder setCity(String city) {
			this.city = city;
			return this;
		}
		
		public Address build() {
			return new Address( this );
		}
		
		
		
	}
}