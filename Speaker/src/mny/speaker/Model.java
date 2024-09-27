package mny.speaker;

enum SexType {
	Submission, Dominance, Sissy, VirtualSex
}

public class Model {

	public String getGreetings() {
		return "привет! как твое настроение сегодня?";
	}

	public String getGameVariants() {
		return "в какую игру будем играть сегодня?\n" +
				SexType.Submission.ordinal() + " подчинение\n" +
				SexType.Dominance.ordinal() + " доминирование\n" +
				SexType.Sissy.ordinal() + " метроном\n" +
				SexType.VirtualSex.ordinal() + " вирт";
	}

	public String getMessageNeedTenderness() {
		return "Хочешь немного нежности? (y/n)";
	}

	public String getMessageNotCorrectInput() {
		return "Неправильный ввод!";
	}

	public String getMessageNeedHumiliateOption() {
		return "Хочешь сегодня унижений?";
	}

	public String getMessageStartSubmission() {
		return "Мальчик хочет служить мне. Интересно :-)\n" +
				"Тогда начнем ;-)";
	}

}
