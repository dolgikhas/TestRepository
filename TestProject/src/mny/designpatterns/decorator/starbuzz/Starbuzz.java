package mny.designpatterns.decorator.starbuzz;

abstract class Beverage {
	String description = "Unknown Beverage";
	
	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
}

abstract class CondimentDecorator extends Beverage {
	public abstract String getDescription();
}

class Espresso extends Beverage {
	public Espresso() {
		description = "Espresso";
	}
	
	public double cost() {
		return 1.99;
	}
}

class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "House Blend Coffee";
	}
	@Override
	public double cost() {
		return 0.89;
	}
}

class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "Dark Roast Coffee";
	}
	@Override
	public double cost() {
		return 0.99;
	}
}

class Mocha extends CondimentDecorator {
	Beverage beverage;
	
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}

	@Override
	public double cost() {
		return 0.2 + beverage.cost();
	}
	
}

class Milk extends CondimentDecorator {
	Beverage beverage;
	
	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

	@Override
	public double cost() {
		return 0.2 + beverage.cost();
	}
	
}

class Soy extends CondimentDecorator {
	Beverage beverage;
	
	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

	@Override
	public double cost() {
		return 0.2 + beverage.cost();
	}
	
}

class Whip extends CondimentDecorator {
	Beverage beverage;
	
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}

	@Override
	public double cost() {
		return 0.2 + beverage.cost();
	}
	
}


public class Starbuzz {
public static void main(String[] args) {

Beverage beverage = new Espresso();
System.out.println(beverage.getDescription() + " $" + beverage.cost());
//Espresso $1.99

Beverage beverage2 = new DarkRoast();
beverage2 = new Mocha(beverage2);
beverage2 = new Mocha(beverage2);
beverage2 = new Whip(beverage2);
System.out.println(beverage2.getDescription() + " $" + beverage.cost());
//Dark Roast Coffee, Mocha, Mocha, Whip $1.99

Beverage beverage3 = new HouseBlend();
beverage3 = new Soy(beverage3);
beverage3 = new Mocha(beverage3);
beverage3 = new Whip(beverage3);
System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
// House Blend Coffee, Soy, Mocha, Whip $1.49

}
}
