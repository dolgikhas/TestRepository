package mny.processwords.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import mny.processwords.CreateListWordsFromExamples;
import mny.processwords.model.Paths;
import mny.processwords.model.ProcessWordsModel;
import mny.processwords.view.ProcessElementsDlg;
import mny.processwords.view.ProcessWordsFrame;
import mny.processwords.worddata.Word;

public class ProcessWordsSwingController {
	private ProcessWordsModel model;
	private ProcessWordsFrame view;
	private Logger logger;
	private Paths pathValues;

	public ProcessWordsSwingController(ProcessWordsModel model, ProcessWordsFrame view,
			Logger logger) {
		this.model = model;
		this.view = view;
		this.logger = logger;
	}

	public void initSwingController() throws FileNotFoundException, IOException {
		logger.info("ProcessWordsSwingController.initSwingController() >>");
		view.initView();
		logger.info("view.initView()");
		
		view.createWordControls();
		logger.info("view.createWordControls()");
		
		CreateListWordsFromExamples.createListWordsFromExamples(pathValues.getExamples());
		logger.info("CreateListWordsFromExamples.createListWordsFromExamples()");
		
		model.setPathValues(pathValues);
		model.loadWordsData();
		logger.info("model.loadWordsData()");

		view.getWordTextEditor().addActionListener(event -> {
			logger.info("Enter word");
			view.setWord(view.getWord().trim());
			logger.info("Trim word");
			if (model.isWordExists(view.getWord())) {
				Word word = model.getWord(view.getWord());
				view.setTranscription(word.getTranscription());
				view.setTranslation(word.getTranslation());
				view.setStatistics("...слово найдено...");
				logger.info("Get word data and fill text fields");
			} else {
				view.setTranscription("");
				view.setTranslation("");
				view.setStatistics("Слово не найдено");
				logger.info("Word data not found");
				
				int selection = JOptionPane.showConfirmDialog(view, "Данные по слову " + view.getWord() + " не найдены. " +
						"Загрузить данные по слову?", "Слово не найдено",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (selection == JOptionPane.OK_OPTION) {
					loadWordData();
					
					selection = JOptionPane.showConfirmDialog(view, "Добавить новое слово в файлы со словами?",
								"Новое слово", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (selection == JOptionPane.OK_OPTION) {
						addNewWordToFiles();
					}
				}
				

			}
		});
		
		view.getBtnLoadWordsData().addActionListener(event -> {
			loadWordData();
		});
		
		view.getBtnAddWord().addActionListener(event -> {
			addNewWordToFiles();
		});
		
		view.getBtnLoadExamples().addActionListener(event -> {
			try {
				boolean loadExamples = true;
				
				if (checkIsExampleExists(view.getWord())) {
					loadExamples = false;
					
					int selection = JOptionPane.showConfirmDialog(view,
							"Примеры для данного слова существуют. Нужно искать новые примеры?",
							"Поиск примеров для слова", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (JOptionPane.OK_OPTION == selection)
						loadExamples = true;
				}
					
				if (loadExamples) {
					ArrayList<String> listExamples = model.getExamples(view.getWord());
					listExamples = model.processIsWordsKnown(listExamples, view.getWord());
					ProcessElementsDlg processElementsDlg = new ProcessElementsDlg(view, listExamples);
					processElementsDlg.getBtnOK().addActionListener(e -> {
						try {
							model.outputExamplesToFile(view.getWord(), processElementsDlg.getTextArea().getText());
						} catch (IOException e1) {
							view.setStatistics("ОШИБКА при добавлении примеров для слова " + view.getWord());
							e1.printStackTrace();
						}
						processElementsDlg.setVisible(false);
					});
					processElementsDlg.setVisible(true);
					logger.info("get examples by ProcessElementsDlg dialog");			
					view.setStatistics("Примеры для слова " + view.getWord() + " добавлены!");
				} else {
					view.setStatistics("Примеры для слова " + view.getWord() + " уже существуют.");
				}
			} catch (IOException e) {
				e.printStackTrace();
				view.setStatistics("ОШИБКА при получении примеров для слова " + view.getWord());
			}
		});
		
		view.completeInitView();
		logger.info("ProcessWordsSwingController.initSwingController() <<");
	}

	public void addNewWordToFiles() {
		try {
			model.addNewWord(new Word.WordBuilder()
									 .setWord(view.getWord())
									 .setTranscription(view.getTranscription())
									 .setTranslation(view.getTranslation())
									 .setRepeat("0")
									 .build(),
							 pathValues.getCheckWords());
			logger.info("add new word data for word " + view.getWord());
			view.setStatistics("Слово " + view.getWord() + " добавлено ;-)");
		} catch (IOException e) {
			e.printStackTrace();
			view.setStatistics("ОШИБКА при добавлении нового слова " + view.getWord());
		}
	}

	public void loadWordData() {
		try {
			String word = view.getWord();
			view.setTranscription(model.getTranscription(word));
			logger.info("view.setTranscription(model.getTranscription(word))");
			
			ProcessElementsDlg processTranslations = new ProcessElementsDlg(view, model.getTranslation(word));
			processTranslations.getBtnOK().addActionListener(e -> {
				view.setTranslation(processTranslations.getTextArea().getText());
				processTranslations.setVisible(false);
			});
			processTranslations.setVisible(true);
			logger.info("get translation by ProcessElementsDlg dialog");
			
			view.setStatistics("Загружены данные для слова " + word);
		} catch (IOException e) {
			e.printStackTrace();
			view.setStatistics("ОШИБКА при загрузке данных слова " + view.getWord());
		}
	}

	public void setPathValues(Paths ipathValues) {
		this.pathValues = ipathValues;
	}
	
	private boolean checkIsExampleExists(String word) {
		String exampleWordPath = pathValues.getExamples() + word + ".txt"; 
		File file = new File(exampleWordPath);
		if (file.exists()) {
			return true;
		}

		return false;
	}

}
