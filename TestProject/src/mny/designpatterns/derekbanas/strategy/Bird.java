package mny.designpatterns.derekbanas.strategy;

public class Bird extends Animal {
	public Bird() {
		flyingType = new ItFlys();
	}

}
