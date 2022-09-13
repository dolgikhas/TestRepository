package mny.designpatterns.strategy.developer;

interface Activity {
	public void justDoIt();
}

class Coding implements Activity {
	@Override
	public void justDoIt() {
		System.out.println("Coding");
	}	
}

class Reading implements Activity {
	@Override
	public void justDoIt() {
		System.out.println("Reading");
	}	
}

class Developer {
	Activity activity;
	
	public void setActivity(Activity act) {
		activity = act;
	}

	public void executeActivity() {
		activity.justDoIt();
	}
}

public class DeveloperRunner {

	public static void main(String[] args) {
		Developer developer = new Developer();
		developer.setActivity(new Coding());
		developer.executeActivity();

		developer.setActivity(new Reading());
		developer.executeActivity();
	}

}
