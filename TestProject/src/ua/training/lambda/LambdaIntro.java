package ua.training.lambda;

public class LambdaIntro {

	@FunctionalInterface
	public interface ISum {
		public int sum( int a, int b );
	}
	
	@FunctionalInterface
	public interface ISqrt{
		public double sqrt( double x );
	}
	
	public static void main(String[] args) {
//		ISum summer = new ISum() {
//			@Override
//			public int sum(int a, int b) {
//				return a + b;
//			}			
//		};
		
		ISum summer2 = (a, b) -> a + b;
		
		ISum summer3 = (a, b) -> {
			if (a == 0) return 0;
			return a + b;
		};
		
		ISqrt sqrt = x -> Math.sqrt(x);
	}
}
