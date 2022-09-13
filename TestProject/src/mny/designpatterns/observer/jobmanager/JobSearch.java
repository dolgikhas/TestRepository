package mny.designpatterns.observer.jobmanager;

import java.util.ArrayList;
import java.util.List;

interface Observer {
	public void handleEvent(List<String> vacancies);	
}

interface Observed {
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}

class Subscriber implements Observer {
	String name;
	
	public Subscriber(String name) {
		this.name = name;
	}

	@Override
	public void handleEvent(List<String> vacancies) {
		System.out.println("Dear " + name + "\nWe have some changes in vacancies: "
			+ "\n" + vacancies + "\n");
	}	
}


class JobSite implements Observed {
	List<String> vacancies = new ArrayList<>();
	List<Observer> subscribers = new ArrayList<>();
	
	public void addVacancy(String vacancy) {
		vacancies.add(vacancy);
		notifyObservers();
	}
	
	public void removeVacancy(String vacancy) {
		vacancies.remove(vacancy);
		notifyObservers();
	}

	@Override
	public void addObserver(Observer observer) {
		subscribers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		subscribers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : subscribers) {
			observer.handleEvent(vacancies);
		}
	}	
}

public class JobSearch {
	public static void main(String[] args) {
		JobSite jobSite = new JobSite();
		jobSite.addVacancy("first Java position");
		jobSite.addVacancy("second Java position");
		
		Observer subsrciber1 = new Subscriber("Eugene");
		Observer subsrciber2 = new Subscriber("Peter");
		
		jobSite.addObserver(subsrciber1);
		jobSite.addObserver(subsrciber2);

		jobSite.addVacancy("third Java position");
		
		jobSite.removeVacancy("first Java position");
	}	
}