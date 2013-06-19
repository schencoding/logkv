package com.hp.hpl.logkv.demo.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import com.hp.hpl.logkv.demo.util.RandomNumberGenerator;

/**
 * Color meter dial panel.
 * @author Edmond
 */
public class ColorMeterDialPanel extends JPanel {

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
	 * @param unitsName		units name
	 */
	public ColorMeterDialPanel(String chartTitle, String unitsName) {

		DialPlot localDialPlot = new DialPlot();
		localDialPlot.setDataset(dataset);
		localDialPlot.setDialFrame(new StandardDialFrame());
	    localDialPlot.setBackground(new DialBackground());
	    DialTextAnnotation localDialTextAnnotation = new DialTextAnnotation(unitsName);
	    localDialTextAnnotation.setFont(new Font("Dialog", 1, 14));
	    localDialTextAnnotation.setRadius(0.7D);
	    localDialPlot.addLayer(localDialTextAnnotation);
	    DialValueIndicator localDialValueIndicator = new DialValueIndicator(0);
	    localDialPlot.addLayer(localDialValueIndicator);
	    StandardDialScale localStandardDialScale = new StandardDialScale(0, 100, -120, -300, 10, 4);
	    NumberFormat noFractionNumberFormat = DecimalFormat.getNumberInstance();
	    noFractionNumberFormat.setMaximumFractionDigits(0);
	    noFractionNumberFormat.setMaximumFractionDigits(0);
	    localStandardDialScale.setTickLabelFormatter(noFractionNumberFormat);
	    localStandardDialScale.setTickRadius(0.88D);
	    localStandardDialScale.setTickLabelOffset(0.15D);
	    localStandardDialScale.setTickLabelFont(new Font("Dialog", 0, 14));
	    localDialPlot.addScale(0, localStandardDialScale);
	    localDialPlot.addPointer(new DialPointer.Pin());
	    DialCap localDialCap = new DialCap();
	    localDialPlot.setCap(localDialCap);


	    JFreeChart chart = new JFreeChart(chartTitle, localDialPlot);

	    StandardDialRange localStandardDialRange1 = new StandardDialRange(0, 50, Color.green);
	    localStandardDialRange1.setInnerRadius(0.52D);
	    localStandardDialRange1.setOuterRadius(0.55D);
	    localDialPlot.addLayer(localStandardDialRange1);
	    StandardDialRange localStandardDialRange2 = new StandardDialRange(50, 70, Color.orange);
	    localStandardDialRange2.setInnerRadius(0.52D);
	    localStandardDialRange2.setOuterRadius(0.55D);
	    localDialPlot.addLayer(localStandardDialRange2);
	    StandardDialRange localStandardDialRange3 = new StandardDialRange(70, 100, Color.red);
	    localStandardDialRange3.setInnerRadius(0.52D);
	    localStandardDialRange3.setOuterRadius(0.55D);
	    localDialPlot.addLayer(localStandardDialRange3);
	    GradientPaint localGradientPaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
	    DialBackground localDialBackground = new DialBackground(localGradientPaint);
	    localDialBackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
	    localDialPlot.setBackground(localDialBackground);
	    localDialPlot.removePointer(0);
	    DialPointer.Pointer localPointer = new DialPointer.Pointer();
	    localDialPlot.addPointer(localPointer);

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
		 * color meter dial panel.
		 */
		private ColorMeterDialPanel colorMeterDialPanel;

		/**
		 * constructor method.
		 * @param delay					timer delay in millisecond
		 * @param colorMeterDialPanel	color meter dial panel
		 */
		public RandomDataGenerator(int delay, ColorMeterDialPanel colorMeterDialPanel) {
	      super(delay, null);
	      this.colorMeterDialPanel = colorMeterDialPanel;
	      addActionListener(this);
	    }

		/**
		 * action performed for timer.
		 * random generate usage number
		 * @param e	action event
		 */
	    public void actionPerformed(ActionEvent e) {

	    	colorMeterDialPanel.getDataset().setValue(RandomNumberGenerator.getRandomUsage());
		}

	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

	    JFrame frame = new JFrame("Simple Dial Demo");

	    ColorMeterDialPanel colorMeterDialPanel = new ColorMeterDialPanel("Color Meter Dial Demo", "Temperature");
	    frame.getContentPane().add(colorMeterDialPanel, BorderLayout.CENTER);
	    frame.setBounds(200, 120, 600, 280);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    new RandomDataGenerator(1000, colorMeterDialPanel).start();
	 }

}
