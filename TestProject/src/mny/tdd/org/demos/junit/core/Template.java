package mny.tdd.org.demos.junit.core;

public class Template {
	
	private String templateText;

	public Template(String templateText) {
		this.templateText = templateText;
	}

	public Template() {
		// TODO Auto-generated constructor stub
	}

	public String render(int score) {
		return templateText + score;
	}

}
