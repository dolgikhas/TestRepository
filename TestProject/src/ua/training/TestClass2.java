package ua.training;

public class TestClass2 {
	
	public static void swap(Integer a, Integer b) {
		int temp = a;
		a = b;
		b = temp;
		System.out.println("a= " + a + " b= " + b);
	}

	public static void main(String[] args) {
		Integer a = 5, b = 4;
		swap(a, b);
		System.out.println("a= " + a + " b= " + b);
	}

}
