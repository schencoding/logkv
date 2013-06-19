package com.hp.hpl.logkv.demo.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.general.DefaultValueDataset;

import com.hp.hpl.logkv.demo.util.Constants;
import com.hp.hpl.logkv.demo.util.RandomNumberGenerator;

/**
 * Meter dial panel.
 * @author Edmond
 */
public class MeterDialPanel extends JPanel {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * data set.
	 */
	private static DefaultValueDataset dataset = new DefaultValueDataset(0);

	/**
	 * constructor method.
	 * @param chartTitle	chart title
	 * @param unitsName		units name
	 */
	public MeterDialPanel(String chartTitle, String unitsName) {

	    MeterPlot meterPlot = new MeterPlot(dataset);
	    meterPlot.setDialOutlinePaint(Color.white);
	    meterPlot.setUnits(unitsName);
	    JFreeChart chart = new JFreeChart(chartTitle, Constants.CHART_TITLE_LABEL_FONT, meterPlot, false);

	    ChartUtilities.applyCurrentTheme(chart);

		this.setLayout(new BorderLayout());
		this.add(new ChartPanel(chart), BorderLayout.CENTER);
	}

	/**
	 * @return the data set
	 */
	public DefaultValueDataset getDataset() {
		return dataset;
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
		 * meter dial panel.
		 */
		private MeterDialPanel meterDialPanel;

		/**
		 * constructor method.
		 * @param delay				timer delay in millisecond
		 * @param meterDialPanel	meter dial panel
		 */
		public RandomDataGenerator(int delay, MeterDialPanel meterDialPanel) {
	      super(delay, null);
	      this.meterDialPanel = meterDialPanel;
	      addActionListener(this);
	    }

		/**
		 * action performed for timer.
		 * random generate usage number
		 * @param e	action event
		 */
	    public void actionPerformed(ActionEvent e) {

	    	meterDialPanel.getDataset().setValue(RandomNumberGenerator.getRandomUsage());
		}

	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Simple Dial Demo");

	    MeterDialPanel meterDialPanel = new MeterDialPanel("Meter Dial Demo", "");
	    frame.getContentPane().add(meterDialPanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 600, 280);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    new RandomDataGenerator(1000, meterDialPanel).start();
	 }

}
