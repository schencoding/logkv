package com.hp.hpl.logkv.demo.applet.workernodes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

import com.hp.hpl.logkv.demo.applet.DateUtil;
import com.hp.hpl.logkv.demo.applet.StringUtil;
import com.hp.hpl.logkv.demo.applet.URLRequester;
import com.hp.hpl.logkv.demo.chart.ResourceChartPanel;
import com.hp.hpl.logkv.demo.util.NumberUtil;
import com.hp.hpl.logkv.ui.ResourceStatusRequest;
import com.hp.hpl.logkv.ui.ResourceStatusResponse;
import com.hp.hpl.logkv.ui.ResourceStatusResponse.MemoryStatus;
import com.hp.hpl.logkv.ui.ResourceStatusResponse.NetworkStatus;

/**
 * Worker nodes applet.
 * @author Edmond
 */
public class WorkerNodesApplet extends JApplet {

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

	/**
	 * resource status response cache list.
	 */
	private LinkedList<ResourceStatusResponse> resourceStatusResponseCacheList = new LinkedList<ResourceStatusResponse>();

	@Override
	public void init() {
		String serverIP = this.getParameter("serverIP");
		String serverPort = this.getParameter("serverPort");
		try {
			overviewServletURL = new URL("http://" + serverIP + ":" + serverPort + "/LogKVDemo/WorkerNodesServlet");
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
				Thread.sleep(50);
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
		 * all time series list.
		 */
		private List<TimeSeries> allTimeSeriesList = new ArrayList<TimeSeries>();

		/**
		 * statistics table model.
		 */
		private StatisticsTableModel statisticsTableModel;

		/**
		 * constructor method.
		 * @param delay
		 *            timer delay in millisecond
		 */
		public DataRefresher(int delay) {
			super(delay, null);
			addActionListener(this);
		}

		/**
		 * action performed for timer. random generate usage number
		 * @param e	action event
		 */
		public void actionPerformed(ActionEvent e) {

			try {

				ResourceStatusRequest requestObj = new ResourceStatusRequest(
						true, null);

				Object object = urlRequester.request(requestObj);

				if (object == null) {
					System.err.println("ResourceStatusResponse object is null");
					return;
				}

				ResourceStatusResponse responseObj = (ResourceStatusResponse) object;

				if (resourceStatusResponseCacheList.size() > 60) {
					resourceStatusResponseCacheList.remove(0);
				}

				resourceStatusResponseCacheList.add(responseObj);

				Iterator<String> ipStrIter = responseObj.getKeyIterator();

				if (!initResourcePanel) {

					List<TimeSeries> logSizeTimeSeriesList = new ArrayList<TimeSeries>();

					List<TimeSeries> cpuTimeSeriesList = new ArrayList<TimeSeries>();

					List<TimeSeries> memoryTimeSeriesList = new ArrayList<TimeSeries>();

					List<TimeSeries> networkTimeSeriesList = new ArrayList<TimeSeries>();

					List<TimeSeries> storageSizeTimeSeriesList = new ArrayList<TimeSeries>();

					WorkerNodesApplet.this.getContentPane().setLayout(new BorderLayout());

					JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

					final JComboBox cbWorkerNode = new JComboBox();

					cbWorkerNode.addItem("============= ALL =============");

					cbWorkerNode.setPreferredSize(new Dimension(240, 20));

					controlPanel.add(cbWorkerNode);

					JButton btnShowStatus = new JButton("Show status");

					btnShowStatus.setPreferredSize(new Dimension(120, 20));

					controlPanel.add(btnShowStatus);

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

			    	controlPanel.add(cbPanelChoice);
			    	controlPanel.add(btnChangeResourcePanelStatus);

					JPanel controlRowPanel = new JPanel(new BorderLayout());

					controlPanel.setBackground(Color.white);
					controlRowPanel.add(controlPanel, BorderLayout.CENTER);

					JPanel addWorkerNodePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

					JButton btnAddWorkerNode = new JButton("Add worker node");

					btnAddWorkerNode.setPreferredSize(new Dimension(180, 20));

					addWorkerNodePanel.add(btnAddWorkerNode);

					addWorkerNodePanel.setBackground(Color.white);
					controlRowPanel.add(addWorkerNodePanel, BorderLayout.EAST);

					WorkerNodesApplet.this.getContentPane().add(controlRowPanel, BorderLayout.NORTH);

					List<String> ipStrList = new ArrayList<String>();

					while (ipStrIter.hasNext()) {

						String convertIpStr = StringUtil.convertNodeName(ipStrIter.next());

						ipStrList.add(convertIpStr);
						logSizeTimeSeriesList.add(new TimeSeries(convertIpStr));
						cpuTimeSeriesList.add(new TimeSeries(convertIpStr));
						memoryTimeSeriesList.add(new TimeSeries(convertIpStr));
						networkTimeSeriesList.add(new TimeSeries(convertIpStr + " - In"));
						networkTimeSeriesList.add(new TimeSeries(convertIpStr + " - Out"));
						storageSizeTimeSeriesList.add(new TimeSeries(convertIpStr));
						allTimeSeriesList.add(new TimeSeries(convertIpStr));
					}

					Collections.sort(ipStrList);

					for (String convertIpStr : ipStrList) {
						cbWorkerNode.addItem(convertIpStr);
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

					btnShowStatus.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							int selectedIndex = cbWorkerNode.getSelectedIndex();
							String selectedItem = (String) cbWorkerNode.getSelectedItem();

							if (selectedIndex == 0) {

								List<TimeSeries> networkTimeSeriesList = new ArrayList<TimeSeries>();
								for (TimeSeries ts : allTimeSeriesList) {
									networkTimeSeriesList.add(new TimeSeries(ts.getKey().toString() + " - In"));
									networkTimeSeriesList.add(new TimeSeries(ts.getKey().toString() + " - Out"));
								}

								logSizeResourcePanel.refreshTimeSeriesList(allTimeSeriesList);
								cpuResourcePanel.refreshTimeSeriesList(allTimeSeriesList);
								memoryResourcePanel.refreshTimeSeriesList(allTimeSeriesList);
								networkResourcePanel.refreshTimeSeriesList(networkTimeSeriesList);
								storageSizeResourcePanel.refreshTimeSeriesList(allTimeSeriesList);
							} else {
								List<TimeSeries> singleTimeSeriesList = new ArrayList<TimeSeries>();
								singleTimeSeriesList.add(new TimeSeries(selectedItem));

								List<TimeSeries> singleNetworkTimeSeriesList = new ArrayList<TimeSeries>();
								singleNetworkTimeSeriesList.add(new TimeSeries(selectedItem + " - In"));
								singleNetworkTimeSeriesList.add(new TimeSeries(selectedItem + " - Out"));

								logSizeResourcePanel.refreshTimeSeriesList(singleTimeSeriesList);
								cpuResourcePanel.refreshTimeSeriesList(singleTimeSeriesList);
								memoryResourcePanel.refreshTimeSeriesList(singleTimeSeriesList);
								networkResourcePanel.refreshTimeSeriesList(singleNetworkTimeSeriesList);
								storageSizeResourcePanel.refreshTimeSeriesList(singleTimeSeriesList);
							}

							for (ResourceStatusResponse cacheObj : resourceStatusResponseCacheList) {
								showData(cacheObj);
							}

						}
					});

					final JPanel resourceListPanel = new JPanel(new GridLayout(5, 1));

					resourceListPanel.add(logSizeResourcePanel);
					resourceListPanel.add(cpuResourcePanel);
					resourceListPanel.add(memoryResourcePanel);
					resourceListPanel.add(networkResourcePanel);
					resourceListPanel.add(storageSizeResourcePanel);

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

							WorkerNodesApplet.this.getContentPane().validate();

						}
			    	});

				    JPanel grahpPanel = new JPanel(new BorderLayout());
				    grahpPanel.add(resourceListPanel, BorderLayout.CENTER);


					String[] columnNames = new String[] {"Time", "Node", "Number of log events",
							"CPU usage (%)", "Memory usage (%)", "Network In/Out (KB/s)", "Storage Size (MB)"};

					statisticsTableModel = new StatisticsTableModel(columnNames);

					JTable table = new JTable(statisticsTableModel);
					JScrollPane tableScrollPanle = new JScrollPane(table);

					table.setBackground(Color.white);
					table.getParent().setBackground(Color.white);
					tableScrollPanle.setBackground(Color.white);

					JTabbedPane tabbedPane = new JTabbedPane();
					tabbedPane.setBackground(Color.white);
					tabbedPane.add("Graph", grahpPanel);
					tabbedPane.add("Statistics", tableScrollPanle);

					WorkerNodesApplet.this.getContentPane().setBackground(Color.white);
					WorkerNodesApplet.this.getContentPane().add(tabbedPane, BorderLayout.CENTER);

					WorkerNodesApplet.this.getContentPane().validate();

					initResourcePanel = true;
				}

				showData(responseObj);
				statisticsTableModel.append(responseObj);

			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println(ex);
			}

		}

		/**
		 * show data.
		 * @param responseObj	resource status response.
		 */
		public synchronized void showData(ResourceStatusResponse responseObj) {

			Iterator<String> ipStrIter = responseObj.getKeyIterator();

			long timestamp = responseObj.getTimestamp();
			Millisecond time = new Millisecond(new Date(timestamp));

			while (ipStrIter.hasNext()) {

				String ipStr = ipStrIter.next();
				String convertIpStr = StringUtil.convertNodeName(ipStr);

				for (TimeSeries ts : logSizeResourcePanel
						.getTimeSeriesList()) {
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

				for (TimeSeries ts : memoryResourcePanel
						.getTimeSeriesList()) {
					if (ts.getKey().equals(convertIpStr)) {
						MemoryStatus memoryStatus = responseObj
								.getMemoryStatus(ipStr);
						if (memoryStatus != null) {
							ts.addOrUpdate(time,
									memoryStatus.getPercentage());
						}
						break;
					}
				}

				for (TimeSeries ts : networkResourcePanel
						.getTimeSeriesList()) {
					if (ts.getKey().equals(convertIpStr + " - In")) {
						NetworkStatus networkStatus = responseObj
								.getNetworkStatus(ipStr);
						if (networkStatus != null) {
							ts.addOrUpdate(time, networkStatus.getSizeIn() / 1024.0);
						}
					} else if (ts.getKey().equals(convertIpStr + " - Out")) {
						NetworkStatus networkStatus = responseObj
								.getNetworkStatus(ipStr);
						if (networkStatus != null) {
							ts.addOrUpdate(time, networkStatus.getSizeOut() / 1024.0);
						}
					}
				}

				for (TimeSeries ts : storageSizeResourcePanel
						.getTimeSeriesList()) {
					if (ts.getKey().equals(convertIpStr)) {
						Long storageCost = responseObj
								.getStorageCost(ipStr);
						if (storageCost != null) {
							ts.addOrUpdate(time, storageCost / (1024.0 * 1024.0));
						}
						break;
					}
				}

			}
		}

	}

	/**
	 * Statistics table model.
	 * @author Edmond
	 */
	public static class StatisticsTableModel extends DefaultTableModel {

		/**
		 * serial version UID.
		 */
		private static final long serialVersionUID = 1L;


		/**
		 * table row data.
		 */
		private LinkedList<TableRowData> tableRowData = new LinkedList<TableRowData>();

		/**
		 * column names.
		 */
		private String[] columnNames;

		/**
		 * constructor method.
		 * @param columnNames	column names
		 */
		public StatisticsTableModel(String[] columnNames) {
			this.columnNames = columnNames;
		}

		/**
		 * get column name.
		 * @param column	column index
		 * @return	column name
		 */
		public String getColumnName(int column) {
			return columnNames[column].toString();
		}

		/**
		 * get row count.
		 * @return	row count
		 */
        public int getRowCount() {

        	if (tableRowData == null) {
        		return 0;
        	} else {
        		return tableRowData.size();
        	}
        }

        /**
         * get column count.
         * @return column count
         */
        public int getColumnCount() {
        	return columnNames.length;
        }

        /**
         * get value at.
         * @param row	row index
         * @param col	column index
         * @return	cell value
         */
        public Object getValueAt(int row, int col) {

        	TableRowData rowData = tableRowData.get(row);

        	if (col == 0) {
        		return rowData.time;
        	} else if (col == 1) {
        		return rowData.node;
        	} else if (col == 2) {
        		return rowData.eventCount;
        	} else if (col == 3) {
        		return rowData.cpuUsage;
        	} else if (col == 4) {
        		return rowData.memoryUsage;
        	} else if (col == 5) {
        		return rowData.networkTraffic;
        	} else if (col == 6) {
        		return rowData.storageSize;
        	}

        	return "";
        }

        /**
         * is cell editable.
         * @param row		row index
         * @param column	column index
         * @return	editable status
         */
        public boolean isCellEditable(int row, int column) {
        	return false;
        }

        /**
         * append response object to table.
         * @param responseObj	response object
         */
        public void append(ResourceStatusResponse responseObj) {

        	if (responseObj == null) {
        		return;
        	}

        	String time = DateUtil.getSimpleDateTimeString(new Date(responseObj.getTimestamp()));

        	Iterator<String> ipStrIter = responseObj.getKeyIterator();

        	while (ipStrIter.hasNext()) {
        		String node = ipStrIter.next();
        		String convertIpStr = StringUtil.convertNodeName(node);
        		long eventCount = responseObj.getLogSize(node);
        		double cpuUsage = responseObj.getCPUStatus(node);
        		double memoryUsage = responseObj.getMemoryStatus(node).getPercentage();
        		double networkTrafficIn = responseObj.getNetworkStatus(node).getSizeIn() / 1024.0;
        		double networkTrafficOut = responseObj.getNetworkStatus(node).getSizeOut() / 1024.0;
        		double storageSize = responseObj.getStorageCost(node) / (1024.0 * 1024.0);

        		TableRowData trd = new TableRowData();
        		trd.time = time;
        		trd.node = convertIpStr;
        		trd.eventCount = "" + eventCount;
        		trd.cpuUsage = NumberUtil.getNumberFormatDigitWithTwoFractionDigits(cpuUsage);
        		trd.memoryUsage = NumberUtil.getNumberFormatDigitWithTwoFractionDigits(memoryUsage);
        		trd.networkTraffic =
        				NumberUtil.getNumberFormatDigitWithTwoFractionDigits(networkTrafficIn) + "/"
        					+ NumberUtil.getNumberFormatDigitWithTwoFractionDigits(networkTrafficOut);
        		trd.storageSize = NumberUtil.getNumberFormatDigitWithTwoFractionDigits(storageSize);

        		tableRowData.addFirst(trd);
        	}

        	while (tableRowData.size() > 100) {
        		tableRowData.removeLast();
        	}

        	this.fireTableDataChanged();
        }
	}

	/**
	 * Table row data entity.
	 * @author Edmond
	 */
	public static class TableRowData {

		/**
		 * time stamp.
		 */
		public String time;

		/**
		 * node name.
		 */
		public String node;

		/**
		 * event count.
		 */
		public String eventCount;

		/**
		 * CPU usage.
		 */
		public String cpuUsage;

		/**
		 * memory usage.
		 */
		public String memoryUsage;

		/**
		 * network traffic.
		 */
		public String networkTraffic;

		/**
		 * storage size.
		 */
		public String storageSize;
	}

}
