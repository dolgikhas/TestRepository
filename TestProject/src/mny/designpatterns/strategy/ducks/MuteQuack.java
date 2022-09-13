package mny.designpatterns.strategy.ducks;

public class MuteQuack implements QuackBehaviour {
	@Override
	public void quack() {
		System.out.println("<< Silence >>");
	}

}
