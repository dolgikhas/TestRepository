package mny.checkwords.model;

public class Word {
	private String word;
	private String transcription;
	private String translation;
	private String repeat;
	private ResultCheck resultCheck;
	

	public Word(WordBuilder builder) {
		word = builder.getWord();
		transcription = builder.getTranscription();
		translation = builder.getTranslation();
		repeat = builder.getRepeat();
	}
	
	public static class WordBuilder {
		private String word;
		private String transcription;
		private String translation;
		private String repeat;
		
		public String getWord() {
			return word;
		}
		public String getTranscription() {
			return transcription;
		}
		public String getTranslation() {
			return translation;
		}
		public String getRepeat() {
			return repeat;
		}
		
		public WordBuilder setWord(String word) {
			this.word = word;
			return this;
		}
		public WordBuilder setTranscription(String transcription) {
			this.transcription = transcription;
			return this;
		}
		public WordBuilder setTranslation(String translation) {
			this.translation = translation;
			return this;
		}
		public WordBuilder setRepeat(String repeat) {
			this.repeat = repeat;
			return this;
		}
		
		public Word build() {
			return new Word(this);
		}
		@Override
		public String toString() {
			return "word=" + word + ", transcription=" + transcription + ", translation=" + translation;
		}
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getTranscription() {
		return transcription;
	}
	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	public int getRepeatNumber() {
		return Integer.parseInt(repeat);
	}
	public void setResultCheck(ResultCheck result) {
		this.resultCheck = result;
	}
	public ResultCheck getResultCheck() {
		return resultCheck;
	}
	
}