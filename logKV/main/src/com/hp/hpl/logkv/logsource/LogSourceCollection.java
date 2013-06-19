package com.hp.hpl.logkv.logsource;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public class LogSourceCollection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1254866024923608966L;
	private LogSourceDescriptor[] logSources = null;
	
	public LogSourceCollection(Collection<LogSourceDescriptor> logSourceList){
		this.logSources = new LogSourceDescriptor[logSourceList.size()];
		logSourceList.toArray(logSources);		
	}
	
	

	public LogSourceDescriptor[] getLogSources() {
		return logSources;
	}



	@Override
	public String toString() {
		return "LogSourceCollection [logSources=" + Arrays.toString(logSources) + "]";
	}
	


}
