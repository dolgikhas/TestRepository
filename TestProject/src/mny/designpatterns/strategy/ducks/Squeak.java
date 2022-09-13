package mny.designpatterns.strategy.ducks;

public class Squeak implements QuackBehaviour {

	@Override
	public void quack() {
		System.out.println("Squeak");
	}

}
