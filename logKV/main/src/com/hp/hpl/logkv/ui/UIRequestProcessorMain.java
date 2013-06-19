package com.hp.hpl.logkv.ui;

import com.hp.hpl.logkv.util.Util;

public class UIRequestProcessorMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Start UIRequestProcessor
		 */
		UIRequestProcessor requestProcessor = new UIRequestProcessor();
		requestProcessor.start();
		Util.log("RequestProcessor started.", UIRequestProcessorMain.class);

	}

}
