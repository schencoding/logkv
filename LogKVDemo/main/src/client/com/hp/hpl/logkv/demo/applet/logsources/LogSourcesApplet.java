package com.hp.hpl.logkv.demo.applet.logsources;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JApplet;

import com.hp.hpl.logkv.demo.applet.StringUtil;
import com.hp.hpl.logkv.demo.applet.URLRequester;
import com.hp.hpl.logkv.demo.model.LogKVEdge;
import com.hp.hpl.logkv.demo.model.LogSource;
import com.hp.hpl.logkv.demo.model.WorkerNode;
import com.hp.hpl.logkv.demo.model.WorkerNodeLogSourceEdge;
import com.hp.hpl.logkv.logsource.LogSourceCollection;
import com.hp.hpl.logkv.logsource.LogSourceDescriptor;
import com.hp.hpl.logkv.ui.AddLogSourceResponse;
import com.hp.hpl.logkv.ui.GetLogSourcesRequest;

/**
 * Log sources applet.
 * @author Edmond
 *
 */
public class LogSourcesApplet extends JApplet {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * log source panel.
	 */
	LogSourcePanel logSourcePanel;

	/**
	 * server IP.
	 */
	private URL logSourcesServletURL;

	/**
	 * URL request.
	 */
	private URLRequester urlRequester;

	 @Override
	 public void init() {

		 String serverIP = this.getParameter("serverIP");
		 String serverPort = this.getParameter("serverPort");

		 try {
			 logSourcesServletURL = new URL("http://" + serverIP + ":" + serverPort + "/LogKVDemo/LogSourcesServlet");
		 } catch (Exception e) {
			 throw new RuntimeException(e);
		 }

		 urlRequester = new URLRequester(logSourcesServletURL);

		 new ThreadStarter().start();
	 }

	 @Override
	 public void start() {
		 validate();
	 }

	 /**
	  * Threader starter.
	  * @author Edmond
	  */
	 public class ThreadStarter extends Thread {

		@Override
		public void run() {

			try {
				Thread.sleep(1000);
			} catch (Exception ignore) {
				ignore.printStackTrace();
			}

			logSourcePanel = new LogSourcePanel(LogSourcesApplet.this);

			 GetLogSourcesRequest requestObj = new GetLogSourcesRequest();

			 Object object = urlRequester.request(requestObj);

	    	if (object == null) {
	    		System.err.println("object is null");
	    		return;
	    	}

	    	AddLogSourceResponse responseObj = (AddLogSourceResponse) object;

	    	logSourcePanel.setSize(LogSourcesApplet.this.getSize());

	    	refreshGraph(responseObj);

	    	LogSourcesApplet.this.getContentPane().add(logSourcePanel, BorderLayout.CENTER);
	    	LogSourcesApplet.this.getContentPane().validate();
		}

	 }

	 /**
	  * refresh graph.
	  * @param responseObj	response object
	  */
	 public void refreshGraph(AddLogSourceResponse responseObj) {

		 String[] ipStrArray = responseObj.getNodes();

	    	LogSourceCollection logSourceCollection = responseObj.getLogSources();

	    	List<WorkerNode> workerNodeList = new ArrayList<WorkerNode>();

	    	Map<Integer, WorkerNode> workerNodeMapping = new HashMap<Integer, WorkerNode>();

	    	for (int i = 0; i < ipStrArray.length; i++) {
	    		String convertIpStr = StringUtil.convertNodeName(ipStrArray[i]);
				WorkerNode wn = new WorkerNode(convertIpStr);
				workerNodeList.add(wn);
				workerNodeMapping.put(i, wn);
			}

			List<LogSource> logSourceList = new ArrayList<LogSource>();

			List<LogKVEdge> edgeList = new ArrayList<LogKVEdge>();

			for (LogSourceDescriptor logSourceDescriptor : logSourceCollection.getLogSources()) {

				LogSource logSource = new LogSource(logSourceDescriptor);

			 	logSourceList.add(logSource);

			 	for (Integer ipStrIndex : logSourceDescriptor.getMapping().keySet()) {
			 		edgeList.add(new WorkerNodeLogSourceEdge(
			 				workerNodeMapping.get(ipStrIndex), logSource,
			 				100 * logSourceDescriptor.getMapping().get(ipStrIndex)));
			 	}
			}


			logSourcePanel.insertResourceToGraph(workerNodeList, logSourceList, edgeList);
	 }

	/**
	 * @return the urlRequester
	 */
	public URLRequester getUrlRequester() {
		return urlRequester;
	}

}
