package com.hp.hpl.logkv.demo.chart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.hp.hpl.logkv.demo.util.Constants;
import com.hp.hpl.logkv.demo.util.RandomNumberGenerator;

/**
 * Resource chart panel.
 * @author Edmond
 */
public class ResourceChartPanel extends JPanel {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * time series comparator.
	 */
	private static TimeSeriesComparator timeSeriesComparator = new TimeSeriesComparator();

	/**
	 * time series list.
	 */
	private List<TimeSeries> timeSeriesList;

	/**
	 * display time range.
	 */
	private int displayTimeRange;

	/**
	 * graph XY plot.
	 */
	private XYPlot graphXYPlot;

	/**
	 * constructor method.
	 * @param chartTitle		chart title
	 * @param timeAxisLabel		time axis label
	 * @param usageAxisLabel	usage axis label
	 * @param displayTimeRange	display time range
	 * @param timeSeriesList	time series list
	 * @param limitYRange		limit Y range
	 * @param minYValue			min Y value
	 * @param maxYValue			max Y value
	 */
	public ResourceChartPanel(
			String chartTitle, String timeAxisLabel, String usageAxisLabel,
			int displayTimeRange, List<TimeSeries> timeSeriesList,
			boolean limitYRange, double minYValue, double maxYValue) {

		this.setBackground(Color.white);

		this.displayTimeRange = displayTimeRange;

		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

		Collections.sort(timeSeriesList, timeSeriesComparator);

		this.timeSeriesList = timeSeriesList;

		for (TimeSeries ts : timeSeriesList) {
			ts.setMaximumItemAge(displayTimeRange);
			timeSeriesCollection.addSeries(ts);
		}

		DateAxis timeAxis = new DateAxis(timeAxisLabel);
	    NumberAxis usageAxis = new NumberAxis(usageAxisLabel);

	    timeAxis.setAutoRange(true);
	    timeAxis.setLowerMargin(0.0D);
	    timeAxis.setUpperMargin(0.0D);
	    timeAxis.setTickLabelsVisible(true);
	    timeAxis.setFixedAutoRange(displayTimeRange);

	    usageAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	    if (limitYRange) {
	    	usageAxis.setLowerBound(minYValue);
		    usageAxis.setUpperBound(maxYValue);
	    }

	    timeAxis.setTickLabelFont(Constants.AXIS_TICK_LABEL_FONT);
	    usageAxis.setTickLabelFont(Constants.AXIS_TICK_LABEL_FONT);
	    timeAxis.setLabelFont(Constants.AXIS_LABEL_FONT);
	    usageAxis.setLabelFont(Constants.AXIS_LABEL_FONT);


	    NumberFormat nf = new CustomDecimalFormat(10);
	    usageAxis.setNumberFormatOverride(nf);

	    XYSplineRenderer graphXYLineAndShapeRenderer = new XYSplineRenderer(12);

	    for (int i = 0; i < timeSeriesList.size(); i++) {
	    	graphXYLineAndShapeRenderer.setSeriesShapesVisible(i, false);
	    }

	    graphXYPlot = new XYPlot(timeSeriesCollection, timeAxis, usageAxis, graphXYLineAndShapeRenderer);

	    JFreeChart graphJFreeChart = new JFreeChart(chartTitle, Constants.CHART_TITLE_LABEL_FONT, graphXYPlot, true);

	    ChartUtilities.applyCurrentTheme(graphJFreeChart);

	    graphXYPlot.setBackgroundPaint(Color.white);
	    graphXYPlot.setDomainGridlinePaint(Color.gray);
	    graphXYPlot.setRangeGridlinePaint(Color.gray);

	    GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(-6.0F, 0.0F);
        generalPath.lineTo(-3.0F, 6.0F);
        generalPath.lineTo(3.0F, -6.0F);
        generalPath.lineTo(6.0F, 0.0F);
	    graphXYLineAndShapeRenderer.setLegendLine(generalPath);

	    for (int i = 0; i < timeSeriesList.size(); i++) {
	    	graphXYLineAndShapeRenderer.setSeriesStroke(i, new BasicStroke(1.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	    }

	    ChartPanel chartPanel = new ChartPanel(graphJFreeChart, true);
	    chartPanel.setBorder(
	    		BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4),
	    		BorderFactory.createLineBorder(Color.black)));

	    chartPanel.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.add(chartPanel, BorderLayout.CENTER);

	}

	/**
	 * @return the timeSeriesList
	 */
	public List<TimeSeries> getTimeSeriesList() {
		return timeSeriesList;
	}

	/**
	 * refresh time series list.
	 * @param newTimeSeriesList	new time series list
	 */
	public void refreshTimeSeriesList(List<TimeSeries> newTimeSeriesList) {

		Iterator<TimeSeries> iter = timeSeriesList.iterator();
		while (iter.hasNext()) {
			TimeSeries ts = iter.next();

			boolean containsFlag = false;

			for (TimeSeries newTS : newTimeSeriesList) {
				if (newTS.getKey().equals(ts.getKey())) {
					containsFlag = true;
					break;
				}
			}

			if (!containsFlag) {
				iter.remove();
			}

		}

		for (TimeSeries newTS : newTimeSeriesList) {

			boolean containsFlag = false;

			for (TimeSeries ts : timeSeriesList) {
				if (newTS.getKey().equals(ts.getKey())) {
					containsFlag = true;
					break;
				}
			}

			if (!containsFlag) {
				timeSeriesList.add(new TimeSeries(newTS.getKey()));
			}
		}

		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

		Collections.sort(timeSeriesList, timeSeriesComparator);

		for (TimeSeries ts : timeSeriesList) {
			ts.setMaximumItemAge(displayTimeRange);
			timeSeriesCollection.addSeries(ts);
		}

		graphXYPlot.setDataset(timeSeriesCollection);
	}

	/**
	 * random data generator.
	 * @author Edmond
	 */
	public static class RandomDataGenerator extends Timer implements ActionListener {

		/**
		 * serial version UID.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * resource panel.
		 */
		private ResourceChartPanel resourcePanel;

		/**
		 * constructor method.
		 * @param delay			timer delay in millisecond
		 * @param resourcePanel	resource panel
		 */
		public RandomDataGenerator(int delay, ResourceChartPanel resourcePanel) {
	      super(delay, null);
	      this.resourcePanel = resourcePanel;
	      addActionListener(this);
	    }

		/**
		 * action performed for timer.
		 * random generate usage number
		 * @param e	action event
		 */
	    public void actionPerformed(ActionEvent e) {

	    	Millisecond time = new Millisecond();
			for (TimeSeries ts : resourcePanel.timeSeriesList) {
				ts.addOrUpdate(time, RandomNumberGenerator.getRandomUsage());
			}
		}

	}

	/**
	 * Time series comparator.
	 * @author Edmond
	 */
	public static class TimeSeriesComparator implements Comparator<TimeSeries> {

		@Override
		@SuppressWarnings("unchecked")
		public int compare(TimeSeries ts1, TimeSeries ts2) {
			return ts1.getKey().compareTo(ts2.getKey());
		}
	}

	/**
	 * Custom decimal format.
	 * @author Edmond
	 */
	public static class CustomDecimalFormat extends DecimalFormat {

		/**
		 * serial version UID.
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * min length.
		 */
		private int minLength;

		/**
		 * constructor method.
		 * @param minLength	min length
		 */
		public CustomDecimalFormat(int minLength) {
			this.minLength = minLength;
		}

		@Override
		public StringBuffer format(double number, StringBuffer result,
				FieldPosition fieldPosition) {

			StringBuffer resultStr = new StringBuffer("" + (long) number);

			if (resultStr.length() < minLength) {

				int orignalLength = minLength - resultStr.length();

				for (int i = 0; i < orignalLength; i++) {
					resultStr.insert(0, "  ");
				}

			}

			return resultStr;
		}

		@Override
		public StringBuffer format(long number, StringBuffer result,
				FieldPosition fieldPosition) {

			StringBuffer resultStr = new StringBuffer("" + number);

			if (resultStr.length() < minLength) {

				for (int i = 0; i < minLength - resultStr.length(); i++) {
					resultStr.insert(0, "  ");
				}

			}

			return resultStr;
		}

	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("System Usage Demo");

	    List<TimeSeries> timeSeriesList = new ArrayList<TimeSeries>();

	    timeSeriesList.add(new TimeSeries("CPU Usage"));
	    timeSeriesList.add(new TimeSeries("Memory Usage"));
	    timeSeriesList.add(new TimeSeries("Network Usage"));

	    ResourceChartPanel resourcePanel = new ResourceChartPanel("System Usage", "Time", "Usage", 60000, timeSeriesList, true, 0, 100);
	    frame.getContentPane().add(resourcePanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 600, 280);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    new RandomDataGenerator(1000, resourcePanel).start();
	 }

}
