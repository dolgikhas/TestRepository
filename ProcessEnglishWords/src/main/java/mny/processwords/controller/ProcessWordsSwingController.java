package mny.processwords.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import mny.processwords.model.ProcessWordsModel;
import mny.processwords.view.ProcessElementsDlg;
import mny.processwords.view.ProcessWordsFrame;
import mny.processwords.worddata.Word;

public class ProcessWordsSwingController {
	private ProcessWordsModel model;
	private ProcessWordsFrame view;
	private Logger logger;

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
		
		model.loadWordsData();
		logger.info("model.loadWordsData()");

		view.getWordTextEditor().addActionListener(event -> {
			logger.info("Enter word");
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
			}
		});
		
		view.getBtnLoadWordsData().addActionListener(event -> {
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
			}
		});
		
		view.getBtnAddWord().addActionListener(event -> {
			try {
				model.addNewWord(new Word.WordBuilder()
										 .setWord(view.getWord())
										 .setTranscription(view.getTranscription())
										 .setTranslation(view.getTranslation())
										 .setRepeat("0")
										 .build());
				logger.info("add new word data for word " + view.getWord());
				view.setStatistics("Слово " + view.getWord() + " добавлено ;-)");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		view.getBtnLoadExamples().addActionListener(event -> {
			try {
				ArrayList<String> listExamples = model.getExamples(view.getWord());
				listExamples = model.processIsWordsKnown(listExamples, view.getWord());
				ProcessElementsDlg processElementsDlg = new ProcessElementsDlg(view, listExamples);
				processElementsDlg.getBtnOK().addActionListener(e -> {
					try {
						model.outputExamplesToFile(view.getWord(), processElementsDlg.getTextArea().getText());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					processElementsDlg.setVisible(false);
				});
				processElementsDlg.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("get examples by ProcessElementsDlg dialog");			
			view.setStatistics("Примеры для Слова " + view.getWord() + " добавлены!");
		});
		
		view.completeInitView();
		logger.info("ProcessWordsSwingController.initSwingController() <<");
	}

}
