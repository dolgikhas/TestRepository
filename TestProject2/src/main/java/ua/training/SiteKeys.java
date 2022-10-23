package ua.training;

public class SiteKeys {
	private final String address;
	private final String keyTranscription;
	private final String keyExamples;
	private final String keyExamplesExtra;
	private final String keyTranslation;
	
	private SiteKeys(Builder builder) {
		this.address = builder.getAddress();
		this.keyTranscription = builder.getKeyTranscription();
		this.keyExamples = builder.getKeyExamples();
		this.keyExamplesExtra = builder.getKeyExamplesExtra();
		this.keyTranslation = builder.getKeyTranslation();
	}
	
	public String getKeyExamplesExtra() {
		return keyExamplesExtra;
	}

	public String getAddress() {
		return address;
	}

	public String getKeyTranscription() {
		return keyTranscription;
	}

	public String getKeyExamples() {
		return keyExamples;
	}

	public String getKeyTranslation() {
		return keyTranslation;
	}

	public static class Builder {
		private String address;
		private String keyTranscription;
		private String keyExamples;
		private String keyExamplesExtra;
		private String keyTranslation;
		
		public String getKeyExamplesExtra() {
			return keyExamplesExtra;
		}
		public String getAddress() {
			return address;
		}
		public String getKeyTranscription() {
			return keyTranscription;
		}
		public String getKeyExamples() {
			return keyExamples;
		}
		public String getKeyTranslation() {
			return keyTranslation;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}
		public Builder setKeyTranscription(String keyTranscription) {
			this.keyTranscription = keyTranscription;
			return this;
		}
		public Builder setKeyExamples(String keyExamples) {
			this.keyExamples = keyExamples;
			return this;
		}
		public Builder setKeyTranslation(String keyTranslation) {
			this.keyTranslation = keyTranslation;
			return this;
		}
		public Builder setKeyExamplesExtra(String keyExamplesExtra) {
			this.keyExamplesExtra = keyExamplesExtra;
			return this;
		}

		public Builder() {			
		}
		
		public SiteKeys build() {
			return new SiteKeys(this);
		}		
	}	
}