package mny.designpatterns.observer.weatherstation;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

interface DisplayElement {
	public void display();
}

class WeatherData extends Observable {
	private float temperature;
	private float humidity;
	private float pressure;
	
	public void measurementsChanged() {
		setChanged();
		notifyObservers();
	}
	
	public void setMeasurements(float t, float h, float p) {
		temperature = t;
		humidity = h;
		pressure = p;
		
		measurementsChanged();
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}	
}

class CurrentConditionsDisplay implements Observer, DisplayElement {
	Observable observable;
	private float temperature;
	private float humidity;
	
	public CurrentConditionsDisplay(Observable ob) {
		observable = ob;
		ob.addObserver(this);
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)obs;
			temperature = weatherData.getTemperature();
			humidity = weatherData.getHumidity();
			display();
		}
	}

	@Override
	public void display() {
		System.out.println("Current conditions: " + temperature
			+ "F degrees and " + humidity + "% humidity" );
	}
	
}

class StatisticsDisplay implements Observer, DisplayElement {
	Observable observable;
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum = 0.0f;
	private int numReadings;

	public StatisticsDisplay(Observable ob) {
		observable = ob;
		ob.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum/numReadings)
				+ "/" + maxTemp + "/" + minTemp);
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherData ) {
			WeatherData weatherData = (WeatherData)obs;
			float temp = weatherData.getTemperature(); 
			tempSum += temp;
			numReadings++;
			
			if (temp > maxTemp) {
				maxTemp = temp;
			}
			if (temp < minTemp) {
				minTemp = temp;
			}		
			display();
		}
	}
}

class ForecastDisplay implements Observer, DisplayElement {
	Observable observable;
	private float currentPressure = 29.92f;
	private float lastPressure;
	
	public ForecastDisplay(Observable ob) {
		observable = ob;
		ob.addObserver(this);
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)obs;
			lastPressure = currentPressure;
			currentPressure = weatherData.getPressure();
			display();
		}
	}

	@Override
	public void display() {
		System.out.print("Forecast: ");
		if (currentPressure > lastPressure) {
			System.out.println("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			System.out.println("More of the same");
		} else {
			System.out.println("Watch out for cooler, rainy weather");
		}		
	}	
}

class HeatIndexDisplay implements Observer, DisplayElement {
	Observable observable;
	private float heatIndex = 0.0f;
	

	public HeatIndexDisplay(Observable ob) {
		observable = ob;
		ob.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Heat index is " + heatIndex);
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)obs;
			heatIndex = computeHeatIndex(weatherData.getTemperature(), weatherData.getHumidity());
			display();
		}		
	}
	
	private float computeHeatIndex(float t, float rh) {
		float index = (float)((16.923 + (0.185212 * t) + (5.37941 * rh) - (0.100254 * t * rh) 
			+ (0.00941695 * (t * t)) + (0.00728898 * (rh * rh)) 
			+ (0.000345372 * (t * t * rh)) - (0.000814971 * (t * rh * rh)) +
			(0.0000102102 * (t * t * rh * rh)) - (0.000038646 * (t * t * t)) + (0.0000291583 * 
			(rh * rh * rh)) + (0.00000142721 * (t * t * t * rh)) + 
			(0.000000197483 * (t * rh * rh * rh)) - (0.0000000218429 * (t * t * t * rh * rh)) +
			0.000000000843296 * (t * t * rh * rh * rh)) -
			(0.0000000000481975 * (t * t * t * rh * rh * rh)));
		return index;
	}
	
}

public class WeatherStation {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);
		
		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
		
	}


}
