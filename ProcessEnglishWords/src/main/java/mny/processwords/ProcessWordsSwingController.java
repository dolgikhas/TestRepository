package mny.processwords;

import java.util.logging.Logger;

public class ProcessWordsSwingController {
	private ProcessWordsModel model;
	private ProcessWordsFrame view;
	private static Logger logger;

	public ProcessWordsSwingController(ProcessWordsModel model, ProcessWordsFrame view,
			Logger logger) {
		this.model = model;
		this.view = view;
		this.logger = logger;
	}

	public void initSwingController() {
		view.initView();
		logger.info("view.initView() complete");
		
		view.createWordControls();
		
		view.completeInitView();
		logger.info("view.completeInitView()");
	}

}
