package com.hp.hpl.logkv.logsource;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.util.Util;

public class LogSourceRunnerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Get the Schema for event
		 */
		Schema schema = Parameter.schema;
		/**
		 * Start LogSourceRunner
		 */
		LogSourceRunner logSourceRunner = new LogSourceRunner(schema);
		logSourceRunner.start();
		Util.log("LogSourceRunner started.", LogSourceRunnerMain.class);

	}

}
