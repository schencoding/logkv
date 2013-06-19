package com.hp.hpl.logkv.demo.applet.overview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

import com.hp.hpl.logkv.demo.applet.StringUtil;
import com.hp.hpl.logkv.demo.applet.URLRequester;
import com.hp.hpl.logkv.demo.chart.ResourceChartPanel;
import com.hp.hpl.logkv.ui.ResourceStatusRequest;
import com.hp.hpl.logkv.ui.ResourceStatusResponse;
import com.hp.hpl.logkv.ui.ResourceStatusResponse.MemoryStatus;
import com.hp.hpl.logkv.ui.ResourceStatusResponse.NetworkStatus;

/**
 * Overview applet.
 * @author Edmond
 *
 */
public class OverviewApplet extends JApplet {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * initial resource panel.
	 */
	private boolean initResourcePanel = false;

	/**
	 * URL requester.
	 */
	private URLRequester urlRequester;

	/**
	 * server IP.
	 */
	private URL overviewServletURL;

	 @Override
	 public void init() {
		 String serverIP = this.getParameter("serverIP");
		 String serverPort = this.getParameter("serverPort");
		 try {
			 overviewServletURL = new URL("http://" + serverIP + ":" + serverPort + "/LogKVDemo/OverviewServlet");
		 } catch (Exception e) {
			 throw new RuntimeException(e);
		 }
		 urlRequester = new URLRequester(overviewServletURL);

		 // discard first performance response.
		 try {
			 ResourceStatusRequest requestObj = new ResourceStatusRequest(true, null);
			 urlRequester.request(requestObj);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

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

			 DataRefresher dataRefresher = new DataRefresher(2000);
			 dataRefresher.setInitialDelay(0);
			 dataRefresher.start();
		}

	 }

	 /**
	  * Data refresher.
	  * @author Edmond
	  */
	 private class DataRefresher extends Timer implements ActionListener {

		 /**
			 * serial version UID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * log size resource panel.
			 */
			private ResourceChartPanel logSizeResourcePanel;

			/**
			 * CPU resource panel.
			 */
			private ResourceChartPanel cpuResourcePanel;

			/**
			 * memory resource panel.
			 */
			private ResourceChartPanel memoryResourcePanel;

			/**
			 * network resource panel.
			 */
			private ResourceChartPanel networkResourcePanel;

			/**
			 * storage size resource panel.
			 */
			private ResourceChartPanel storageSizeResourcePanel;

			/**
			 * constructor method.
			 * @param delay			timer delay in millisecond
			 */
			public DataRefresher(int delay) {
		      super(delay, null);
		      addActionListener(this);
		    }

			/**
			 * action performed for timer.
			 * random generate usage number
			 * @param e	action event
			 */
		    public void actionPerformed(ActionEvent e) {

		    	try {

			    	ResourceStatusRequest requestObj = new ResourceStatusRequest(true, null);

			    	Object object = urlRequester.request(requestObj);

			    	if (object == null) {
			    		System.err.println("ResourceStatusResponse object is null");
			    		return;
			    	}

			    	ResourceStatusResponse responseObj = (ResourceStatusResponse) object;

			    	Iterator<String> ipStrIter = responseObj.getKeyIterator();

			    	if (!initResourcePanel) {

			    		 List<TimeSeries> logSizeTimeSeriesList = new ArrayList<TimeSeries>();

			    		 List<TimeSeries> cpuTimeSeriesList = new ArrayList<TimeSeries>();

			    		 List<TimeSeries> memoryTimeSeriesList = new ArrayList<TimeSeries>();

			    		 List<TimeSeries> networkTimeSeriesList = new ArrayList<TimeSeries>();

			    		 List<TimeSeries> storageSizeTimeSeriesList = new ArrayList<TimeSeries>();

				    	while (ipStrIter.hasNext()) {

				    		String convertIpStr = StringUtil.convertNodeName(ipStrIter.next());

				    		logSizeTimeSeriesList.add(new TimeSeries(convertIpStr));
				    		cpuTimeSeriesList.add(new TimeSeries(convertIpStr));
				    		memoryTimeSeriesList.add(new TimeSeries(convertIpStr));
				    		networkTimeSeriesList.add(new TimeSeries(convertIpStr + " - In"));
				    		networkTimeSeriesList.add(new TimeSeries(convertIpStr + " - Out"));
				    		storageSizeTimeSeriesList.add(new TimeSeries(convertIpStr));
				    	}

				    	logSizeResourcePanel = new ResourceChartPanel(
				    			"Number of log events", "Time", "Size", 60000, logSizeTimeSeriesList, false, 0, 0);
				    	cpuResourcePanel = new ResourceChartPanel(
				    			"CPU usage (%)", "Time", "Usage", 60000, cpuTimeSeriesList, true, 0, 100);
				    	memoryResourcePanel = new ResourceChartPanel(
				    			"Memory usage (%)", "Time", "Usage", 60000, memoryTimeSeriesList, true, 0, 100);
				    	networkResourcePanel = new ResourceChartPanel(
				    			"Network traffic (KB/s)", "Time", "Size", 60000, networkTimeSeriesList, false, 0, 0);
				    	storageSizeResourcePanel = new ResourceChartPanel(
				    			"Storage Size (MB)", "Time", "Size", 60000, storageSizeTimeSeriesList, false, 0, 0);

				    	final JPanel resourceListPanel = new JPanel(new GridLayout(5, 1));

				    	resourceListPanel.add(logSizeResourcePanel);
				    	resourceListPanel.add(cpuResourcePanel);
				    	resourceListPanel.add(memoryResourcePanel);
				    	resourceListPanel.add(networkResourcePanel);
				    	resourceListPanel.add(storageSizeResourcePanel);

				    	resourceListPanel.setBackground(Color.white);

				    	OverviewApplet.this.getContentPane().setLayout(new BorderLayout());
				    	OverviewApplet.this.getContentPane().add(resourceListPanel, BorderLayout.CENTER);

				    	JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

				    	final JComboBox cbPanelChoice = new JComboBox();
				    	cbPanelChoice.addItem("============= ALL =============");
				    	cbPanelChoice.addItem("Number of log events");
				    	cbPanelChoice.addItem("CPU usage (%)");
				    	cbPanelChoice.addItem("Memory usage (%)");
				    	cbPanelChoice.addItem("Network traffic (KB/s)");
				    	cbPanelChoice.addItem("Storage Size (MB)");

				    	cbPanelChoice.setPreferredSize(new Dimension(240, 20));

				    	JButton btnChangeResourcePanelStatus = new JButton("Change View Panel");
				    	btnChangeResourcePanelStatus.setPreferredSize(new Dimension(180, 20));

				    	btnChangeResourcePanelStatus.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								if (cbPanelChoice.getSelectedIndex() == 0) {

									resourceListPanel.removeAll();
									resourceListPanel.setLayout(new GridLayout(5, 1));
							    	resourceListPanel.add(logSizeResourcePanel);
							    	resourceListPanel.add(cpuResourcePanel);
							    	resourceListPanel.add(memoryResourcePanel);
							    	resourceListPanel.add(networkResourcePanel);
							    	resourceListPanel.add(storageSizeResourcePanel);
								} else if (cbPanelChoice.getSelectedIndex() == 1) {
									resourceListPanel.removeAll();
									resourceListPanel.setLayout(new BorderLayout());
							    	resourceListPanel.add(logSizeResourcePanel, BorderLayout.CENTER);
								} else if (cbPanelChoice.getSelectedIndex() == 2) {
									resourceListPanel.removeAll();
									resourceListPanel.setLayout(new BorderLayout());
							    	resourceListPanel.add(cpuResourcePanel, BorderLayout.CENTER);
								} else if (cbPanelChoice.getSelectedIndex() == 3) {
									resourceListPanel.removeAll();
									resourceListPanel.setLayout(new BorderLayout());
							    	resourceListPanel.add(memoryResourcePanel, BorderLayout.CENTER);
								} else if (cbPanelChoice.getSelectedIndex() == 4) {
									resourceListPanel.removeAll();
									resourceListPanel.setLayout(new BorderLayout());
							    	resourceListPanel.add(networkResourcePanel, BorderLayout.CENTER);
								} else if (cbPanelChoice.getSelectedIndex() == 5) {
									resourceListPanel.removeAll();
									resourceListPanel.setLayout(new BorderLayout());
							    	resourceListPanel.add(storageSizeResourcePanel, BorderLayout.CENTER);
								}

								OverviewApplet.this.getContentPane().validate();

							}
				    	});

				    	controlPanel.add(cbPanelChoice);
				    	controlPanel.add(btnChangeResourcePanelStatus);

				    	controlPanel.setBackground(Color.white);

				    	OverviewApplet.this.getContentPane().add(controlPanel, BorderLayout.NORTH);

						OverviewApplet.this.getContentPane().validate();

						initResourcePanel = true;

			    	}

			    	ipStrIter = responseObj.getKeyIterator();

			    	long timestamp = responseObj.getTimestamp();
			    	Millisecond time = new Millisecond(new Date(timestamp));

			    	while (ipStrIter.hasNext()) {

			    		String ipStr = ipStrIter.next();

			    		String convertIpStr = StringUtil.convertNodeName(ipStr);

						for (TimeSeries ts : logSizeResourcePanel.getTimeSeriesList()) {
							if (ts.getKey().equals(convertIpStr)) {
								Long logSize = responseObj.getLogSize(ipStr);
								if (logSize != null) {
									ts.addOrUpdate(time, logSize);
								}
								break;
							}
						}

						for (TimeSeries ts : cpuResourcePanel.getTimeSeriesList()) {
							if (ts.getKey().equals(convertIpStr)) {
								Double cpuStatus = responseObj.getCPUStatus(ipStr);
								if (cpuStatus != null) {
									ts.addOrUpdate(time, cpuStatus);
								}
								break;
							}
						}

						for (TimeSeries ts : memoryResourcePanel.getTimeSeriesList()) {
							if (ts.getKey().equals(convertIpStr)) {
								MemoryStatus memoryStatus = responseObj.getMemoryStatus(ipStr);
								if (memoryStatus != null) {
									ts.addOrUpdate(time, memoryStatus.getPercentage());
								}
								break;
							}
						}

						for (TimeSeries ts : networkResourcePanel.getTimeSeriesList()) {
							if (ts.getKey().equals(convertIpStr + " - In")) {
								NetworkStatus networkStatus = responseObj.getNetworkStatus(ipStr);
								if (networkStatus != null) {
									ts.addOrUpdate(time, networkStatus.getSizeIn() / 1024.0);
								}
							} else if (ts.getKey().equals(convertIpStr + " - Out")) {
								NetworkStatus networkStatus = responseObj.getNetworkStatus(ipStr);
								if (networkStatus != null) {
									ts.addOrUpdate(time, networkStatus.getSizeOut() / 1024.0);
								}
							}
						}

						for (TimeSeries ts : storageSizeResourcePanel.getTimeSeriesList()) {
							if (ts.getKey().equals(convertIpStr)) {
								Long storageCost = responseObj.getStorageCost(ipStr);
								if (storageCost != null) {
									ts.addOrUpdate(time, storageCost / (1024.0 * 1024.0));
								}
								break;
							}
						}

			    	}

		    	} catch (Exception ex) {
		    		ex.printStackTrace();
		    	}

			}

	 }

}
