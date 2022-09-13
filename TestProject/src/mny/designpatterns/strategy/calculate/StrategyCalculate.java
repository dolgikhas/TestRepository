package mny.designpatterns.strategy.calculate;

interface Strategy {
	public int execute(int a, int b);
}

class StrategyAdd implements Strategy {
	@Override
	public int execute(int a, int b) {
		return a + b;
	}	
}

class StrategySubstract implements Strategy {
	@Override
	public int execute(int a, int b) {
		return a - b;
	}	
}

class StrategyMultiply implements Strategy {
	@Override
	public int execute(int a, int b) {
		return a * b;
	}	
}

class Context {
	private Strategy strategy;
	
	public void setStrategy(Strategy s) {
		strategy = s;
	}
	
	public int executeStrategy(int a, int b) {
		return strategy.execute(a, b);
	}
	
}


public class StrategyCalculate {
	public static void main(String args[]) {
		Context context = new Context();
		context.setStrategy(new StrategyAdd());
		System.out.println(context.executeStrategy(1, 1));	// 2
		
		context.setStrategy(new StrategySubstract());
		System.out.println(context.executeStrategy(1, 1));	// 0
		
		context.setStrategy(new StrategyMultiply());
		System.out.println(context.executeStrategy(1, 1));	// 1
	}
}
