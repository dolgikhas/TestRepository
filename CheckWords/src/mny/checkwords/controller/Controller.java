package mny.checkwords.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javazoom.jl.decoder.JavaLayerException;
import mny.checkwords.view.*;
import mny.checkwords.model.*;

public class Controller {
	private Model model;
	private View view;
	private Logger logger = Logger.getLogger("Controller");;
	private boolean showWord = true;
	private boolean showTranslation = false;
	private Word word;
	ArrayList<String> listExamples;
	private SoundExamples soundExamples;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void processUser() {
		view.setModel(model);
		view.initView();
		view.createControls();
		setListenersToControls();
		view.completeInitView();
	}

	private void setListenersToControls() {
		setListenerToButtonStartCheckWords();
		setListenerToButtonStopCheckWords();
		setListenerToButtonShowTranslation();
		setListenerToButtonShowWord();
		setListenerToButtonStopCheckWordsAndSaveResult();
		setListenerToButtonPlus();
		setListenerToButtonMinus();
		setListenerToButtonAsterisk();
		setListenerToButtonMinus4();
		setListenerToButtonPutWordToDoubtFile();
		setListenerToButtonPreviousWord();
		setListenerToButtonRepeatPlayExample();
		setListenerToButtonPlayNextExample();
	}

	private void setListenerToButtonStopCheckWordsAndSaveResult() {
		view.getButtonStopCheckWordsAndSaveResult().addActionListener(event -> {
			try {
				model.saveCheckWordResultsWithWordsWithoutResults(view.getFileWordsToCheck());
				
				JOptionPane.showMessageDialog(null, "Слова успешно сохранены!",
						null, JOptionPane.INFORMATION_MESSAGE);
				
				view.disableButtonsForCheckWords();
				view.disableControlsResults();
				view.enableButtonAndListForStartCheckWords();				
			} catch (Exception exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			}
		});
	}

	private void setListenerToButtonPutWordToDoubtFile() {
		view.getButtonPutWordToDoubtFile().addActionListener(event -> {
			//logger.info("\tbuttonPutWordToDoubtFile was pressed");
			word.setResultCheck(ResultCheck.Doubt);
			//logger.info("\tset " + ResultCheck.Doubt + " for: " + word.getWord());			
			getAndDisplayNextWordOrMessageEndCheckWords();
			
		});
	}

	private void setListenerToButtonPlayNextExample() {
		view.getButtonNextExample().addActionListener(event -> {
			try {
				soundExamples.playNextExample();
			} catch (FileNotFoundException exception ) {
				JOptionPane.showMessageDialog(null, "Озвучка примера не найдена",
						null, JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			} catch (JavaLayerException exception) {
				JOptionPane.showMessageDialog(null, "Ошибка при воспроизведении озвучки примера",
						null, JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}			
			
			if (!soundExamples.isNextExample()) {
				view.disableButtonPlayNextExample();
			}
		});
	}

	private void setListenerToButtonRepeatPlayExample() {
		view.getButtonRepeatExample().addActionListener(event -> {
			try {
				soundExamples.playCurrentExample();
			} catch (FileNotFoundException exception ) {
				JOptionPane.showMessageDialog(null, "Озвучка примера не найдена",
						null, JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			} catch (JavaLayerException exception) {
				JOptionPane.showMessageDialog(null, "Ошибка при воспроизведении озвучки примера",
						null, JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}			
		});
	}

	private void setListenerToButtonPreviousWord() {
		view.getButtonPreviousWord().addActionListener(event -> {
			//logger.info("\tbuttonPreviousWord was pressed");
			if (model.isPreviousWord()) {
				word = model.getPreviousWord();
				//logger.info("\t\tget previous word: " + word.getWord());
				showWordAndExamples();
				disableButttonPreviousWordIfNeed();
			}
		});
	}

	private void setListenerToButtonMinus4() {
		view.getButtonMinus4().addActionListener(event -> {
			word.setResultCheck(ResultCheck.Minus4);
			getAndDisplayNextWordOrMessageEndCheckWords();
			enableButtonPreviousWord();
		});
	}

	private void setListenerToButtonAsterisk() {
		view.getButtonAsterisk().addActionListener(event -> {
			word.setResultCheck(ResultCheck.Asterisk);
			getAndDisplayNextWordOrMessageEndCheckWords();
			enableButtonPreviousWord();
		});
	}

	private void setListenerToButtonMinus() {
		view.getButtonMinus().addActionListener(event -> {
			word.setResultCheck(ResultCheck.Minus);
			getAndDisplayNextWordOrMessageEndCheckWords();
		});
	}

	private void setListenerToButtonPlus() {
		view.getButtonPlus().addActionListener(event -> {
			//logger.info("\tbuttonPlus was pressed");
			word.setResultCheck(ResultCheck.Plus);
			//logger.info("\tset " + ResultCheck.Plus + " for: " + word.getWord());			
			getAndDisplayNextWordOrMessageEndCheckWords();
		});
	}

	private void getAndDisplayNextWordOrMessageEndCheckWords() {
		if (model.isNextWord()) {
			word = model.getNextWord();
			//logger.info("\tget next word: " + word.getWord());
			showWordAndExamples();
			
			if (!model.isFirstWord()) {
				view.enableButtonPreviousWord();
			}
			
			view.displayStatistics("" + (model.getCurrentWordIndex() + 1) + " из " + model.getNumberWords());

			return;
		}

		JOptionPane.showMessageDialog(null, "Проверка слов окончена!",
				null, JOptionPane.INFORMATION_MESSAGE);
		try {
			model.saveCheckWordResults(view.getFileWordsToCheck());
			
			// TODO: добавить повторную проверку по доп.списку

			view.disableButtonsForCheckWords();
			view.disableControlsResults();
			view.enableButtonAndListForStartCheckWords();				
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ошибка при сохранении результатов проверки",
					null, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ошибка при сохранении результатов проверки: " +
				e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}		
	}

	private void soundFirstExample() throws FileNotFoundException, JavaLayerException {
		soundExamples = new SoundExamples(word.getWord(), listExamples);
		soundExamples.playFirstExample();
		
		if (!soundExamples.isNextExample()) {
			//logger.info("word " + word.getWord() + " has only 1 example. so need to disable buttonPlayNextExample");
			view.disableButtonPlayNextExample();
		} else {
			view.enableButtonPlayNextExample();
		}
	}

	private void showExamples() throws FileNotFoundException, IOException {
		listExamples = model.getExamples(word);
		//logger.info("model.getExamples exit");
		String strExamples = getStringFromList(listExamples);
		//logger.info("get examples: " + strExamples);
		
		view.getTextAreaExamples().setText(strExamples);
	}

	private String getStringFromList(ArrayList<String> listExamples) {
		String strExamples = "";
		
		for (String example : listExamples) {
			strExamples += example + "\n";
		}
		return strExamples;
	}

	private void setListenerToButtonShowWord() {
		view.getButtonShowWord().addActionListener(event -> {
			showWordAndTranscription();
		});
	}

	private void setListenerToButtonShowTranslation() {
		view.getButtonShowTranslation().addActionListener(event -> {
			view.displayTranslation(word.getTranslation());
		});
	}

	private void setListenerToButtonStopCheckWords() {
		view.getButtonStopCheckWords().addActionListener(event -> {
			view.disableButtonsForCheckWords();
			view.disableControlsResults();
			view.enableButtonAndListForStartCheckWords();
		});
	}

	private void setListenerToButtonStartCheckWords() {
		view.getButtonCheckWords().addActionListener(event -> {
			try {
				manageControlsAndStartCheckWords();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Ошибка при открытии файла " + view.getFileWordsToCheck(),
					null, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				
				view.disableButtonsForCheckWords();
				view.disableControlsResults();
				view.enableButtonAndListForStartCheckWords();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Непонятная ошибка при обработке файла со словами",
						null, JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				
				view.disableButtonsForCheckWords();
				view.disableControlsResults();
				view.enableButtonAndListForStartCheckWords();
			}
		});
	}

	private void manageControlsAndStartCheckWords() throws FileNotFoundException, IOException {
		view.enableButtonsForCheckWords();
		view.disableButtonAndListForStartCheckWords();
		String fileWordsToCheck = view.getFileWordsToCheck();
		//logger.info("\tselected file for check: " + fileWordsToCheck);
		
		if (fileWordsToCheck.equals(Constants.FILE_DOUBT_WORDS)) {
			view.disableButtonPutWordToDoubtFile();
		}

		getOptionIsNeedShowWord();
		getOptionIsNeedShowTranslation();
		
		model.createWordsList();
		model.readListWords(fileWordsToCheck);
		
		getAndDisplayFirstWordToCheck();
		disableButttonPreviousWordIfNeed();
		
		view.displayStatistics("1 из " + model.getNumberWords());
	}

	private void getOptionIsNeedShowTranslation() {
		showTranslation = view.isOptionShowTranslationSelected();
	}

	private void disableButttonPreviousWordIfNeed() {
		if (model.isFirstWord()) {
			view.disableButtonPreviousWord();
		}
	}

	private void getOptionIsNeedShowWord() {
		showWord = view.isOptionShowWordSelected();
	}

	private void getAndDisplayFirstWordToCheck() {
		word = model.getFirstWord();
		//logger.info("\tget first word: " + word.getWord());
		
		showWordAndExamples();
	}

	private void showWordAndExamples() {
		getOptionIsNeedShowWord();
		view.displayWord("");
		view.displayTranscription("");
		if (showWord) {
			showWordAndTranscription();
		}
		
		getOptionIsNeedShowTranslation();
		if (showTranslation) {
			view.displayTranslation(word.getTranslation());
			view.disableButtonShowTranslation();
		} else {
			view.displayTranslation("");
			view.enableButtonShowTranslation();
		}
		
		try {
			try {
				showExamples();
			} catch (FileNotFoundException exception) {
				JOptionPane.showMessageDialog(null, "Не найден файл с примерами",
						null, JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			} 
			
			try {
				soundFirstExample();
			} catch (FileNotFoundException exception) {
				JOptionPane.showMessageDialog(null, "Пример не найден " + word.getWord() + "_" + listExamples.get(0) + ".mp3",
						null, JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			} catch (JavaLayerException exception) {
				JOptionPane.showMessageDialog(null, "Ошибка при воспроизведении примера " + word.getWord() + "_" + listExamples.get(0) + ".mp3",
						null, JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}
		} catch (IOException exception) {
			JOptionPane.showMessageDialog(null, "Непонятная ошибка при обработке примеров",
					null, JOptionPane.ERROR_MESSAGE);
			exception.printStackTrace();
		}
	}

	private void enableButtonPreviousWord() {
		if (!model.isFirstWord()) {
			view.getButtonPreviousWord().setEnabled(true);
			//logger.info("\tdisable previous word button");
		}
	}

	private void showWordAndTranscription() {
		view.displayWord(word.getWord());
		view.displayTranscription(word.getTranscription());
		//logger.info("\tshowed word and transcription for: " + word.getWord());
	}
}
