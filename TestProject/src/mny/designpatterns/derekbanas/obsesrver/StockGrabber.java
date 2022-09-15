package mny.designpatterns.derekbanas.obsesrver;

import java.util.ArrayList;

public class StockGrabber implements Subject {
	private ArrayList<Observer> observers;
	private double ibmPrice;
	private double applPrice;
	private double googPrice;
	
	public StockGrabber() {
 		observers = new ArrayList<Observer>();
	}

	@Override
	public void register(Observer newObserver) {
		observers.add(newObserver);

	}

	@Override
	public void unregister(Observer deleteObserver) {
		System.out.println( "Observer " + observers.indexOf(deleteObserver) + " deleted" );
		observers.remove(deleteObserver);
	}

	@Override
	public void notifyObserver() {
		for ( Observer observer : observers )
			observer.update(ibmPrice, applPrice, googPrice);

	}

	public void setIbmPrice(double newIBMPrice) {
		this.ibmPrice = newIBMPrice;
		notifyObserver();
	}

	public void setApplPrice(double newApplePrice) {
		this.applPrice = newApplePrice;
		notifyObserver();
	}

	public void setGoogPrice(double googPrice) {
		this.googPrice = googPrice;
		notifyObserver();
	}
	
}
