package mny.designpatterns.decorator.developer;

interface Developer {
	public String makeJob();
}

class DeveloperDecorator implements Developer {
	Developer developer;

	public DeveloperDecorator(Developer developer) {
		this.developer = developer;
	}

	@Override
	public String makeJob() {
		return developer.makeJob();
	}	
}

class JavaDeveloper implements Developer {
	@Override
	public String makeJob() {
		return "Write Java code.";
	}	
}

class SeniorJavaDeveloper extends DeveloperDecorator {
	
	public SeniorJavaDeveloper(Developer developer) {
		super(developer);
	}

	public String makeCodeReview() {
		return " Make code review.";
	}
	
	@Override
	public String makeJob() {
		return super.makeJob() + makeCodeReview();
	}
}

class JavaTeamLead extends DeveloperDecorator {

	public JavaTeamLead(Developer developer) {
		super(developer);
	}
	public String sendWeekReport() {
		return " Send week report to customer.";
	}
	
	@Override
	public String makeJob() {
		return super.makeJob() + sendWeekReport();
	}
}

public class JavaDeveloperRunner {

	public static void main(String[] args) {
		Developer developer = new JavaTeamLead(
					new SeniorJavaDeveloper(
							new JavaDeveloper()));
		
		System.out.println(developer.makeJob());
	}

}
