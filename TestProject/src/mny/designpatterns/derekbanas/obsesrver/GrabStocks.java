package mny.designpatterns.derekbanas.obsesrver;

public class GrabStocks {

	public static void main(String[] args) {
		StockGrabber stockGrabber = new StockGrabber();
		
		StockObserver observer1 = new StockObserver( stockGrabber );
		stockGrabber.setIbmPrice( 197.0 );
		stockGrabber.setApplPrice( 677.6 );
		stockGrabber.setGoogPrice( 676.4 );

		StockObserver observer2 = new StockObserver( stockGrabber );
		stockGrabber.setIbmPrice( 197.0 );
		stockGrabber.setApplPrice( 677.6 );
		stockGrabber.setGoogPrice( 676.4 );
		
		stockGrabber.unregister(observer1);
	}

}
