package com.hp.hpl.logkv.demo.chart;

import java.awt.BasicStroke;
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
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleInsets;

import com.hp.hpl.logkv.demo.util.Constants;
import com.hp.hpl.logkv.demo.util.RandomNumberGenerator;

/**
 * Thermometer panel.
 * @author Edmond
 */
public class ThermometerPanel extends JPanel {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * data set.
	 */
	private static DefaultValueDataset dataset = new DefaultValueDataset(50.0D);

	/**
	 * constructor method.
	 * @param chartTitle	chart title
	 */
	public ThermometerPanel(String chartTitle) {

		ThermometerPlot thermometerPlot = new ThermometerPlot(dataset);
		thermometerPlot.setInsets(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
		thermometerPlot.setPadding(new RectangleInsets(10.0D, 10.0D, 10.0D, 10.0D));
		thermometerPlot.setThermometerStroke(new BasicStroke(2.0F));
		thermometerPlot.setThermometerPaint(Color.lightGray);
		thermometerPlot.setUnits(0);
		thermometerPlot.setGap(3);
		thermometerPlot.setRange(0.0D, 100.0D);
		thermometerPlot.setSubrange(0, 0, 50);
		thermometerPlot.setSubrangePaint(0, Color.green);
		thermometerPlot.setSubrange(1, 50, 70);
		thermometerPlot.setSubrangePaint(1, Color.cyan);
		thermometerPlot.setSubrange(2, 70, 100);
		thermometerPlot.setSubrangePaint(2, Color.red);

		JFreeChart chart = new JFreeChart(chartTitle, Constants.CHART_TITLE_LABEL_FONT, thermometerPlot, false);
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
		 * thermometer panel.
		 */
		private ThermometerPanel thermometerPanel;

		/**
		 * constructor method.
		 * @param delay				timer delay in millisecond
		 * @param thermometerPanel	thermometer panel
		 */
		public RandomDataGenerator(int delay, ThermometerPanel thermometerPanel) {
	      super(delay, null);
	      this.thermometerPanel = thermometerPanel;
	      addActionListener(this);
	    }

		/**
		 * action performed for timer.
		 * random generate usage number
		 * @param e	action event
		 */
	    public void actionPerformed(ActionEvent e) {

	    	thermometerPanel.getDataset().setValue(RandomNumberGenerator.getRandomUsage());
		}

	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Thermometer Demo");

	    ThermometerPanel thermometerPanel = new ThermometerPanel("Thermometer Demo");
	    frame.getContentPane().add(thermometerPanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 600, 280);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    new RandomDataGenerator(1000, thermometerPanel).start();
	 }

}
