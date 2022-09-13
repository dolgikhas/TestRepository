package mny.designpatterns.strategy.ducks;

public class FlyRocketPowdered implements FlyBehaviour {

	@Override
	public void fly() {
		System.out.println("I'm flying with a rocket!");
	}

}
