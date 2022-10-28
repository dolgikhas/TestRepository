package mny.processwords;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

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
				view.setTranslation(model.getTranslation(word));
				view.setStatistics("Загружены данные для слова " + word);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		view.completeInitView();
		logger.info("ProcessWordsSwingController.initSwingController() <<");
	}

}
