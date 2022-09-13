package mny.designpatterns.strategy.ducks;

public class Quack implements QuackBehaviour {
	@Override
	public void quack() {
		System.out.println("Quack");
	}

}
