package com.hp.hpl.logkv.demo.chart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.ArcDialFrame;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import com.hp.hpl.logkv.demo.util.Constants;
import com.hp.hpl.logkv.demo.util.RandomNumberGenerator;

/**
 * Simple dial panel.
 * @author Edmond
 */
public class SimpleDialPanel extends JPanel {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * default value data set.
	 */
	private DefaultValueDataset dataset = new DefaultValueDataset(50.0D);

	/**
	 * constructor method.
	 * @param chartTitle	chart title
	 */
	public SimpleDialPanel(String chartTitle) {

		 DialPlot dialPlot = new DialPlot();
		 dialPlot.setView(0.21D, 0.0D, 0.58D, 0.3D);
		 dialPlot.setDataset(this.dataset);

		 ArcDialFrame arcDialFrame = new ArcDialFrame(60.0D, 60.0D);
		 arcDialFrame.setInnerRadius(0.6D);
		 arcDialFrame.setOuterRadius(0.9D);
		 arcDialFrame.setForegroundPaint(Color.darkGray);
		 arcDialFrame.setStroke(new BasicStroke(3.0F));
		 dialPlot.setDialFrame(arcDialFrame);

		 GradientPaint gradientPaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(240, 240, 240));
		 DialBackground localDialBackground = new DialBackground(gradientPaint);
		 localDialBackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
		 dialPlot.addLayer(localDialBackground);

		 StandardDialScale standardDialScale = new StandardDialScale(0.0D, 100.0D, 115.0D, -50.0D, 10.0D, 4);
		 standardDialScale.setTickRadius(0.88D);
		 standardDialScale.setTickLabelOffset(0.07000000000000001D);
		 standardDialScale.setMajorTickIncrement(25.0D);
		 NumberFormat tickLabelNumberFormat = DecimalFormat.getNumberInstance();
		 tickLabelNumberFormat.setMaximumFractionDigits(0);
		 tickLabelNumberFormat.setMinimumFractionDigits(0);
		 standardDialScale.setTickLabelFormatter(tickLabelNumberFormat);
		 dialPlot.addScale(0, standardDialScale);

		 DialPointer.Pin localPin = new DialPointer.Pin();
		 localPin.setRadius(0.82D);
		 dialPlot.addLayer(localPin);
		 JFreeChart chart = new JFreeChart(chartTitle, Constants.CHART_TITLE_LABEL_FONT, dialPlot, false);
		 ChartPanel chartPanel = new ChartPanel(chart);

		 this.setLayout(new BorderLayout());
		 this.add(chartPanel, BorderLayout.CENTER);

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
		 * simple dial panel.
		 */
		private SimpleDialPanel simpleDialPanel;

		/**
		 * constructor method.
		 * @param delay				timer delay in millisecond
		 * @param simpleDialPanel	simple dial panel
		 */
		public RandomDataGenerator(int delay, SimpleDialPanel simpleDialPanel) {
	      super(delay, null);
	      this.simpleDialPanel = simpleDialPanel;
	      addActionListener(this);
	    }

		/**
		 * action performed for timer.
		 * random generate usage number
		 * @param e	action event
		 */
	    public void actionPerformed(ActionEvent e) {

	    	simpleDialPanel.getDataset().setValue(RandomNumberGenerator.getRandomUsage());
		}

	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Simple Dial Demo");

	    SimpleDialPanel simpleDialPanel = new SimpleDialPanel("Simple Chart Demo");
	    frame.getContentPane().add(simpleDialPanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 600, 280);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    new RandomDataGenerator(1000, simpleDialPanel).start();
	 }

}
