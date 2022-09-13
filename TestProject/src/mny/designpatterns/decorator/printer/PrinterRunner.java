package mny.designpatterns.decorator.printer;

interface PrinterInterface {
	void print();
}

class Printer implements PrinterInterface {
	String value;

	public Printer(String value) {
		this.value = value;
	}

	@Override
	public void print() {
		System.out.print(value);
	}
}

abstract class Decorator implements PrinterInterface {
	protected PrinterInterface component;
	
	public Decorator(PrinterInterface c) {
		component = c;
	}
	
	@Override
	public void print() {
		component.print();
	}
}

class QuotesDecorator extends Decorator {
	
	public QuotesDecorator(PrinterInterface component) {
		super(component);
	}

	@Override
	public void print() {
		System.out.print("\"");
		component.print();
		System.out.print("\"");
	}	
}

class LeftBracketDecorator extends Decorator {
	public LeftBracketDecorator(PrinterInterface component) {
		super(component);
	}

	@Override
	public void print() {
		System.out.print("[");
		component.print();
	}	
}

class RightBracketDecorator extends Decorator {
	public RightBracketDecorator(PrinterInterface component) {
		super(component);
	}

	@Override
	public void print() {
		component.print();
		System.out.print("]");
	}	
}

public class PrinterRunner {
	public static void main(String[] args) {
		PrinterInterface printer = new RightBracketDecorator(
				new LeftBracketDecorator(
						new QuotesDecorator(new Printer("Hello!"))));
		printer.print();
	}
}
