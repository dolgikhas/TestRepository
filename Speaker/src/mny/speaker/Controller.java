package mny.speaker;

import java.util.Scanner;

public class Controller {
	Model model;
	View view;
	Scanner	scanner;
	SexType sexType;
	boolean humiliate = false;
	final boolean HUMILIATE = true;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		scanner = new Scanner(System.in);
	}

	public void processUser() {
		greetUser();				
		getPrintAndGetSexType();
		
		if (SexType.Submission == sexType) {
			view.printMessage(model.getMessageStartSubmission());
			getHumiliateOption();
		}
		
		view.printMessage("You select " + sexType);
	}

	private void getHumiliateOption() {		
		view.printMessage(model.getMessageNeedHumiliateOption());
		humiliate = getHumiliateOptionFromUser();
	}

	private boolean getHumiliateOptionFromUser() {
		String answer = getInput();
		while (answer.toLowerCase().equals("да") || 
				answer.toLowerCase().equals("нет")) {
			view.printMessage(model.getMessageNotCorrectInput());			
		}
		
		if (answer.toLowerCase().equals("да")) {
			humiliate = HUMILIATE;
		}
		
		return false;
	}

	public void getPrintAndGetSexType() {
		view.printMessage(model.getGameVariants());
		sexType = getSexType();
	}

	public void greetUser() {
		view.printMessage(model.getGreetings());
		getInput();
	}

	private SexType getSexType() {
		int input = Integer.parseInt(getInput());
		
		while ((input < SexType.Submission.ordinal()) ||
				(input > SexType.VirtualSex.ordinal()))
		{
			view.printMessage(model.getMessageNotCorrectInput());
			view.printMessage(model.getGameVariants());
			input = Integer.parseInt(getInput());		
		}
		
		if (0 == input) {
			return SexType.Submission;
		} else if (1 == input) {
			return SexType.Dominance;
		} else if (2 == input) {
			return SexType.Sissy;
		}

		return SexType.VirtualSex;
	}

	public String getInput() {
		return scanner.nextLine();
	}

}
