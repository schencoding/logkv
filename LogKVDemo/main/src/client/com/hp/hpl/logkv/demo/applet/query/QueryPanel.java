package com.hp.hpl.logkv.demo.applet.query;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.hp.hpl.logkv.demo.applet.StringUtil;
import com.hp.hpl.logkv.demo.applet.URLRequester;
import com.hp.hpl.logkv.demo.applet.modelwindow.JModalWindow;
import com.hp.hpl.logkv.demo.topology.IconResourceMap;
import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics.IngestKVProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics.TRUProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;
import com.hp.hpl.logkv.ui.QueryRequest;
import com.hp.hpl.logkv.ui.QueryResponse;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

/**
 * Query .
 * @author Edmond
 */
public class QueryPanel extends JPanel {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * URL requester.
	 */
	private URLRequester urlRequester;

	/**
	 * constructor method.
	 * @param parentFrame			parent frame
	 * @param urlRequester			URL requester
	 */
	public QueryPanel(final Frame parentFrame, URLRequester urlRequester) {

		this.urlRequester = urlRequester;

		final JTextArea taSearchQuery = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(taSearchQuery);
		scrollPane.setPreferredSize(new Dimension(2400, 80));

		Calendar current = new GregorianCalendar();

		final JDateChooser startDateChoice = new JDateChooser(current.getTime());
		startDateChoice.setPreferredSize(new Dimension(100, 20));

		final JSpinField startHour = new JSpinField();
		startHour.setMinimum(0);
		startHour.setMaximum(23);
		startHour.setPreferredSize(new Dimension(40, 20));
		startHour.setValue(current.get(Calendar.HOUR_OF_DAY));

		final JSpinField startMinius = new JSpinField();
		startMinius.setMinimum(0);
		startMinius.setMaximum(59);
		startMinius.setPreferredSize(new Dimension(40, 20));
		startMinius.setValue(current.get(Calendar.MINUTE));

		final JSpinField startSecond = new JSpinField();
		startSecond.setMinimum(0);
		startSecond.setMaximum(59);
		startSecond.setPreferredSize(new Dimension(40, 20));
		startSecond.setValue(current.get(Calendar.SECOND));

		current.add(Calendar.SECOND, 5);

		final JDateChooser endDateChoice = new JDateChooser(current.getTime());
		endDateChoice.setPreferredSize(new Dimension(100, 20));

		final JSpinField endHour = new JSpinField();
		endHour.setMinimum(0);
		endHour.setMaximum(23);
		endHour.setPreferredSize(new Dimension(40, 20));
		endHour.setValue(current.get(Calendar.HOUR_OF_DAY));

		final JSpinField endMinius = new JSpinField();
		endMinius.setMinimum(0);
		endMinius.setMaximum(59);
		endMinius.setPreferredSize(new Dimension(40, 20));
		endMinius.setValue(current.get(Calendar.MINUTE));

		final JSpinField endSecond = new JSpinField();
		endSecond.setMinimum(0);
		endSecond.setMaximum(59);
		endSecond.setPreferredSize(new Dimension(40, 20));
		endSecond.setValue(current.get(Calendar.SECOND));

		JButton btnExecute = new JButton("Execute");
		btnExecute.setPreferredSize(new Dimension(120, 20));

		JPanel commandPanel = new JPanel(new BorderLayout());
		JPanel timeRangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel extecutePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));


		timeRangePanel.add(new JLabel("Start time : "));
		timeRangePanel.add(startDateChoice);
		timeRangePanel.add(startHour);
		timeRangePanel.add(new JLabel(":"));
		timeRangePanel.add(startMinius);
		timeRangePanel.add(new JLabel(":"));
		timeRangePanel.add(startSecond);

		timeRangePanel.add(new JLabel("         End time : "));
		timeRangePanel.add(endDateChoice);
		timeRangePanel.add(endHour);
		timeRangePanel.add(new JLabel(":"));
		timeRangePanel.add(endMinius);
		timeRangePanel.add(new JLabel(":"));
		timeRangePanel.add(endSecond);

		extecutePanel.add(btnExecute);

		commandPanel.add(timeRangePanel, BorderLayout.CENTER);
		commandPanel.add(extecutePanel, BorderLayout.EAST);

		JPanel controlPanel = new JPanel(new BorderLayout());
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelPanel.add(new JLabel("Query:"));
		controlPanel.add(labelPanel, BorderLayout.NORTH);
		controlPanel.add(scrollPane, BorderLayout.CENTER);
		controlPanel.add(commandPanel, BorderLayout.SOUTH);

		timeRangePanel.setBackground(Color.white);
		extecutePanel.setBackground(Color.white);
		labelPanel.setBackground(Color.white);
		scrollPane.setBackground(Color.white);
		scrollPane.setBackground(Color.white);
		controlPanel.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.add(controlPanel, BorderLayout.NORTH);

		final JPanel resultTablePanel = new JPanel(new BorderLayout());

		Object[][] cells = {
		};

		String[] columnNames = new String[] {};

		JTable table = new JTable(cells, columnNames);
		JScrollPane tableScrollPanle = new JScrollPane(table);

		resultTablePanel.add(tableScrollPanle, BorderLayout.CENTER);


		final JPanel statisticsPanel = new JPanel(new BorderLayout());

		statisticsPanel.setBackground(Color.white);
		resultTablePanel.setBackground(Color.white);
		statisticsPanel.setBackground(Color.white);
		table.setBackground(Color.white);
		table.getParent().setBackground(Color.white);
		tableScrollPanle.setBackground(Color.white);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.white);
		tabbedPane.add("Result", resultTablePanel);
		tabbedPane.add("Statistics", statisticsPanel);

		this.setBackground(Color.white);
		this.add(tabbedPane, BorderLayout.CENTER);

		ImageIcon loadingBarImageIcon = new ImageIcon(
				IconResourceMap.class.getResource("/com/hp/hpl/logkv/demo/resource/LoadingBar.gif"));
		JLabel lblLoadingBar = new JLabel(loadingBarImageIcon);

	    final JModalWindow jmw = new JModalWindow(parentFrame, parentFrame);
	    jmw.setSize(200, 15);
	    jmw.relativeToOwnerChild(parentFrame);
	    jmw.getContentPane().setLayout(new BorderLayout());
	    jmw.getContentPane().add(lblLoadingBar, BorderLayout.CENTER);

		btnExecute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(taSearchQuery.getText());

				Thread t1 = new Thread() {
					public void run() {
						jmw.setLocationRelativeTo(parentFrame);
						jmw.setVisible(true);
					}
				};

				t1.run();



					Thread t2 = new Thread() {
						public void run() {

							try {

					String searchQuery = taSearchQuery.getText().trim();

					searchQuery = searchQuery.replaceAll("\r\n", " ");
					searchQuery = searchQuery.replaceAll("\n", " ");

					searchQuery = searchQuery.trim();

					if (searchQuery.length() == 0) {
						JOptionPane.showMessageDialog(QueryPanel.this, "Please input query string.");
						taSearchQuery.requestFocus();
						return;
					}

					Calendar startCalendar = startDateChoice.getCalendar();

					startCalendar.set(Calendar.HOUR_OF_DAY, startHour.getValue());

					startCalendar.set(Calendar.MINUTE, startMinius.getValue());

					startCalendar.set(Calendar.SECOND, startSecond.getValue());

					Calendar endCalendar = endDateChoice.getCalendar();

					endCalendar.set(Calendar.HOUR_OF_DAY, endHour.getValue());

					endCalendar.set(Calendar.MINUTE, endMinius.getValue());

					endCalendar.set(Calendar.SECOND, endSecond.getValue());

					if (QueryPanel.this.urlRequester == null) {
						return;
					}

					long startTime = startCalendar.getTimeInMillis();
					long endTime = endCalendar.getTimeInMillis();

					resultTablePanel.removeAll();

					QueryRequest requestObj = new QueryRequest(searchQuery, startTime, endTime);

					Object object = QueryPanel.this.urlRequester.request(requestObj);

			    	if (object == null) {
			    		System.err.println("object is null");
			    		return;
			    	}

			    	QueryResponse responseObj = (QueryResponse) object;

			    	String[] columnNames = responseObj.getColumnNames();

			    	int[] columnIds = responseObj.getColumnIds();

			    	int rowDisplayLimit = 100;

					Object[][] cells = new Object[Math.min(rowDisplayLimit, responseObj.getResultSet().length)][];

					int columnCount = columnNames.length;

					for (int i = 0; i < responseObj.getResultSet().length; i++) {

						if (i >= rowDisplayLimit) {
							break;
						}

						Record record = responseObj.getResultSet()[i];

						Object[] row = new Object[columnCount];

						for (int j = 0; j < columnCount; j++) {
							Object fieldValue = record.getFieldValue(columnIds[j]);
							row[j] = (fieldValue == null ? "" : fieldValue.toString());
						}

						cells[i] = row;
					}

					JTable table = new JTable(cells, columnNames);

					JScrollPane tableScrollPanle = new JScrollPane(table);

					table.setBackground(Color.white);
					table.getParent().setBackground(Color.white);
					tableScrollPanle.setBackground(Color.white);

					resultTablePanel.add(tableScrollPanle, BorderLayout.CENTER);
					resultTablePanel.revalidate();


					statisticsPanel.removeAll();

					QueryProcessingStatistics statistics = responseObj.getRunningStatistics();

					statisticsPanel.add(new JLabel(
							"Running Time : " + responseObj.getRunningStatistics().getRunningTime() + "(ms)"), BorderLayout.NORTH);

					JPanel gridPanel = new JPanel(new GridLayout(2, 1));

					TreeMap<Long, TRUProcessingStatistics> truStatistics = statistics.getTruStatistics();

					cells = new Object[Math.min(rowDisplayLimit, truStatistics.size())][];

					int i = 0;

					for (Map.Entry<Long, TRUProcessingStatistics> entry : truStatistics.entrySet()) {

						if (i >= rowDisplayLimit) {
							break;
						}

						TRUProcessingStatistics truS = entry.getValue();

						Object[] row = new Object[4];

						row[0] = truS.getTruId();
						row[1] = StringUtil.getEmptyIfNull(StringUtil.convertNodeName(truS.getProcessingNodeStr()));
						row[2] = truS.getNumOfReturnedRecords();
						row[3] = truS.getRunningTime();

						cells[i] = row;

						i++;
					}

					columnNames = new String[] {"TRU ID", "Processing Node", "Num Of Returned Records", "Running Time"};

					table = new JTable(cells, columnNames);

					tableScrollPanle = new JScrollPane(table);

					table.setBackground(Color.white);
					table.getParent().setBackground(Color.white);
					tableScrollPanle.setBackground(Color.white);

					gridPanel.add(tableScrollPanle);

					TreeMap<String, IngestKVProcessingStatistics> ingestStatistics = statistics.getIngestKVStatistics();

					cells = new Object[Math.min(rowDisplayLimit, ingestStatistics.size())][];

					i = 0;

					for (Map.Entry<String, IngestKVProcessingStatistics> entry : ingestStatistics.entrySet()) {

						if (i >= rowDisplayLimit) {
							break;
						}

						IngestKVProcessingStatistics ingest = entry.getValue();

						Object[] row = new Object[3];

						row[0] = StringUtil.getEmptyIfNull(StringUtil.convertNodeName(ingest.getProcessingNodeStr()));
						row[1] = ingest.getNumOfReturnedRecords();
						row[2] = ingest.getRunningTime();

						cells[i] = row;

						i++;
					}

					columnNames = new String[] {"Processing Node", "Num Of Returned Records", "Running Time"};

					table = new JTable(cells, columnNames);

					tableScrollPanle = new JScrollPane(table);

					table.setBackground(Color.white);
					table.getParent().setBackground(Color.white);
					tableScrollPanle.setBackground(Color.white);

					gridPanel.add(tableScrollPanle);

					statisticsPanel.add(gridPanel, BorderLayout.CENTER);

					statisticsPanel.revalidate();

						} finally {
							jmw.setVisible(false);
						}

						}
					};

					t2.start();

			}

		});

	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Query Panel Demo");

	    QueryPanel queryPanel = new QueryPanel(null, null);
	    frame.getContentPane().add(queryPanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 1200, 800);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }

}
